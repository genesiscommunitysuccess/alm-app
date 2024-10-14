import { ColDef } from '@ag-grid-community/core';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';
import { cellStyle } from '../../../utils/util-formatters';

export const columnDefs: ColDef[] = [
  {
    field: "PAYMENT_CURRENCY",
  },
  {
    field: "PAYMENT_AMOUNT",
    valueFormatter: getNumberFormatter("0,0.00", null),
    cellStyle: (params) => cellStyle(params),
  },
  {
    field: "PAYMENT_DATE",
  },
  {
    field: "DRAWDOWN_CURRENCY",
  },
  {
    field: "DRAWDOWN_AMOUNT",
    valueFormatter: getNumberFormatter("0,0.00", null),
    cellStyle: (params) => cellStyle(params),
  },
  {
    field: "DRAWDOWN_DATE",
  },
  {
    field: "FACILITY_CURRENCY",
  },
  {
    field: "FACILITY_AMOUNT",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "FACILITY_NAME",
  },
  {
    field: "CLIENT_NAME",
  },
  {
    field: "LOAN_ID",
  }
]
