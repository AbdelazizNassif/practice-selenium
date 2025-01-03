package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SpaceBar {
    WebDriver driver = null ;
    BasePage page = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public SpaceBar(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("leave space")
    public SpacesListPage leaveSpace ()
    {
        page.clickElement(driver, By.cssSelector("[icon='menu']"));
        page.clickElement(driver, By.xpath("//span[text()='Leave this Space']"));
        return new SpacesListPage(driver);
    }
}
