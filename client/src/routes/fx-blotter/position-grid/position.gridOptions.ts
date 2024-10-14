import { GridOptionsConfig } from '@genesislcap/rapid-grid-pro';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';
import { cellStyle } from '../../../utils/util-formatters';
export const gridOptions: GridOptionsConfig = 
  {
    columnDefs: [
     {
          field: "AMOUNT",
          valueFormatter: getNumberFormatter("0,0.00", null),
          cellStyle: (params) => cellStyle(params)
     },
     {
          field: "CURRENCY",
     },
     {
          field: "SETTLEMENT_DATE",
     }
],
  }
