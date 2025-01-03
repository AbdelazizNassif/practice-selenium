package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class HomePage {

    WebDriver driver = null ;
    BasePage page = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }

    @Step("navigate to home page")
    public HomePage navigate (){
        page.navigateToURL(driver, "https://spaces.pencilapp.com");
        return new HomePage(driver);
    }
    @Step("click continue with email button")
    public Login clickContinueWithEmail ()
    {
        page.clickElement(driver, By.xpath("//span[text()='Continue with Email']"));
        return  new Login(driver);
    }
    @Step("is continue with email button displayed")
    public boolean isContinueWithEmailDisplayed ()
    {
        return page.locateElement(driver, By.xpath("//span[text()='Continue with Email']")).isDisplayed();
    }
    @Step("get current url")
    public String getCurrentUrl ()
    {
        return driver.getCurrentUrl();
    }
}
