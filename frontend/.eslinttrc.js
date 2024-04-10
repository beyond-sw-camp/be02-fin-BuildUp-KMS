module.exports = {
  root: true,
  env: {
    node: true,
  },
  extends: ["plugin:vue/vue3-essential", "eslint:recommended"],
  parserOptions: {
    parser: "@babel/eslint-parser",
    requireConfigFile: false,
  },
  rules: {
    "no-console": "warn", //console.log 사용 시 경고
    "no-unused-vars": "warn", //할당되지 않은 변수 있을 시 경고
  },
};
