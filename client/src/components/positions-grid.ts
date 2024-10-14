import { customElement, FASTElement, html, ref } from '@microsoft/fast-element';
import { Connect } from '@genesislcap/foundation-comms';
import { getDateFormatter, getNumberFormatter } from '@genesislcap/foundation-utils';
import { GridPro } from '@genesislcap/rapid-grid-pro';
import { cellStyle } from '../utils/util-formatters';

@customElement({
  name: 'positions-grid',
  template: html<PositionsGrid>`
    <rapid-grid-pro
      ${ref('grid')}
      @onGridReady="${(x, e) => x.onGridReady(e)}"
    >
    </rapid-grid-pro>
  `
})
export class PositionsGrid extends FASTElement {

  @Connect connect!: Connect;

  grid: GridPro;

  connectedCallback() {
    super.connectedCallback();
    this.grid.gridOptions = {};
  }

  onGridReady(e): void {
    this.connect.stream('ALL_POSITIONS', async () => {
      const rows = await this.getPositionsData();
      const currencies = this.getAllCurrenciesFromStream(rows);
      const colDefs = this.createColDefs(rows, currencies);
      const rowData = this.mapRowData(rows, currencies)
      this.grid.gridApi.setColumnDefs(colDefs);
      this.grid.gridApi.setRowData(rowData)
    }, console.error);
  }

  async getPositionsData() {
    const message = await this.connect.request('POSITION')
    return message?.REPLY;
  }

  private mapRowData(message: any[], currencies: string[]): any[] {
    if (!message) {
      return [];
    }

    const rowMap: { [key: string] : any } = message.reduce((acc, row) => {

      const { SETTLEMENT_DATE, AMOUNT, CURRENCY } = row;

      if (!acc[SETTLEMENT_DATE]) {
        acc[SETTLEMENT_DATE] = {
          settlementDate: SETTLEMENT_DATE,
          [CURRENCY]: AMOUNT
        }
      } else {
        const existingValue = acc[SETTLEMENT_DATE][CURRENCY] !== undefined ? acc[SETTLEMENT_DATE][CURRENCY] : 0;
        acc[SETTLEMENT_DATE][CURRENCY] = existingValue + AMOUNT
      }

      return acc;

    }, {});

    return Object.keys(rowMap).map(key => rowMap[key]).sort((a,b) => a.settlementDate > b.settlementDate ? 1 : -1);
  }

  private getAllCurrenciesFromStream(message: any[]): string[] {

    if (!message) {
      return [];
    }

    return message.reduce((currencies: string[], row) => {
      const { CURRENCY } = row;
      if (!currencies.includes(CURRENCY)) {
        currencies.push(CURRENCY)
      }

      return currencies;
    }, []);
  }

  private createColDefs(message: any[], currencies: string[]) {

    const colDefs: any[] = [
      {
        field: "settlementDate",
        headerName: "Settlement Date",
        hide: false,
        valueFormatter: getDateFormatter("en-GB", {
          year: 'numeric',
          month: 'short',
          day: 'numeric',
        })
      }
    ]

    if (!message || message.length) {
      currencies.forEach(c => {
        colDefs.push({
          field: c,
          headerName: c,
          hide: false,
          type: 'rightAligned',
          valueFormatter: getNumberFormatter("0,0.00", null),
          cellStyle: (params) => cellStyle(params)
        })
      });

      return colDefs;
    }

  }
}