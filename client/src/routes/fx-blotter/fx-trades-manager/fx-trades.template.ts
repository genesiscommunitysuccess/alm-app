import { html, whenElse, repeat } from '@genesislcap/web-core';
import { getViewUpdateRightComponent } from '../../../utils';
import type { FxBlotterFxTradesManager } from './fx-trades';
import { createFormSchema } from './fx-trades.create.form.schema';
import { updateFormSchema } from './fx-trades.update.form.schema';
import { columnDefs } from './fx-trades.column.defs';


export const FxTradesTemplate = html<FxBlotterFxTradesManager>`
    ${whenElse(
        (x) => getViewUpdateRightComponent(x.user, ''),
        html`
            <entity-management
                design-system-prefix="rapid"
                header-case-type="capitalCase"
                enable-row-flashing
                enable-cell-flashing
                resourceName="ALL_FX_TRADES"
                createEvent="${(x) => getViewUpdateRightComponent(x.user, '', 'EVENT_FX_TRADE_INSERT')}"
                :createFormUiSchema=${() => createFormSchema }
                updateEvent="${(x) => getViewUpdateRightComponent(x.user, '', 'EVENT_FX_TRADE_MODIFY')}"
                :updateFormUiSchema=${() => updateFormSchema}
                deleteEvent="${(x) => getViewUpdateRightComponent(x.user, '', 'EVENT_FX_TRADE_DELETE')}"
                entityLabel="Fx Trade"
                :columns=${() => columnDefs }
                modal-position="centre"
                size-columns-to-fit
            ></entity-management>
        `,
        html`
          <not-permitted-component></not-permitted-component>
        `,
    )}`;
