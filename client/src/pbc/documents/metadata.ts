import type { AppMetadata } from '@genesislcap/foundation-shell/app';

/**
 * @public
 */
export const metadata: AppMetadata = {
  name: '@genesislcap/pbc-documents-ui',
  description: 'Genesis Documents PBC',
  version: '1.7.0',
  prerequisites: {
    '@genesislcap/foundation-ui': '14.*',
    gsf: '8.*',
  },
  dependencies: {
    '@genesislcap/pbc-documents-ui': '0.0.13',
    serverDepId: '8.4.0',
  },
};
