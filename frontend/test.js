const { Builder, By, until, expect } = require("selenium-webdriver");

(async function example() {
  let driver = await new Builder().forBrowser("chrome").build();

  const uniqueData = `test${Date.now()}@example.com`;

  await driver.get("http://localhost:8081/signup");

  await driver.wait(() =>
    driver
      .executeScript("return document.readyState")
      .then((readyState) => readyState === "complete")
  );

  const input_email = await driver.wait(
    until.elementLocated(By.id("custEmail")),
    10000
  );
  await input_email.sendKeys(uniqueData);

  const input_pw = await driver.wait(
    until.elementLocated(By.id("custPw")),
    10000
  );
  await input_pw.sendKeys("Tester01!");

  const input_name = await driver.wait(
    until.elementLocated(By.id("custName")),
    10000
  );
  await input_name.sendKeys(uniqueData);

  const input_nickname = await driver.wait(
    until.elementLocated(By.id("custNickName")),
    10000
  );
  await input_nickname.sendKeys(uniqueData);

  const agree_btn = await driver.wait(
    until.elementLocated(By.id("agree")),
    10000
  );
  await agree_btn.click();

  const signup_btn = await driver.wait(
    until.elementLocated(By.id("signupBtn")),
    10000
  );
  await signup_btn.click();

  const email_verify_page = await driver.wait(
    until.elementLocated(By.id("emailVerify")),
    10000
  );
  // 가져온 요소가 NULL이 아니라면 성공으로 판단
  expect(email_verify_page).not.toBeNull(); 
})();
