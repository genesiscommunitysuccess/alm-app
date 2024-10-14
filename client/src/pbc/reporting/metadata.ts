import type { AppMetadata } from '@genesislcap/foundation-shell/app';

/**
 * @public
 */
export const metadata: AppMetadata = {
  name: '@genesislcap/pbc-reporting',
  description: 'Genesis Reporting PBC',
  version: '1.7.0',
  prerequisites: {
    '@genesislcap/foundation-ui': '14.*',
    gsf: '8.*',
  },
  dependencies: {
    '@genesislcap/pbc-reporting-ui': '1.0.374',
    serverDepId: '8.4.0',
  },
};
