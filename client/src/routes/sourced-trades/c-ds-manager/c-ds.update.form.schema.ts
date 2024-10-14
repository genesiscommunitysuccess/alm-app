import { UiSchema } from '@genesislcap/foundation-forms';

export const updateFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Maturity Amount",
      "scope": "#/properties/MATURITY_AMOUNT",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Maturity Date",
      "scope": "#/properties/MATURITY_DATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Deposit Currency",
      "scope": "#/properties/DEPOSIT_CURRENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Deposit Amount",
      "scope": "#/properties/DEPOSIT_AMOUNT",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Deposit Rate",
      "scope": "#/properties/DEPOSIT_RATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Deposit Date",
      "scope": "#/properties/DEPOSIT_DATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Client Name",
      "scope": "#/properties/CLIENT_NAME",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Cd Id",
      "scope": "#/properties/CD_ID",
      "options": {}
    }
  ]
}
