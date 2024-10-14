import { html, whenElse, repeat } from '@genesislcap/web-core';
import { getViewUpdateRightComponent } from '../../../utils';
import type { SourcedTradesLoansbyClientChart } from './loans-by-client';


export const LoansByClientTemplate = html<SourcedTradesLoansbyClientChart>`
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
                     resourceName="LOAN_PAYMENTS_GBP"
                     server-fields="CLIENT_NAME PAYMENT_AMOUNT"
                   ></chart-datasource>
          </rapid-g2plot-chart>
        `,
        html`
            <not-permitted-component></not-permitted-component>
        `,
    )}`;
