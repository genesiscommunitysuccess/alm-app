import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { LoansStyles as styles } from './loans.styles';
import { LoansTemplate as template } from './loans.template';

@customElement({
  name: 'sourced-trades-loans-manager',
  template,
  styles,
})
export class SourcedTradesLoansManager extends GenesisElement {
  @User user: User;
}
