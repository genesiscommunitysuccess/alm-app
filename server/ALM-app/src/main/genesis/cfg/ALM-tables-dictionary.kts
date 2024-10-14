/**
  * This file defines the entities (or tables) for the application.  
  * Entities aggregation a selection of the universe of fields defined in 
  * {app-name}-fields-dictionary.kts file into a business entity.  
  *
  * Note: indices defined here control the APIs available to the developer.
  * For example, if an entity requires lookup APIs by one or more of its attributes, 
  * be sure to define either a unique or non-unique index.

  * Full documentation on tables may be found here >> https://learn.genesis.global/docs/database/fields-tables-views/tables/

 */

tables {
  table(name = "FX_TRADE", id = 11_000, audit = details(id = 11_500, sequence = "FT")) {
    field("TRADE_ID", STRING).sequence("TR")
    field("BOOK_NAME", STRING).notNull()
    field("BROKER_CODE", STRING)
    field("CLIENT_NAME", STRING).notNull()
    field("ENTITY_NAME", STRING).notNull()
    field("NOTIONAL", DOUBLE).notNull()
    field("RATE", DOUBLE).notNull()
    field("SALES_NAME", STRING)
    field("SETTLEMENT_DATE", DATE).notNull()
    field("SIDE", ENUM("Sell","Buy")).default("Buy").notNull()
    field("SOURCE_CURRENCY", STRING).notNull()
    field("TARGET_CURRENCY", STRING).notNull()
    field("TRADER_NAME", STRING).notNull()
    field("TRADE_DATETIME", DATETIME).notNull()
    field("TRADE_STATUS", ENUM("New","Amended","Cancelled")).default("New").notNull()
    field("TRADE_VERSION", LONG).notNull().default(1)

    primaryKey("TRADE_ID")

  }
  table(name = "ENTITY", id = 11_001, audit = details(id = 11_501, sequence = "EA")) {
    field("ENTITY_DESCRIPTION", STRING).notNull()
    field("ENTITY_NAME", STRING).notNull()

    primaryKey("ENTITY_NAME")

  }
  table(name = "BOOK", id = 11_002, audit = details(id = 11_502, sequence = "BA")) {
    field("BOOK_DESCRIPTION", STRING).notNull()
    field("BOOK_NAME", STRING).notNull()

    primaryKey("BOOK_NAME")

  }
  table(name = "CLIENT", id = 11_003, audit = details(id = 11_503, sequence = "CA")) {
    field("CLIENT_DESCRIPTION", STRING).notNull()
    field("CLIENT_NAME", STRING).notNull()

    primaryKey("CLIENT_NAME")

  }
  table(name = "POSITION", id = 11_004) {
    field("AMOUNT", DOUBLE).notNull()
    field("CURRENCY", STRING).notNull()
    field("SETTLEMENT_DATE", DATE).notNull()

    primaryKey("CURRENCY", "SETTLEMENT_DATE")

  }
  table(name = "LOAN_TRADE", id = 11_005, audit = details(id = 11_504, sequence = "LT")) {
    field("CLIENT_NAME", STRING).notNull()
    field("DRAWDOWN_AMOUNT", DOUBLE).notNull()
    field("DRAWDOWN_CURRENCY", STRING).notNull()
    field("DRAWDOWN_DATE", DATE).notNull()
    field("FACILITY_AMOUNT", DOUBLE).notNull()
    field("FACILITY_CURRENCY", STRING).notNull()
    field("FACILITY_NAME", STRING).notNull()
    field("LOAN_ID", STRING).notNull()
    field("PAYMENT_AMOUNT", DOUBLE).notNull()
    field("PAYMENT_CURRENCY", STRING).notNull()
    field("PAYMENT_DATE", DATE).notNull()

    primaryKey("LOAN_ID")

  }
  table(name = "CD_TRADE", id = 11_006, audit = details(id = 11_505, sequence = "CT")) {
    field("CD_ID", STRING).notNull()
    field("CLIENT_NAME", STRING).notNull()
    field("DEPOSIT_AMOUNT", DOUBLE).notNull()
    field("DEPOSIT_CURRENCY", STRING).notNull()
    field("DEPOSIT_DATE", DATE).notNull()
    field("DEPOSIT_RATE", DOUBLE).notNull()
    field("MATURITY_AMOUNT", DOUBLE).notNull()
    field("MATURITY_DATE", DATE).notNull()

    primaryKey("CD_ID")

  }
  table(name = "FX_RATE", id = 11_007) {
    field("RATE", DOUBLE).notNull()
    field("SOURCE_CURRENCY", STRING).notNull()
    field("TARGET_CURRENCY", STRING).notNull()

    primaryKey("SOURCE_CURRENCY", "TARGET_CURRENCY")

  }

  // TODO 2. Add further tables you wish to add to the application here. See the comments at the top of this file for learning and guidance.
}
