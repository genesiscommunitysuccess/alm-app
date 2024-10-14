import { ColDef } from '@ag-grid-community/core';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';

export const columnDefs: ColDef[] = [
  {
    field: "TRADE_VERSION",
    valueFormatter: getNumberFormatter("0,0", null),
  },
  {
    field: "SIDE",
  },
  {
    field: "RATE",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "NOTIONAL",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "SETTLEMENT_DATE",
  },
  {
    field: "SOURCE_CURRENCY",
  },
  {
    field: "TARGET_CURRENCY",
  },
  {
    field: "BROKER_CODE",
  },
  {
    field: "SALES_NAME",
  },
  {
    field: "TRADER_NAME",
  },
  {
    field: "TRADE_STATUS",
  },
  {
    field: "TRADE_DATETIME",
  },
  {
    field: "ENTITY_NAME",
    hide: true,
  },
  {
    field: "BOOK_NAME",
    hide: true,
  },
  {
    field: "CLIENT_NAME",
    hide: true,
  },
  {
    field: "TRADE_ID",
  }
]
