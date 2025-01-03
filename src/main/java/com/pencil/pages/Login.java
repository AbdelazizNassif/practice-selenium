package com.pencil.pages;

import filesReaders.PropertyFileReader;
import io.github.cdimascio.dotenv.Dotenv;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Login {

    WebDriver driver = null ;
    BasePage page = null;
    protected Dotenv dotenv = Dotenv.load();
    private WebDriverWait wait;
    final PropertyFileReader executionProps = new PropertyFileReader("execution.properties");

    public Login(WebDriver driver) {
        this.driver = driver;
        page = new BasePage(driver);
        wait = new WebDriverWait(driver,
                Duration.ofSeconds(executionProps.getIntegerProperty("WAIT_TIME")));
    }
    @Step("Login to app")
    public SpacesListPage loginWithEmail (){
        page.typeOnInputField(driver, By.cssSelector(".form-signup input"), "pencil-task@teml.net");
        page.clickElement(driver, By.xpath("//*[text()='Continue']"));
        page.typeOnInputField(driver, By.cssSelector("#password-value"), "PencilTask2025");
        page.clickElement(driver, By.xpath("//*[text()='Continue']"));
        return new SpacesListPage(driver);
    }
}
