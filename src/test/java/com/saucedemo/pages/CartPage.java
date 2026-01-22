package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(CartPage.class);
    
    // Locators
    private By pageTitle = By.className("title");
    private By cartItems = By.className("cart_item");
    private By checkoutButton = By.id("checkout");
    private By continueShoppingButton = By.id("continue-shopping");
    
    // Constructor
    public CartPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public boolean isPageLoaded() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean loaded = title.getText().equals("Your Cart");
            logger.info("Cart page loaded: " + loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Cart page not loaded");
            return false;
        }
    }
    
    public int getCartItemCount() {
        List<WebElement> items = driver.findElements(cartItems);
        int count = items.size();
        logger.info("Number of items in cart: " + count);
        return count;
    }
    
    public void clickCheckout() {
        logger.info("Clicking checkout button");
        WebElement checkout = wait.until(ExpectedConditions.elementToBeClickable(checkoutButton));
        checkout.click();
    }
    
    public void continueShopping() {
        logger.info("Clicking continue shopping button");
        WebElement continueBtn = wait.until(ExpectedConditions.elementToBeClickable(continueShoppingButton));
        continueBtn.click();
    }
}
