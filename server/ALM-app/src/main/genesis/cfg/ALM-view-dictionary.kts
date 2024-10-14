/**
  * This file defines the views for this application. Views provide a way to
  * create a view across entities which are more friendly, for example joining a
  * trade to an instrument to provide the details of the instrument for
  * presentation next to the trade.
  *
  * Views also allow for the addition of dynamic columns calculated in real time.
  * 
  * Views may be used across the system including as inputs to consolidators,
  * expose as APIs in the request response server as snapshots of data or as real
  * time ticking APIs in the data server.

  * Full documentation on views may be found here >> https://learn.genesis.global/docs/database/fields-tables-views/views/

 */

views {
  view("LOAN_PAYMENTS_GBP", LOAN_TRADE) {
    joins {
      joining(FX_RATE) {
        on(LOAN_TRADE.PAYMENT_CURRENCY to FX_RATE { SOURCE_CURRENCY })
          .and(FX_RATE { TARGET_CURRENCY } to "GBP")
      }
    }

    fields {
      derivedField("PAYMENT_AMOUNT", DOUBLE) {
        withInput(LOAN_TRADE.PAYMENT_AMOUNT, FX_RATE.RATE) { amount, rate ->
          if (amount == null || rate == null) amount
          else amount * rate
        }
      }
      LOAN_TRADE.CLIENT_NAME
      LOAN_TRADE.PAYMENT_CURRENCY
    }
  }
  //TODO add additional views here
}
