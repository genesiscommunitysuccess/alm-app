import { html, whenElse, repeat } from '@genesislcap/web-core';
import { getViewUpdateRightComponent } from '../../../utils';
import type { SourcedTradesLoansManager } from './loans';
import { createFormSchema } from './loans.create.form.schema';
import { updateFormSchema } from './loans.update.form.schema';
import { columnDefs } from './loans.column.defs';


export const LoansTemplate = html<SourcedTradesLoansManager>`
    ${whenElse(
        (x) => getViewUpdateRightComponent(x.user, ''),
        html`
            <entity-management
                design-system-prefix="rapid"
                header-case-type="capitalCase"
                enable-row-flashing
                enable-cell-flashing
                resourceName="ALL_LOAN_TRADES"
                deleteEvent="${(x) => getViewUpdateRightComponent(x.user, '', 'EVENT_LOAN_TRADE_DELETE')}"
                entityLabel="Loan Trade"
                :columns=${() => columnDefs }
                modal-position="centre"
                size-columns-to-fit
            ></entity-management>
        `,
        html`
          <not-permitted-component></not-permitted-component>
        `,
    )}`;
