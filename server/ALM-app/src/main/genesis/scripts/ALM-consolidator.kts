import global.genesis.gen.dao.enums.ALM.fx_trade.Side
import global.genesis.gen.dao.enums.ALM.fx_trade.TradeStatus

consolidators {
  consolidator("POSITION_CALC_SOURCE", FX_TRADE, POSITION) {
    select {
      POSITION {
        sum { notional / rate * ( if(side==Side.Buy) -1 else 1) } into AMOUNT
      }
    }
    where {
      tradeStatus != TradeStatus.Cancelled
    }
    groupBy {
      Position.byCurrencySettlementDate(sourceCurrency, settlementDate)
    } into {
      build {
        Position {
          settlementDate = groupId.settlementDate
          currency = groupId.currency
          amount = 0.0
        }
      }
    }
  }

  consolidator("POSITION_CALC_TARGET", FX_TRADE, POSITION) {
    select {
      POSITION {
        sum { notional * ( if(side==Side.Sell) -1 else 1) } into AMOUNT
      }
    }
    where {
      tradeStatus != TradeStatus.Cancelled
    }
    groupBy {
      Position.byCurrencySettlementDate(targetCurrency, settlementDate)
    } into {
      build {
        Position {
          settlementDate = groupId.settlementDate
          currency = groupId.currency
          amount = 0.0
        }
      }
    }
  }

  consolidator("POSITION_LOAN", LOAN_TRADE, POSITION) {
    select {
      POSITION {
        sum { paymentAmount } into AMOUNT
      }
    }
    groupBy {
      Position.byCurrencySettlementDate(paymentCurrency, paymentDate)
    } into {
      build {
        Position {
          settlementDate = groupId.settlementDate
          currency = groupId.currency
          amount = 0.0
        }
      }
    }
  }

  consolidator("POSITION_CD", CD_TRADE, POSITION) {
    select {
      POSITION {
        sum { maturityAmount } into AMOUNT
      }
    }
    groupBy {
      Position.byCurrencySettlementDate(depositCurrency, maturityDate)
    } into {
      build {
        Position {
          settlementDate = groupId.settlementDate
          currency = groupId.currency
          amount = 0.0
        }
      }
    }
  }

  // TODO - add new consolidators here
}