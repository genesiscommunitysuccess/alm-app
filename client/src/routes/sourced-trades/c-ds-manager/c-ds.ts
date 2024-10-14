import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { CDsStyles as styles } from './c-ds.styles';
import { CDsTemplate as template } from './c-ds.template';

@customElement({
  name: 'sourced-trades-c-ds-manager',
  template,
  styles,
})
export class SourcedTradesCDsManager extends GenesisElement {
  @User user: User;
}
