import global.genesis.db.rx.entity.multi.AsyncEntityDb
import global.genesis.gen.dao.Book
import global.genesis.gen.dao.CdTrade
import global.genesis.gen.dao.Client
import global.genesis.gen.dao.Entity
import global.genesis.gen.dao.FxRate
import global.genesis.gen.dao.FxTrade
import global.genesis.gen.dao.LoanTrade
import global.genesis.gen.dao.Position
import global.genesis.gen.dao.enums.ALM.fx_trade.Side
import global.genesis.gen.dao.enums.ALM.fx_trade.TradeStatus
import global.genesis.message.core.event.EventReply
import global.genesis.testsupport.client.eventhandler.EventClientSync
import global.genesis.testsupport.jupiter.GenesisJunit
import global.genesis.testsupport.jupiter.ScriptFile
import global.genesis.testsupport.jupiter.assertedCast
import javax.inject.Inject
import kotlin.String
import kotlin.Unit
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.joda.time.DateTime.now
import org.joda.time.DateTime.parse
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

/**
 * This file tests the event handlers generated for your project.
 *
 * TODO As you edit any event handler code, for example editing existing events or adding new, you
 * should try to extend this test suite to test the changes.
 *
 * Full documentation on event handler tests may be found here >>
 * https://learn.genesis.global/docs/server/event-handler/testing/
 */
@ExtendWith(GenesisJunit::class)
@ScriptFile("ALM-eventhandler.kts")
class EventHandlerTest {
  @Inject
  lateinit var client: EventClientSync

  @Inject
  lateinit var entityDb: AsyncEntityDb

  private val adminUser: String = "admin"

