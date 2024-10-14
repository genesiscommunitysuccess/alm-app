import { UiSchema } from '@genesislcap/foundation-forms';

export const updateFormSchema: UiSchema = {
  "type": "VerticalLayout",
  "elements": [
    {
      "type": "Control",
      "label": "Payment Currency",
      "scope": "#/properties/PAYMENT_CURRENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Payment Amount",
      "scope": "#/properties/PAYMENT_AMOUNT",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Payment Date",
      "scope": "#/properties/PAYMENT_DATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Drawdown Currency",
      "scope": "#/properties/DRAWDOWN_CURRENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Drawdown Amount",
      "scope": "#/properties/DRAWDOWN_AMOUNT",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Drawdown Date",
      "scope": "#/properties/DRAWDOWN_DATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Facility Currency",
      "scope": "#/properties/FACILITY_CURRENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Facility Amount",
      "scope": "#/properties/FACILITY_AMOUNT",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Facility Name",
      "scope": "#/properties/FACILITY_NAME",
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
      "label": "Loan Id",
      "scope": "#/properties/LOAN_ID",
      "options": {}
    }
  ]
}
