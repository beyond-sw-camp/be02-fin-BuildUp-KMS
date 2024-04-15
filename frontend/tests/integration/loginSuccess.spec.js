import { describe, expect, test, beforeAll, afterAll } from "@jest/globals";
const { Builder, By, until } = require("selenium-webdriver");
const chrome = require("selenium-webdriver/chrome");

describe("회원 로그인 테스트", () => {
  let driver;
  beforeAll(async () => {
    jest.setTimeout(60000);

    driver = await new Builder()
      .forBrowser("chrome")
      .setChromeOptions(
        new chrome.Options().addArguments("--headless") // 크롬을 띄우지 않도록 설정
      )
      .build();

    await driver.get("http://192.168.0.61/");

    await driver.wait(() =>
      driver
        .executeScript("return document.readyState")
        .then((readyState) => readyState === "complete")
    );
  });

  afterAll(async () => {
    await driver.quit();
  }, 60000);

  test("회원 로그인 성공 케이스 테스트", async () => {
    const loginStep1_btn = await driver.wait(
      until.elementLocated(By.id("loginStep-1"))
    );
    //   await loginStep1_btn.click();
    await driver.executeScript("arguments[0].click();", loginStep1_btn);

    const loginStep2_btn = await driver.wait(
      until.elementLocated(By.id("loginStep-2"))
    );
    await loginStep2_btn.click();

    const input_email = await driver.wait(
      until.elementLocated(By.id("custEmail"))
    );
    await input_email.sendKeys("test01@gmail.com");

    const input_pw = await driver.wait(until.elementLocated(By.id("custPw")));
    await input_pw.sendKeys("Qwer1234@");

    const login_btn = await driver.wait(
      until.elementLocated(By.id("loginBtn"))
    );
    await login_btn.click();

    const userNickName = await driver.wait(
      until.elementLocated(By.id("userNickName")),
      60000
    );
    // 가져온 요소가 NULL이 아니라면 성공으로 판단
    expect(userNickName).not.toBeNull();
  });
});
