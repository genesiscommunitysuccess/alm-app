import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { CDsByClientStyles as styles } from './c-ds-by-client.styles';
import { CDsByClientTemplate as template } from './c-ds-by-client.template';

@customElement({
  name: 'sourced-trades-c-dsby-client-chart',
  template,
  styles,
})
export class SourcedTradesCDsbyClientChart extends GenesisElement {
  @User user: User;
}
