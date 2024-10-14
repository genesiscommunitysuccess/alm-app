import { html, whenElse, repeat } from '@genesislcap/web-core';
import { getViewUpdateRightComponent } from '../../../utils';
import type { SourcedTradesCDsbyClientChart } from './c-ds-by-client';


export const CDsByClientTemplate = html<SourcedTradesCDsbyClientChart>`
    ${whenElse(
        (x) => getViewUpdateRightComponent(x.user, ''),
        html`
          <rapid-g2plot-chart
            type="pie"
            :config="${(x) => ({
                radius: 0.75,
                angleField: 'value',
                colorField: 'groupBy',
            })}"
          >
            <chart-datasource
              resourceName="ALL_CD_TRADES"
              server-fields="CLIENT_NAME DEPOSIT_AMOUNT"
            ></chart-datasource>
          </rapid-g2plot-chart>
        `,
        html`
            <not-permitted-component></not-permitted-component>
        `,
    )}`;
