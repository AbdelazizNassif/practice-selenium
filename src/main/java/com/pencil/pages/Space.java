package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Space {
    WebDriver driver = null ;
    BasePage page = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public Space(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("Click shapes tool")
    public void clickShapes ()
    {
        page.clickElement(driver, By.cssSelector("[data-name='space-toolbar-button-shapes']"));
    }
    @Step("Click select tool")
    public void clickSelect ()
    {
        page.clickElement(driver, By.cssSelector("[data-name='space-toolbar-button-pointer']"));
    }
    @Step("Draw line")
    public void drawLine () {
        page.clickElement(driver, By.cssSelector("[data-name='space-toolbar-button-object-line']"));
        new Actions(driver)
                .moveToLocation(300,300)
                .clickAndHold()
                .moveByOffset(0,50)
                .release()
                .perform();
    }
    @Step("move line")
    public void moveLine () {
        new Actions(driver)
                .moveToLocation(300, 300)
                .moveByOffset(0, 25)
                .clickAndHold()
                .moveByOffset(200,0)
                .release()
                .perform();
    }
    @Step("select text tool")
    public void selectTextTool () {
        page.clickElement(driver, By.cssSelector("[data-name='space-toolbar-button-text']"));
    }
    @Step("use text tool")
    public void useTextTool (String text) {
        new Actions(driver)
                .moveToLocation(500,300)
                .click()
                .sendKeys(text)
                .moveByOffset(50,50)
                .click()
                .perform();
    }
}
