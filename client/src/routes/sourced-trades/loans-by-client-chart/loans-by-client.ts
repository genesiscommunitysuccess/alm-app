import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { LoansByClientStyles as styles } from './loans-by-client.styles';
import { LoansByClientTemplate as template } from './loans-by-client.template';

@customElement({
  name: 'sourced-trades-loansby-client-chart',
  template,
  styles,
})
export class SourcedTradesLoansbyClientChart extends GenesisElement {
  @User user: User;
}
