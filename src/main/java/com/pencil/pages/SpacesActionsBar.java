package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class SpacesActionsBar {
    WebDriver driver = null ;
    BasePage page = null;
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public SpacesActionsBar(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("is profile picture displayed")
    public boolean isProfilePictureDisplayed ()
    {
        return page.locateElement(driver, By.cssSelector(".spaces-header span.profile-icon")).isDisplayed();
    }
    @Step("sign out from app")
    public void signOut ()
    {
        page.clickElement(driver, By.cssSelector(".spaces-header span.profile-icon"));
        page.clickElement(driver, By.xpath("//span[text()= 'Sign-out']"));
        new LogoutPopup(driver).clickLogout();
    }

    private class LogoutPopup {
        WebDriver driver = null ;
        BasePage page = null;
        private WebDriverWait wait;
        final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

        public LogoutPopup(WebDriver driver) {
            this.driver = driver;
            page = new BasePage(driver);
            wait = new WebDriverWait(driver,
                    Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
        }
        @Step("click logout")
        public void clickLogout () {
            page.clickElement(driver, By.xpath("//*[text()='Logout']"));
        }
    }
}
