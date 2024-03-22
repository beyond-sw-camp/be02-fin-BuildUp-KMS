const config = {
  reporters: [
    'default',
    ['jest-junit', {outputDirectory: 'test-results', outputName: 'report.xml'}],
  ],
  preset: '@vue/cli-plugin-unit-jest',
  testMatch: [
    '**/__tests__/**/*.spec.[jt]s?(x)',
    '**/tests/unit/**/*.spec.[jt]s?(x)'
  ],
  moduleNameMapper: {
    '^/stores/userStore$': '<rootDir>/src/stores/userStore.js',
    '^pinia$': '<rootDir>/node_modules/pinia',
    '^axios$': '<rootDir>/node_modules/axios',
  },
  transformIgnorePatterns: [
    '^/node_modules/(?!axios)',
    '^/node_modules/(?!pinia)',
  ],
  transform: {
    '^.+\\.js$': 'babel-jest',
  },
};
module.exports = config;