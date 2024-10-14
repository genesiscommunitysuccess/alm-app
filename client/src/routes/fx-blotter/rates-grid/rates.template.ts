import { html, whenElse, repeat } from '@genesislcap/web-core';
import { getViewUpdateRightComponent } from '../../../utils';
import type { FxBlotterRatesGrid } from './rates';
import { gridOptions } from './rates.gridOptions';


export const RatesTemplate = html<FxBlotterRatesGrid>`
    ${whenElse(
        (x) => getViewUpdateRightComponent(x.user, ''),
        html`
          <rapid-grid-pro
            header-case-type="capitalCase"
            only-template-col-defs
            enable-row-flashing
            enable-cell-flashing
            >
            <grid-pro-genesis-datasource
              resource-name="ALL_FX_RATES"
              :deferredGridOptions=${() => ({ onRowClicked: gridOptions?.onRowClicked })}
            ></grid-pro-genesis-datasource>
            ${repeat(
              (x) => gridOptions?.columnDefs,
              html`
                <grid-pro-column :definition=${(x) => x}></grid-pro-column>
              `,
            )}
          </rapid-grid-pro>
        `,
        html`
            <not-permitted-component></not-permitted-component>
        `,
    )}`;
