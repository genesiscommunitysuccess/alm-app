import { ColDef } from '@ag-grid-community/core';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';
import { cellStyle } from '../../../utils/util-formatters';

export const columnDefs: ColDef[] = [
  {
    field: "MATURITY_AMOUNT",
    valueFormatter: getNumberFormatter("0,0.00", null),
    cellStyle: (params) => cellStyle(params),
  },
  {
    field: "MATURITY_DATE",
  },
  {
    field: "DEPOSIT_CURRENCY",
  },
  {
    field: "DEPOSIT_AMOUNT",
    valueFormatter: getNumberFormatter("0,0.00", null),
    cellStyle: (params) => cellStyle(params),
  },
  {
    field: "DEPOSIT_RATE",
    valueFormatter: getNumberFormatter("0,0.00", null),
  },
  {
    field: "DEPOSIT_DATE",
  },
  {
    field: "CLIENT_NAME",
  },
  {
    field: "CD_ID",
  }
]
