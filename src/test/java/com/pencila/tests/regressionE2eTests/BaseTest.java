package com.pencila.tests.regressionE2eTests;

import driverSettigns.DriverFactory;
import io.qameta.allure.Step;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import utils.ScreenshotUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class BaseTest {

    public WebDriver driver = null;

    @BeforeAll
    @Step("Initialize chrome browser")
    public void initializeDriver() {
        driver = new DriverFactory().getDriver();
        driver.manage().window().maximize();
    }

    @AfterAll
    @Step("Quit browser")
    public void quitDriver() {
        driver.quit();
    }

    @AfterEach
    public void takeScreenShot(TestInfo testInfo) {
        new ScreenshotUtil().
                attachScreenshotToAllureReport(driver, testInfo.getDisplayName());
    }


}


