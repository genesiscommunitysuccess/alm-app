import { html, whenElse, repeat } from '@genesislcap/web-core';
import { getViewUpdateRightComponent } from '../../../utils';
import type { SourcedTradesCDsManager } from './c-ds';
import { createFormSchema } from './c-ds.create.form.schema';
import { updateFormSchema } from './c-ds.update.form.schema';
import { columnDefs } from './c-ds.column.defs';


export const CDsTemplate = html<SourcedTradesCDsManager>`
    ${whenElse(
        (x) => getViewUpdateRightComponent(x.user, ''),
        html`
            <entity-management
                design-system-prefix="rapid"
                header-case-type="capitalCase"
                enable-row-flashing
                enable-cell-flashing
                resourceName="ALL_CD_TRADES"
                deleteEvent="${(x) => getViewUpdateRightComponent(x.user, '', 'EVENT_CD_TRADE_DELETE')}"
                entityLabel="Cd Trade"
                :columns=${() => columnDefs }
                modal-position="centre"
                size-columns-to-fit
            ></entity-management>
        `,
        html`
          <not-permitted-component></not-permitted-component>
        `,
    )}`;