  @Test
  fun `test insert FX_TRADE`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = FxTrade {
        bookName = "1"
        brokerCode = "1"
        clientName = "1"
        entityName = "1"
        notional = 0.1
        rate = 0.1
        salesName = "1"
        settlementDate = now()
        side = Side.Buy
        sourceCurrency = "1"
        targetCurrency = "1"
        tradeDatetime = now()
        tradeStatus = TradeStatus.New
        tradeVersion = 1
        traderName = "1"
      },
      messageType = "EVENT_FX_TRADE_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val fxTrade = entityDb.getBulk<FxTrade>().toList()
    assertTrue(fxTrade.isNotEmpty())
  }

  @Test
  fun `test modify FX_TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      FxTrade {
        bookName = "1"
        brokerCode = "1"
        clientName = "1"
        entityName = "1"
        notional = 0.1
        rate = 0.1
        salesName = "1"
        settlementDate = now()
        side = Side.Buy
        sourceCurrency = "1"
        targetCurrency = "1"
        tradeDatetime = now()
        tradeStatus = TradeStatus.New
        tradeVersion = 1
        traderName = "1"
      }
    )
    val tradeIdValue = result.record.tradeId
    val modifyResult = client.sendEvent(
      details = FxTrade {
        bookName = "2"
        brokerCode = "2"
        clientName = "2"
        entityName = "2"
        notional = 0.2
        rate = 0.2
        salesName = "2"
        settlementDate = parse("2024-01-01T00:00:00.000Z")
        side = Side.Sell
        sourceCurrency = "2"
        targetCurrency = "2"
        tradeDatetime = parse("2024-01-01T00:00:00.000Z")
        tradeId = tradeIdValue
        tradeStatus = TradeStatus.Amended
        tradeVersion = 2
        traderName = "2"
      },
      messageType = "EVENT_FX_TRADE_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(FxTrade.ByTradeId(tradeIdValue))
    assertEquals("2", modifiedRecord?.bookName)
    assertEquals("2", modifiedRecord?.brokerCode)
    assertEquals("2", modifiedRecord?.clientName)
    assertEquals("2", modifiedRecord?.entityName)
    assertEquals(0.2, modifiedRecord?.notional)
    assertEquals(0.2, modifiedRecord?.rate)
    assertEquals("2", modifiedRecord?.salesName)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.settlementDate))
    assertEquals(Side.Sell, modifiedRecord?.side)
    assertEquals("2", modifiedRecord?.sourceCurrency)
    assertEquals("2", modifiedRecord?.targetCurrency)
    assertEquals("2", modifiedRecord?.traderName)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.tradeDatetime))
    assertEquals(TradeStatus.Amended, modifiedRecord?.tradeStatus)
    assertEquals(2, modifiedRecord?.tradeVersion)
  }

  @Test
  fun `test delete FX_TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      FxTrade {
        bookName = "1"
        brokerCode = "1"
        clientName = "1"
        entityName = "1"
        notional = 0.1
        rate = 0.1
        salesName = "1"
        settlementDate = now()
        side = Side.Buy
        sourceCurrency = "1"
        targetCurrency = "1"
        tradeDatetime = now()
        tradeStatus = TradeStatus.New
        tradeVersion = 1
        traderName = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<FxTrade>().toList().size
    val tradeIdValue = result.record.tradeId
    val deleteResult = client.sendEvent(
      details = FxTrade.ByTradeId(tradeIdValue),
      messageType = "EVENT_FX_TRADE_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<FxTrade>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert ENTITY`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Entity {
        entityDescription = "1"
        entityName = "1"
      },
      messageType = "EVENT_ENTITY_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val entity = entityDb.getBulk<Entity>().toList()
    assertTrue(entity.isNotEmpty())
  }

  @Test
  fun `test modify ENTITY`(): Unit = runBlocking {
    val result = entityDb.insert(
      Entity {
        entityDescription = "1"
        entityName = "1"
      }
    )
    val entityNameValue = result.record.entityName
    val modifyResult = client.sendEvent(
      details = Entity {
        entityDescription = "2"
        entityName = entityNameValue
      },
      messageType = "EVENT_ENTITY_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Entity.ByName(entityNameValue))
    assertEquals("2", modifiedRecord?.entityDescription)
  }

  @Test
  fun `test delete ENTITY`(): Unit = runBlocking {
    val result = entityDb.insert(
      Entity {
        entityDescription = "1"
        entityName = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<Entity>().toList().size
    val entityNameValue = result.record.entityName
    val deleteResult = client.sendEvent(
      details = Entity.ByName(entityNameValue),
      messageType = "EVENT_ENTITY_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Entity>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert BOOK`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Book {
        bookDescription = "1"
        bookName = "1"
      },
      messageType = "EVENT_BOOK_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val book = entityDb.getBulk<Book>().toList()
    assertTrue(book.isNotEmpty())
  }

  @Test
  fun `test modify BOOK`(): Unit = runBlocking {
    val result = entityDb.insert(
      Book {
        bookDescription = "1"
        bookName = "1"
      }
    )
    val bookNameValue = result.record.bookName
    val modifyResult = client.sendEvent(
      details = Book {
        bookDescription = "2"
        bookName = bookNameValue
      },
      messageType = "EVENT_BOOK_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Book.ByName(bookNameValue))
    assertEquals("2", modifiedRecord?.bookDescription)
  }

  @Test
  fun `test delete BOOK`(): Unit = runBlocking {
    val result = entityDb.insert(
      Book {
        bookDescription = "1"
        bookName = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<Book>().toList().size
    val bookNameValue = result.record.bookName
    val deleteResult = client.sendEvent(
      details = Book.ByName(bookNameValue),
      messageType = "EVENT_BOOK_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Book>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert CLIENT`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Client {
        clientDescription = "1"
        clientName = "1"
      },
      messageType = "EVENT_CLIENT_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val client = entityDb.getBulk<Client>().toList()
    assertTrue(client.isNotEmpty())
  }

  @Test
  fun `test modify CLIENT`(): Unit = runBlocking {
    val result = entityDb.insert(
      Client {
        clientDescription = "1"
        clientName = "1"
      }
    )
    val clientNameValue = result.record.clientName
    val modifyResult = client.sendEvent(
      details = Client {
        clientDescription = "2"
        clientName = clientNameValue
      },
      messageType = "EVENT_CLIENT_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Client.ByName(clientNameValue))
    assertEquals("2", modifiedRecord?.clientDescription)
  }

  @Test
  fun `test delete CLIENT`(): Unit = runBlocking {
    val result = entityDb.insert(
      Client {
        clientDescription = "1"
        clientName = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<Client>().toList().size
    val clientNameValue = result.record.clientName
    val deleteResult = client.sendEvent(
      details = Client.ByName(clientNameValue),
      messageType = "EVENT_CLIENT_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Client>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert POSITION`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = Position {
        amount = 0.1
        currency = "1"
        settlementDate = now()
      },
      messageType = "EVENT_POSITION_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val position = entityDb.getBulk<Position>().toList()
    assertTrue(position.isNotEmpty())
  }

  @Test
  fun `test modify POSITION`(): Unit = runBlocking {
    val result = entityDb.insert(
      Position {
        amount = 0.1
        currency = "1"
        settlementDate = now()
      }
    )
    val currencyValue = result.record.currency
    val modifyResult = client.sendEvent(
      details = Position {
        amount = 0.2
        currency = currencyValue
        settlementDate = parse("2024-01-01T00:00:00.000Z")
      },
      messageType = "EVENT_POSITION_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(Position.ByCurrency(currencyValue))
    assertEquals(0.2, modifiedRecord?.amount)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.settlementDate))
  }

  @Test
  fun `test delete POSITION`(): Unit = runBlocking {
    val result = entityDb.insert(
      Position {
        amount = 0.1
        currency = "1"
        settlementDate = now()
      }
    )
    val numRecordsBefore = entityDb.getBulk<Position>().toList().size
    val currencyValue = result.record.currency
    val deleteResult = client.sendEvent(
      details = Position.ByCurrency(currencyValue),
      messageType = "EVENT_POSITION_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<Position>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert LOAN_TRADE`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = LoanTrade {
        clientName = "1"
        drawdownAmount = 0.1
        drawdownCurrency = "1"
        drawdownDate = now()
        facilityAmount = 0.1
        facilityCurrency = "1"
        facilityName = "1"
        loanId = "1"
        paymentAmount = 0.1
        paymentCurrency = "1"
        paymentDate = now()
      },
      messageType = "EVENT_LOAN_TRADE_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val loanTrade = entityDb.getBulk<LoanTrade>().toList()
    assertTrue(loanTrade.isNotEmpty())
  }

  @Test
  fun `test modify LOAN_TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      LoanTrade {
        clientName = "1"
        drawdownAmount = 0.1
        drawdownCurrency = "1"
        drawdownDate = now()
        facilityAmount = 0.1
        facilityCurrency = "1"
        facilityName = "1"
        loanId = "1"
        paymentAmount = 0.1
        paymentCurrency = "1"
        paymentDate = now()
      }
    )
    val loanIdValue = result.record.loanId
    val modifyResult = client.sendEvent(
      details = LoanTrade {
        clientName = "2"
        drawdownAmount = 0.2
        drawdownCurrency = "2"
        drawdownDate = parse("2024-01-01T00:00:00.000Z")
        facilityAmount = 0.2
        facilityCurrency = "2"
        facilityName = "2"
        loanId = loanIdValue
        paymentAmount = 0.2
        paymentCurrency = "2"
        paymentDate = parse("2024-01-01T00:00:00.000Z")
      },
      messageType = "EVENT_LOAN_TRADE_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(LoanTrade.ByLoanId(loanIdValue))
    assertEquals("2", modifiedRecord?.clientName)
    assertEquals(0.2, modifiedRecord?.drawdownAmount)
    assertEquals("2", modifiedRecord?.drawdownCurrency)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.drawdownDate))
    assertEquals(0.2, modifiedRecord?.facilityAmount)
    assertEquals("2", modifiedRecord?.facilityCurrency)
    assertEquals("2", modifiedRecord?.facilityName)
    assertEquals(0.2, modifiedRecord?.paymentAmount)
    assertEquals("2", modifiedRecord?.paymentCurrency)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.paymentDate))
  }

  @Test
  fun `test delete LOAN_TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      LoanTrade {
        clientName = "1"
        drawdownAmount = 0.1
        drawdownCurrency = "1"
        drawdownDate = now()
        facilityAmount = 0.1
        facilityCurrency = "1"
        facilityName = "1"
        loanId = "1"
        paymentAmount = 0.1
        paymentCurrency = "1"
        paymentDate = now()
      }
    )
    val numRecordsBefore = entityDb.getBulk<LoanTrade>().toList().size
    val loanIdValue = result.record.loanId
    val deleteResult = client.sendEvent(
      details = LoanTrade.ByLoanId(loanIdValue),
      messageType = "EVENT_LOAN_TRADE_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<LoanTrade>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert CD_TRADE`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = CdTrade {
        cdId = "1"
        clientName = "1"
        depositAmount = 0.1
        depositCurrency = "1"
        depositDate = now()
        depositRate = 0.1
        maturityAmount = 0.1
        maturityDate = now()
      },
      messageType = "EVENT_CD_TRADE_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val cdTrade = entityDb.getBulk<CdTrade>().toList()
    assertTrue(cdTrade.isNotEmpty())
  }

  @Test
  fun `test modify CD_TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      CdTrade {
        cdId = "1"
        clientName = "1"
        depositAmount = 0.1
        depositCurrency = "1"
        depositDate = now()
        depositRate = 0.1
        maturityAmount = 0.1
        maturityDate = now()
      }
    )
    val cdIdValue = result.record.cdId
    val modifyResult = client.sendEvent(
      details = CdTrade {
        cdId = cdIdValue
        clientName = "2"
        depositAmount = 0.2
        depositCurrency = "2"
        depositDate = parse("2024-01-01T00:00:00.000Z")
        depositRate = 0.2
        maturityAmount = 0.2
        maturityDate = parse("2024-01-01T00:00:00.000Z")
      },
      messageType = "EVENT_CD_TRADE_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(CdTrade.ByCdId(cdIdValue))
    assertEquals("2", modifiedRecord?.clientName)
    assertEquals(0.2, modifiedRecord?.depositAmount)
    assertEquals("2", modifiedRecord?.depositCurrency)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.depositDate))
    assertEquals(0.2, modifiedRecord?.depositRate)
    assertEquals(0.2, modifiedRecord?.maturityAmount)
    assertEquals(0, parse("2024-01-01T00:00:00.000Z").compareTo(modifiedRecord?.maturityDate))
  }

  @Test
  fun `test delete CD_TRADE`(): Unit = runBlocking {
    val result = entityDb.insert(
      CdTrade {
        cdId = "1"
        clientName = "1"
        depositAmount = 0.1
        depositCurrency = "1"
        depositDate = now()
        depositRate = 0.1
        maturityAmount = 0.1
        maturityDate = now()
      }
    )
    val numRecordsBefore = entityDb.getBulk<CdTrade>().toList().size
    val cdIdValue = result.record.cdId
    val deleteResult = client.sendEvent(
      details = CdTrade.ByCdId(cdIdValue),
      messageType = "EVENT_CD_TRADE_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<CdTrade>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }

  @Test
  fun `test insert FX_RATE`(): Unit = runBlocking {
    val result = client.sendEvent(
      details = FxRate {
        rate = 0.1
        sourceCurrency = "1"
        targetCurrency = "1"
      },
      messageType = "EVENT_FX_RATE_INSERT",
      userName = adminUser
    )
    result.assertedCast<EventReply.EventAck>()
    val fxRate = entityDb.getBulk<FxRate>().toList()
    assertTrue(fxRate.isNotEmpty())
  }

  @Test
  fun `test modify FX_RATE`(): Unit = runBlocking {
    val result = entityDb.insert(
      FxRate {
        rate = 0.1
        sourceCurrency = "1"
        targetCurrency = "1"
      }
    )
    val sourceCurrencyValue = result.record.sourceCurrency
    val modifyResult = client.sendEvent(
      details = FxRate {
        rate = 0.2
        sourceCurrency = sourceCurrencyValue
        targetCurrency = "2"
      },
      messageType = "EVENT_FX_RATE_MODIFY",
      userName = adminUser
    )
    modifyResult.assertedCast<EventReply.EventAck>()
    val modifiedRecord = entityDb.get(FxRate.BySourceCurrency(sourceCurrencyValue))
    assertEquals(0.2, modifiedRecord?.rate)
    assertEquals("2", modifiedRecord?.targetCurrency)
  }

  @Test
  fun `test delete FX_RATE`(): Unit = runBlocking {
    val result = entityDb.insert(
      FxRate {
        rate = 0.1
        sourceCurrency = "1"
        targetCurrency = "1"
      }
    )
    val numRecordsBefore = entityDb.getBulk<FxRate>().toList().size
    val sourceCurrencyValue = result.record.sourceCurrency
    val deleteResult = client.sendEvent(
      details = FxRate.BySourceCurrency(sourceCurrencyValue),
      messageType = "EVENT_FX_RATE_DELETE",
      userName = adminUser
    )
    deleteResult.assertedCast<EventReply.EventAck>()
    val numRecordsAfter = entityDb.getBulk<FxRate>().toList().size
    assertEquals(numRecordsBefore - 1, numRecordsAfter)
  }
}
