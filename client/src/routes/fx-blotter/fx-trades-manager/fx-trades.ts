import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { FxTradesStyles as styles } from './fx-trades.styles';
import { FxTradesTemplate as template } from './fx-trades.template';

@customElement({
  name: 'fx-blotter-fx-trades-manager',
  template,
  styles,
})
export class FxBlotterFxTradesManager extends GenesisElement {
  @User user: User;
}
