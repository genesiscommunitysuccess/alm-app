import global.genesis.gen.dao.enums.ALM.fx_trade.TradeStatus
import global.genesis.httpclient.GenesisHttpClient
import global.genesis.httpclient.request.HttpMethod
import global.genesis.httpclient.request.HttpRequest
import global.genesis.message.core.HttpStatusCode
import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.ContentType
import io.ktor.serialization.jackson.jackson
import io.ktor.util.reflect.TypeInfo

/**
  * This file defines the event handler APIs. These APIs (modeled after CQRS
  * commands) allow callers to manipulate the data in the database. By default,
  * insert, update and delete event handlers (or commands) have been created.
  * These result in the data being written to the database and messages to be
  * published for the rest of the platform to by notified.
  * 
  * Custom event handlers may be added to extend the functionality of the
  * application as well as custom logic added to existing event handlers.
  *
  * The following objects are visible in each eventhandler
  * `event.details` which holds the entity that this event handler is acting upon
  * `entityDb` which is database object used to perform INSERT, MODIFY and UPDATE the records
  * Full documentation on event handler may be found here >> https://learn.genesis.global/docs/server/event-handler/introduction/

 */

eventHandler {
  eventHandler<FxTrade>("FX_TRADE_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      val insertedRow = entityDb.insert(details)
      // return an ack response which contains a list of record IDs
      ack(listOf(mapOf(
        "TRADE_ID" to insertedRow.record.tradeId,
      )))
    }
  }
  eventHandler<FxTrade>("FX_TRADE_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      //Increment version number and set to amended
      details.tradeVersion = details.tradeVersion + 1
      details.tradeStatus = TradeStatus.Amended
      entityDb.modify(details)
      ack()
    }
  }
  //Event object changed to <FxTrade> rather than ById
  eventHandler<FxTrade>("FX_TRADE_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      //Increment version number and set to cancelled
      details.tradeVersion = details.tradeVersion + 1
      details.tradeStatus = TradeStatus.Cancelled
      //Call modify, rather than delete so it stays in blotter
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Entity>("ENTITY_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<Entity>("ENTITY_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Entity.ByName>("ENTITY_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<Book>("BOOK_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<Book>("BOOK_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Book.ByName>("BOOK_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<Client>("CLIENT_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<Client>("CLIENT_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Client.ByName>("CLIENT_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<Position>("POSITION_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<Position>("POSITION_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<Position.ByCurrencySettlementDate>("POSITION_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<LoanTrade>("LOAN_TRADE_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<LoanTrade>("LOAN_TRADE_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<LoanTrade.ByLoanId>("LOAN_TRADE_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<CdTrade>("CD_TRADE_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<CdTrade>("CD_TRADE_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<CdTrade.ByCdId>("CD_TRADE_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }
  eventHandler<FxRate>("FX_RATE_INSERT", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.insert(details)
      ack()
    }
  }
  eventHandler<FxRate>("FX_RATE_MODIFY", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.modify(details)
      ack()
    }
  }
  eventHandler<FxRate.BySourceCurrencyTargetCurrency>("FX_RATE_DELETE", transactional = true) {
    onCommit { event ->
      val details = event.details
      entityDb.delete(details)
      ack()
    }
  }

  //TODO - add new or customize event handlers to add validation, access permission checks
  /**
    * If you want to provide some validation before the action, you need to have an onValidate block before the onCommit. The last value of the code block must always be the return message type.
    * eventHandler<THIS_ENTITY>(name = "THIS_ENTITY_INSERT") {
    *      onValidate { event ->
    *          val thisEntity = event.details
    *          require(thisEntity.name != null) { "ThisEntity should have a name" }
    *          ack()
    *      }
    *      onCommit { event ->
    *          ...
    *      }
    *  }
    * You can add permission to the query by using permission codes like below
    * permissioning {
    *     // 'permission Code' list, users must have the permission to access the enclosing resource
    *     permissionCodes = listOf("PERMISSION1", "PERMISSION2")
    * }
    * Full documentation on permissions may be found here https://learn.genesis.global/docs/server/access-control/authorisation-overview/#authorisation
    */

    eventHandler<Message>("LOAN_TRADE_REST_API", transactional = true) {
      // Instantiate the client outside the onCommit to avoid repeating
      val baseUrl = "https://playground.demo.genesis.global/gwf"
      val authUrl = "$baseUrl/event-login-auth"
      val loansUrl = "$baseUrl/ALL_LOAN"

      val ktorClient = HttpClient {
          install(ContentNegotiation) {
              jackson(ContentType.Application.Json)
          }
      }
      val client = GenesisHttpClient(ktorClient)

      onCommit {
          // Login to the external application
          val response = client.submitRequest<LoginRequest, LoginResponse>(
              HttpRequest(
                  url = authUrl,
                  method = HttpMethod.POST,
                  body = LoginRequest(),
                  headers = mapOf("Content-Type" to "application/json")
              ),
              responseTypeInfo = TypeInfo(type = LoginResponse::class, reifiedType = LoginResponse::class.java),
          )

          val statusCode = response.statusCode
          if (statusCode != HttpStatusCode.Ok)
              return@onCommit nack("Received $statusCode from $authUrl")

          val responseAuthToken = response.data.sessionAuthToken
          LOG.info("Retrieved session auth token")

          // Send a data logon to the external dataserver and retrieve the current loan data
          val loanMessages = client.dataLogon(loansUrl, responseAuthToken).data.row

          /**
           * Note:
           * Why are we inserting into a table? We retrieve from a dataserver, inherently this data can change
           * We retrieve updates for loan messages not a full view of all loan messages, which means we get inserts, updates, deletes etc.
           * If we keep blindly inserting and the data changes this will break, or we write even more code to deal with each operation and keep our DB in sync
           * If we just want to display these messages in a UI, use a req-rep on the external app instead of a dataserver
           * Even better, as mentioned elsewhere, use a simple spring boot app for the external app and simplify everything
           * I would estimate an 80%+ code reduction if you use this approach
           *
           */
          // Map and insert the retrieved loan messages
          loanMessages.map { loanMessage ->
              loanMessage.asLoanTrade()
          }.forEach { loanTrade ->
              LOG.info("Loan Trade: {}", loanTrade)
              entityDb.upsert(loanTrade)
          }

          // Close the dataserver subscription (important!)
          val closeResponse = client.delete<String> {
              url = loansUrl
              headers(
                  "Content-Type" to "application/json",
                  "SESSION_AUTH_TOKEN" to responseAuthToken,
                  "SOURCE_REF" to "data-logon-1", // must match source ref of data logon
              )
          }

          LOG.info("Received status {} from close subscription request", closeResponse.statusCode)

          ack()
      }

  }

}

suspend fun GenesisHttpClient.dataLogon(url: String, authToken: String) = submitRequest<DataLogonRequest, AllLoansResponse>(
    HttpRequest(
        url = url,
        method = HttpMethod.POST,
        body = DataLogonRequest(
            // Max rows will determine the number of records retrieved
            DataLogonDetails(
                maxRows = 10,
                maxView = 100
            )
        ),
        headers = mapOf(
            "Content-Type" to "application/json",
            "SESSION_AUTH_TOKEN" to authToken,
            "SOURCE_REF" to "data-logon-1",
            "USER_NAME" to "JaneDee",
        )
    ),
    responseTypeInfo = TypeInfo(type = AllLoansResponse::class, reifiedType = AllLoansResponse::class.java),
)
