import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { PositionStyles as styles } from './position.styles';
import { PositionTemplate as template } from './position.template';

@customElement({
  name: 'fx-blotter-position-grid',
  template,
  styles,
})
export class FxBlotterPositionGrid extends GenesisElement {
  @User user: User;
}
