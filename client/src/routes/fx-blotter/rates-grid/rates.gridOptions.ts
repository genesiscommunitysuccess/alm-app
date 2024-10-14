import { GridOptionsConfig } from '@genesislcap/rapid-grid-pro';
import { getNumberFormatter, getDateFormatter } from '@genesislcap/foundation-utils';
export const gridOptions: GridOptionsConfig = 
  {
    columnDefs: [
     {
          field: "RATE",
          valueFormatter: getNumberFormatter("0,0.00", null),
     },
     {
          field: "TARGET_CURRENCY",
     },
     {
          field: "SOURCE_CURRENCY",
     }
],
  }
