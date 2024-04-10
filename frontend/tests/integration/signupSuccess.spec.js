import { describe, expect, test, beforeAll, afterAll } from "@jest/globals";
const { Builder, By, until } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");

describe("일반회원 회원가입 테스트", () => {
  let driver;
  beforeAll(async () => {
    driver = await new Builder()
      .forBrowser("chrome")
      .setChromeOptions(
        new chrome.Options().addArguments("--headless") // 크롬을 띄우지 않도록 설정
      )
      .build();

    await driver.get("http://localhost:8081/signup");

    await driver.wait(() =>
      driver
        .executeScript("return document.readyState")
        .then((readyState) => readyState === "complete")
    );
  });

  afterAll(async () => {
    await driver.quit();
  }, 40000);

  test("회원가입 성공 케이스 테스트", async () => {
    const uniqueData = `test${Date.now()}@example.com`;

    const input_email = await driver.wait(
      until.elementLocated(By.id("custEmail")),
    );
    await input_email.sendKeys(uniqueData);

    const input_pw = await driver.wait(
      until.elementLocated(By.id("custPw")),
    );
    await input_pw.sendKeys("Tester01!");

    const input_name = await driver.wait(
      until.elementLocated(By.id("custName")),
    );
    await input_name.sendKeys(uniqueData);

    const input_nickname = await driver.wait(
      until.elementLocated(By.id("custNickName")),
    );
    await input_nickname.sendKeys(uniqueData);

    const agree_btn = await driver.wait(
      until.elementLocated(By.id("agree")),
    );
    await agree_btn.click();

    const signup_btn = await driver.wait(
      until.elementLocated(By.id("signupBtn")),
    );
    await signup_btn.click();

    const email_verify_page = await driver.wait(
      until.elementLocated(By.id("emailVerify")),
      10000
    );
    // 가져온 요소가 NULL이 아니라면 성공으로 판단
    expect(email_verify_page).not.toBeNull();
  });
});
