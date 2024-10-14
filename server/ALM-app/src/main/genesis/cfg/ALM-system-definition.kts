import java.net.InetAddress;
/**
 * System              : Genesis Business Library
 * Sub-System          : multi-pro-code-test Configuration
 * Version             : 1.0
 * Copyright           : (c) Genesis
 * Date                : 2022-03-18
 * Function : Provide system definition config for multi-pro-code-test.
 *
 * Modification History
 */
systemDefinition {
    global {
        // the following have been set up as global definitions to be used as part of the pipelines script, these can be host specific as well
        item("BOOTSTRAP_SERVER", "boot-qjjhmpj3.c2.kafka-serverless.eu-west-2.amazonaws.com:9098")
        item("CONSUMER_GROUP_ID", InetAddress.getLocalHost().getHostName())
        // see topic creation comment in docker-compose.yml
        item("KAFKA_TOPIC", "fx-rate")
        // for running kafka locally we have set all security configurations to be PLAINTEXT, ensure that you use the appropriate security config for your application
        item("KAFKA_SECURITY_CONFIG", "SASL_SSL")
    }

    systems {

    }

}