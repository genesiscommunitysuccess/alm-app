import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { FxBlotterStyles as styles } from './fx-blotter.styles';
import { FxBlotterTemplate as template } from './fx-blotter.template';

@customElement({
  name: 'fx-blotter-route',
  template,
  styles,
})
export class FxBlotter extends GenesisElement {
  @User user: User;

  constructor() {
    super();
  }
}
