import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { RatesStyles as styles } from './rates.styles';
import { RatesTemplate as template } from './rates.template';

@customElement({
  name: 'fx-blotter-rates-grid',
  template,
  styles,
})
export class FxBlotterRatesGrid extends GenesisElement {
  @User user: User;
}
