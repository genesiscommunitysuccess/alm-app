package global.genesis

import global.genesis.gen.dao.CdTrade
import global.genesis.pipeline.`file`.CsvRow
import global.genesis.pipeline.api.SuspendElementOperator
import global.genesis.pipeline.api.db.DbOperation
import kotlin.String
import kotlin.collections.Map
import org.joda.time.DateTime
import org.slf4j.Logger
import org.slf4j.LoggerFactory

/**
 * TODO Update the `Mapper` class to suit your application's needs
 * See https://learn.genesis.global/docs/how-to/ht-ingest-csv/ for a detailed overview and examples
 */
public class CdTradeLoadCdTradeCsvDataMapper :
    SuspendElementOperator<CsvRow<Map<String, String>>, DbOperation<CdTrade>> {
  override suspend fun apply(input: CsvRow<Map<String, String>>): DbOperation<CdTrade> =
      DbOperation.Upsert(
    CdTrade {
      maturityAmount = input.data.getValue("MATURITY_AMOUNT").toDouble()
      maturityDate = DateTime.parse(input.data.getValue("MATURITY_DATE"))
      depositCurrency = input.data.getValue("DEPOSIT_CURRENCY")
      depositAmount = input.data.getValue("DEPOSIT_AMOUNT").toDouble()
      depositRate = input.data.getValue("DEPOSIT_RATE").toDouble()
      depositDate = DateTime.parse(input.data.getValue("DEPOSIT_DATE"))
      clientName = input.data.getValue("CLIENT_NAME")
      cdId = input.data.getValue("CD_ID")
    }
  )

  public companion object {
    public val log: Logger = LoggerFactory.getLogger(CdTradeLoadCdTradeCsvDataMapper::class.java)
  }
}
