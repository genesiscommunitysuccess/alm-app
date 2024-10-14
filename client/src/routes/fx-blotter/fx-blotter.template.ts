import { isDev } from '@genesislcap/foundation-utils';
import { html } from '@genesislcap/web-core';
import type { FxBlotter } from './fx-blotter';
import { FxBlotterFxTradesManager } from './fx-trades-manager';
import { FxBlotterPositionGrid } from './position-grid';
import { FxBlotterRatesGrid } from './rates-grid';
import { PositionsGrid } from '../../components/positions-grid';

FxBlotterFxTradesManager;
FxBlotterPositionGrid;
FxBlotterRatesGrid;
PositionsGrid;

export const FxBlotterTemplate = html<FxBlotter>`
  <rapid-layout auto-save-key="${() => (isDev() ? null : 'FX Blotter_1728641572989')}">
     <rapid-layout-region>
         <rapid-layout-item title="FX Trades">
             <fx-blotter-fx-trades-manager></fx-blotter-fx-trades-manager>
         </rapid-layout-item>
         <rapid-layout-region type="vertical">
 <rapid-layout-item title="Positions">
     <positions-grid></positions-grid>
 </rapid-layout-item>
         <rapid-layout-item title="Rates">
             <fx-blotter-rates-grid></fx-blotter-rates-grid>
         </rapid-layout-item>
         </rapid-layout-region>
     </rapid-layout-region>
  </rapid-layout>
`;
