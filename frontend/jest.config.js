const config = {
  preset: '@vue/cli-plugin-unit-jest',
  testMatch :  ["<rootDir>/tests/unit/**/*.spec.js"],
  reporters: [
    'default',
    ['jest-junit', {outputDirectory: 'test-results', outputName: 'report.xml'}],
  ],
  transformIgnorePatterns: ['<rootDir>/node_modules/(?!axios)'],
};
module.exports = config;