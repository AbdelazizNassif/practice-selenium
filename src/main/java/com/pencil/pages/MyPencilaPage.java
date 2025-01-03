package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MyPencilaPage {

    WebDriver driver = null ;
    BasePage page = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public MyPencilaPage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }

    public MyPencilaPage navigate (){
        page.navigateToURL(driver, "https://my.pencilapp.com/");
        return new MyPencilaPage(driver);
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
