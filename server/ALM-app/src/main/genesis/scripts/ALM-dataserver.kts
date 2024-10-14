/**
  * This file defines the data server queries for the application. Data server
  * will load the data defined here and expose APIs for the clients including
  * Genesis UI Components to present this data as well as keep it up to date as
  * the data set changes underneath.
  *
  * Data server queries also allow for the definition of dynamic columns as well
  * as rich access controls definitions.

  * Full documentation on dataserver may be found here >> https://learn.genesis.global/docs/server/data-server/introduction/

 */

dataServer {
  query("ALL_FX_TRADES", FX_TRADE) {
    fields {
      TRADE_ID
      TRADE_VERSION
      SIDE
      RATE
      NOTIONAL
      SETTLEMENT_DATE
      SOURCE_CURRENCY
      TARGET_CURRENCY
      BROKER_CODE
      SALES_NAME
      TRADER_NAME
      TRADE_STATUS
      TRADE_DATETIME
      ENTITY_NAME
      BOOK_NAME
      CLIENT_NAME
    }
  }
  query("ALL_ENTITYS", ENTITY) {
    fields {
      ENTITY_DESCRIPTION
      ENTITY_NAME
    }
  }
  query("ALL_BOOKS", BOOK) {
    fields {
      BOOK_DESCRIPTION
      BOOK_NAME
    }
  }
  query("ALL_CLIENTS", CLIENT) {
    fields {
      CLIENT_DESCRIPTION
      CLIENT_NAME
    }
  }
  query("ALL_POSITIONS", POSITION) {
    fields {
      AMOUNT
      CURRENCY
      SETTLEMENT_DATE
    }
  }
  query("ALL_LOAN_TRADES", LOAN_TRADE) {
    fields {
      PAYMENT_CURRENCY
      PAYMENT_AMOUNT
      PAYMENT_DATE
      DRAWDOWN_CURRENCY
      DRAWDOWN_AMOUNT
      DRAWDOWN_DATE
      FACILITY_CURRENCY
      FACILITY_AMOUNT
      FACILITY_NAME
      CLIENT_NAME
      LOAN_ID
    }
  }
  query("ALL_CD_TRADES", CD_TRADE) {
    fields {
      MATURITY_AMOUNT
      MATURITY_DATE
      DEPOSIT_CURRENCY
      DEPOSIT_AMOUNT
      DEPOSIT_RATE
      DEPOSIT_DATE
      CLIENT_NAME
      CD_ID
    }
  }
  query("ALL_FX_RATES", FX_RATE) {
    fields {
      RATE
      TARGET_CURRENCY
      SOURCE_CURRENCY
    }
  }
  query("LOAN_PAYMENTS_GBP", LOAN_PAYMENTS_GBP) {
    fields {
      PAYMENT_AMOUNT
      CLIENT_NAME
      PAYMENT_CURRENCY
    }
  }

  //TODO - add new queries or update existing queries to add authorization
  /**
    * You can add derived field like below
    * derivedField("FIELD_NAME", BOOLEAN) {
    *                 THIS_ENTITY.ATTR1 == "USD"
    *             }
    * You can add permission to the query by using permission codes like below
    * permissioning {
    *     // 'permission Code' list, users must have the permission to access the enclosing resource
    *     permissionCodes = listOf("PERMISSION1", "PERMISSION2")
    * }
    * Full documentation on permissions may be found here https://learn.genesis.global/docs/server/access-control/authorisation-overview/#authorisation  */

}
