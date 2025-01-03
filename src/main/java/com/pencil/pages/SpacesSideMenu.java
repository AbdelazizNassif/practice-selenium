package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class SpacesSideMenu {
    WebDriver driver = null ;
    BasePage page = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public SpacesSideMenu(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("get side menu entries")
    public List<String> getSideMenuEntries ()
    {
        return page.getTextOfListOfElements(driver, By.cssSelector(".sidebar-top .list-group-item-action.ng-star-inserted"));
    }
}
