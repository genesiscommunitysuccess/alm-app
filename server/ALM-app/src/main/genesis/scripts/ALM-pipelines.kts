import global.genesis.pipeline.file.CsvRow
import global.genesis.CdTradeLoadCdTradeCsvDataMapper
import global.genesis.pipeline.api.db.DbOperation
import kotlinx.coroutines.flow.flow
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.apache.kafka.clients.consumer.ConsumerRecords
import org.apache.kafka.common.serialization.StringDeserializer

val source = kafkaSource<String, String> {
    // now you can define the kafka specific configurations, these can either be hardcoded into this script or configurable via system definitions as shown below
    bootstrapServers = systemDefinition.getItem("BOOTSTRAP_SERVER").toString()
    groupId = systemDefinition.getItem("CONSUMER_GROUP_ID").toString()
    // here we are using the out of box deserialisers for simplicity but as mentioned in the README you can create your own as per your requirement
    // you can provide any deserialiser as per your requirement as long as it is of type Deserializer<T> where T is the type of your key / value respectively specified when initialising kafkaSource above
    keyDeserializer = StringDeserializer()
    // here you can use PriceReceivedDeserialiser() for the value and the ConsumerRecord returned would have key String, value PriceReceived
    valueDeserializer = StringDeserializer()
    // ensure that this kafka topic has been created, in this example we do so when initialising the docker container
    topic = systemDefinition.getItem("KAFKA_TOPIC").toString()
    securityProtocol = systemDefinition.getItem("KAFKA_SECURITY_CONFIG").toString()
    // if you would like to provide any additional kafka consumer config you can do this like so
    additionalConfig = mapOf(
        "sasl.mechanism" to "AWS_MSK_IAM",
        "sasl.jaas.config" to "software.amazon.msk.auth.iam.IAMLoginModule required awsDebugCreds=false awsMaxRetries=\"10\" awsMaxBackOffTimeMs=\"500\";",
        "sasl.client.callback.handler.class" to "software.amazon.msk.auth.iam.IAMClientCallbackHandler"
    )
    
}

// initialise the operator that converts the output of the kafka source (ConsumerRecords<String, Int> and returns a flow of each ConsumerRecord object in that batch
val splitOperator: SplitOperator<ConsumerRecords<String, String>, ConsumerRecord<String, String>> = SplitOperator { consumerRecords ->
    flow {
        // here we iterate over every ConsumerRecord
        consumerRecords.forEach {
            // here we emit it to the resulting flow
            emit(it)
        }
    }
}

/**
 * This file defines the pipelines for this application.
 * Pipelines are used for the ingress (pulling in) and egress (sending out) of data into and out
 * of an application, typically from/to an external source.
 *
 * Full documentation on pipelines may be found here >> https://learn.genesis.global/docs/server/data-pipelines/introduction/
 *
 */

val loadCdTradeCsvDataFileSource = camelSource {
  location = getDefaultLocalFileCamelLocation("file://server/ALM-app/src/main/genesis/data/loadData?", "CDs")
}

pipelines {
  pipeline("LOAD_CD_TRADE_CSV_DATA") {
    source(loadCdTradeCsvDataFileSource)
    .split(csvRawDecoder())
    .map(CdTradeLoadCdTradeCsvDataMapper())
    .onOperationError { element: CsvRow<Map<String, String>>, context: PipelineContext<*>, throwable: Throwable ->
      LOG.warn("Pipeline {} failed to process element {}", context.name, element, throwable)
      OperationErrorAction.SKIP_ELEMENT
    }
    .sink(dbSink())
    .onCompletion { context ->
      LOG.info("Completed LOAD_CD_TRADE_CSV_DATA. File: ${context.data.name}")
    }
  }

  pipeline("KAFKA_TO_DB_PIPELINE") {
    // sourcing from the kafka source as defined above
    source(source)
        // split operator to split up batch of ConsumerRecords as defined above
        .split(splitOperator).split { input -> 
            flow<FxRate>{
                val values = input.value().split("\",\"")

                for (fxRates in values) {
                    val fxRate = fxRates.split(",")
                    val rate = fxRate[0].substring(fxRate[0].indexOf("\"") + 1).trim()
                    val targetCurrency = fxRate[1].substringBefore("\\")
                    val sourceCurrency = fxRate[2].substringBefore("\\").substringBefore("\"")
                    emit(FxRate(rate.toDouble(), targetCurrency, sourceCurrency))
                }
            }
        }
        // here we are converting each PriceReceived object into a DbOperation to insert into the database
        .map {
            // in order to use the database sink we must provide it a DbOperation to perform - in this case it's an insert on each PriceReceived object provided by the above operation
            DbOperation.Upsert(it)
        }
        // here we are using a simple database sink to perform the above operation - txDbSink is a transactional database sink
        // if you would like to use a non-transactional database sink, simple replace txDbSink() with dbSink()
        .sink(txDbSink())
  }

}
