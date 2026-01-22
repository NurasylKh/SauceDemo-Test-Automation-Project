package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(LoginPage.class);
    
    // Locators
    private By usernameField = By.id("user-name");
    private By passwordField = By.id("password");
    private By loginButton = By.id("login-button");
    private By errorMessage = By.cssSelector("[data-test='error']");
    
    // Constructor
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public void enterUsername(String username) {
        logger.info("Entering username: " + username);
        WebElement usernameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(usernameField));
        usernameElement.clear();
        usernameElement.sendKeys(username);
    }
    
    public void enterPassword(String password) {
        logger.info("Entering password");
        WebElement passwordElement = wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
        passwordElement.clear();
        passwordElement.sendKeys(password);
    }
    
    public void clickLoginButton() {
        logger.info("Clicking login button");
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginBtn.click();
    }
    
    public void login(String username, String password) {
        logger.info("Performing login with username: " + username);
        enterUsername(username);
        enterPassword(password);
        clickLoginButton();
    }
    
    public String getErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            String errorText = error.getText();
            logger.info("Error message displayed: " + errorText);
            return errorText;
        } catch (Exception e) {
            logger.warn("No error message found");
            return "";
        }
    }
    
    public boolean isErrorDisplayed() {
        try {
            return driver.findElement(errorMessage).isDisplayed();
        } catch (Exception e) {
            return false;
        }
    }
}
