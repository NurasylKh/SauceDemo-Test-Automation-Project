package com.saucedemo.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class ProductsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static Logger logger = LogManager.getLogger(ProductsPage.class);
    
    // Locators
    private By pageTitle = By.className("title");
    private By productSortDropdown = By.className("product_sort_container");
    private By inventoryItems = By.className("inventory_item");
    private By addToCartBackpack = By.id("add-to-cart-sauce-labs-backpack");
    private By addToCartBikeLight = By.id("add-to-cart-sauce-labs-bike-light");
    private By shoppingCartBadge = By.className("shopping_cart_badge");
    private By shoppingCartLink = By.className("shopping_cart_link");
    private By menuButton = By.id("react-burger-menu-btn");
    private By logoutLink = By.id("logout_sidebar_link");
    
    // Constructor
    public ProductsPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    // Page Actions
    public boolean isPageLoaded() {
        try {
            WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
            boolean loaded = title.getText().equals("Products");
            logger.info("Products page loaded: " + loaded);
            return loaded;
        } catch (Exception e) {
            logger.error("Products page not loaded");
            return false;
        }
    }
    
    public String getPageTitle() {
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(pageTitle));
        String titleText = title.getText();
        logger.info("Page title: " + titleText);
        return titleText;
    }
    
    public void sortProducts(String option) {
        logger.info("Sorting products by: " + option);
        WebElement dropdown = wait.until(ExpectedConditions.visibilityOfElementLocated(productSortDropdown));
        Select select = new Select(dropdown);
        select.selectByVisibleText(option);
    }
    
    public void addBackpackToCart() {
        logger.info("Adding Sauce Labs Backpack to cart");
        WebElement backpackButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartBackpack));
        backpackButton.click();
    }
    
    public void addBikeLightToCart() {
        logger.info("Adding Sauce Labs Bike Light to cart");
        WebElement bikeLightButton = wait.until(ExpectedConditions.elementToBeClickable(addToCartBikeLight));
        bikeLightButton.click();
    }
    
    public String getCartBadgeCount() {
        try {
            WebElement badge = wait.until(ExpectedConditions.visibilityOfElementLocated(shoppingCartBadge));
            String count = badge.getText();
            logger.info("Cart badge count: " + count);
            return count;
        } catch (Exception e) {
            logger.warn("Cart badge not visible");
            return "0";
        }
    }
    
    public void clickShoppingCart() {
        logger.info("Clicking shopping cart");
        WebElement cartLink = wait.until(ExpectedConditions.elementToBeClickable(shoppingCartLink));
        cartLink.click();
    }
    
    public int getProductCount() {
        List<WebElement> products = driver.findElements(inventoryItems);
        int count = products.size();
        logger.info("Number of products displayed: " + count);
        return count;
    }
    
    public void logout() {
        logger.info("Performing logout");
        WebElement menu = wait.until(ExpectedConditions.elementToBeClickable(menuButton));
        menu.click();
        
        WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(logoutLink));
        logout.click();
        logger.info("Logout completed");
    }
}
