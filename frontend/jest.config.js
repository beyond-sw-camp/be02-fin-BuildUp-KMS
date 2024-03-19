const config = {
  preset: '@vue/cli-plugin-unit-jest',
  testMatch : [
    '<rootDir>/(tests/unit/**/*.spec.(js|jsx|ts|tsx)|**/__tests__/*.(js|jsx|ts|tsx))'
  ],
  reporters: [
    'default',
    ['jest-junit', {outputDirectory: 'test-results', outputName: 'report.xml'}],
  ],
};

module.exports = config;
