import { isDev } from '@genesislcap/foundation-utils';
import { html } from '@genesislcap/web-core';
import type { SourcedTrades } from './sourced-trades';
import { SourcedTradesLoansManager } from './loans-manager';
import { SourcedTradesCDsManager } from './c-ds-manager';
import { SourcedTradesLoansbyClientChart } from './loans-by-client-chart';
import { SourcedTradesCDsbyClientChart } from './c-ds-by-client-chart';

SourcedTradesLoansManager;
SourcedTradesCDsManager;
SourcedTradesLoansbyClientChart;
SourcedTradesCDsbyClientChart;

export const SourcedTradesTemplate = html<SourcedTrades>`
  <rapid-layout auto-save-key="${() => (isDev() ? null : 'Sourced Trades_1728641572991')}">
     <rapid-layout-region type="horizontal">
         <rapid-layout-region type="vertical">
             <rapid-layout-item title="Loans">
                 <sourced-trades-loans-manager></sourced-trades-loans-manager>
             </rapid-layout-item>
             <rapid-layout-item title="CDs">
                 <sourced-trades-c-ds-manager></sourced-trades-c-ds-manager>
             </rapid-layout-item>
         </rapid-layout-region>
         <rapid-layout-region type="vertical">
             <rapid-layout-item title="Loans by Client - GBP">
                 <sourced-trades-loansby-client-chart></sourced-trades-loansby-client-chart>
             </rapid-layout-item>
                          <rapid-layout-item title="Loans by Currency - GBP Equivalent">
                 <rapid-g2plot-chart
                   type="bar"
                   :config="${(x) => ({
                       xField: 'value',
                       yField: 'groupBy',
                       seriesField: 'groupBy',
                       barWidthRatio: 0.8,
                   })}"
                 >
                   <chart-datasource
                     resourceName="LOAN_PAYMENTS_GBP"
                     server-fields="PAYMENT_CURRENCY PAYMENT_AMOUNT"
                   ></chart-datasource>
                 </rapid-g2plot-chart>
             </rapid-layout-item>
             <rapid-layout-item title="CDs by Client">
                 <sourced-trades-c-dsby-client-chart></sourced-trades-c-dsby-client-chart>
             </rapid-layout-item>
         </rapid-layout-region>
     </rapid-layout-region>
  </rapid-layout>
`;
