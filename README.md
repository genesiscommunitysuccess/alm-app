# ALM

This project has been created from the Genesis Blank Application Seed. Our seeds allow users to quickly bootstrap
their projects. Each seed adheres to strict Genesis best practices, and has passed numerous performance, compliance and
accessibility checks. 

Asset Liability Management

# Introduction

The Asset Liability Management application covers the booking, amendment and cancellation of FX Cash trades. It provides a number of common requirements and features of a typical trading space:

- The application builds a currency-movement ladder from the FX Cash trades, showing aggregate movements by currency and date.
- It consumes and displays ticking market data from a Kafka channel.
- A series of Certificates of Deposit transactions are imported from a csv feed file and added into the aggregate cash-movement ladder as data arrives or is modified.
- Loan transactions are sourced via a Rest API call and are added into the aggregate cash-movement ladder as data arrives or is modified.


There are detailed instructions and step-by-step guide on how to build the application available here:
https://learn.genesis.global/docs/how-to/alm-app/ht-alm-intro/



# License

This is free and unencumbered software released into the public domain. For full terms, see [LICENSE](./LICENSE)

**NOTE** This project uses licensed components listed in the next section, thus licenses for those components are required during development.

## Licensed components
Genesis low-code platform
