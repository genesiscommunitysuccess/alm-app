import { User } from '@genesislcap/foundation-user';
import { customElement, GenesisElement } from '@genesislcap/web-core';
import { SourcedTradesStyles as styles } from './sourced-trades.styles';
import { SourcedTradesTemplate as template } from './sourced-trades.template';

@customElement({
  name: 'sourced-trades-route',
  template,
  styles,
})
export class SourcedTrades extends GenesisElement {
  @User user: User;

  constructor() {
    super();
  }
}
