package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SpacesListPage {
    WebDriver driver = null;
    BasePage page = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public SpacesListPage(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("Get time from logging in to loading spaces list")
    public Long getTimeFromFinishingLoginToSpacesListing(Long startTime) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("tr.space-row")));
        long endTime = System.currentTimeMillis();
        return endTime - startTime;
    }
    @Step("Get number of spaces")
    public int getNumberOfSpaces() {
        return page.locateListOfElements(driver, By.cssSelector("tr.space-row")).size();
    }
    @Step("Get first space title")
    public String getFirstSpaceTitle() {
        return page.getTextOfListOfElements(driver, By.cssSelector(".space-title-section .space-title")).get(0);
    }
    @Step("is enter space button displayed")
    public boolean isEnterSpaceButtonEnabled() {
        return page.locateElement(driver, By.xpath("//span[contains(text(),' Enter Space')]")).isEnabled();
    }
    @Step("click enter space button")
    public Space clickEnterSpace() {
        page.clickElement(driver, By.xpath("//span[contains(text(),' Enter Space')]"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-name='space-toolbar-button-shapes']")));
        return new Space(driver);
    }


}
