import { UiSchema } from '@genesislcap/foundation-forms';

export const updateFormSchema: UiSchema = {
  "type": "LayoutVertical2Columns",
  "elements": [
    {
      "type": "Control",
      "label": "Trade Version",
      "scope": "#/properties/TRADE_VERSION",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Side",
      "scope": "#/properties/SIDE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Rate",
      "scope": "#/properties/RATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Notional",
      "scope": "#/properties/NOTIONAL",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Settlement Date",
      "scope": "#/properties/SETTLEMENT_DATE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Source Currency",
      "scope": "#/properties/SOURCE_CURRENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Target Currency",
      "scope": "#/properties/TARGET_CURRENCY",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Broker Code",
      "scope": "#/properties/BROKER_CODE",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Sales Name",
      "scope": "#/properties/SALES_NAME",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Trader Name",
      "scope": "#/properties/TRADER_NAME",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Trade Status",
      "scope": "#/properties/TRADE_STATUS",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Trade Datetime",
      "scope": "#/properties/TRADE_DATETIME",
      "options": {}
    },
    {
      "type": "Control",
      "label": "Entity Name",
      "scope": "#/properties/ENTITY_NAME",
      "options": {
        "allOptionsResourceName": "ENTITY",
        "valueField": "ENTITY_NAME",
        "labelField": "ENTITY_DESCRIPTION"
      }
    },
    {
      "type": "Control",
      "label": "Book Name",
      "scope": "#/properties/BOOK_NAME",
      "options": {
        "allOptionsResourceName": "BOOK",
        "valueField": "BOOK_NAME",
        "labelField": "BOOK_DESCRIPTION"
      }
    },
    {
      "type": "Control",
      "label": "Client Name",
      "scope": "#/properties/CLIENT_NAME",
      "options": {
        "allOptionsResourceName": "CLIENT",
        "valueField": "CLIENT_NAME",
        "labelField": "CLIENT_DESCRIPTION"
      }
    },
    {
      "type": "Control",
      "label": "Trade Id",
      "scope": "#/properties/TRADE_ID",
      "options": {
        "readonly": true
      }
    }
  ]
}
