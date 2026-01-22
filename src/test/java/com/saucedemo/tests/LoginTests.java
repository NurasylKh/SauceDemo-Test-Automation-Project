package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTests extends BaseTest {
    
    @Test(priority = 1, description = "Verify successful login with valid credentials")
    public void testValidLogin() {
        logStep("Test Case: Valid Login");
        logStep("Step 1: Initialize Login Page");
        LoginPage loginPage = new LoginPage(driver);
        
        logStep("Step 2: Enter valid username 'standard_user'");
        loginPage.enterUsername("standard_user");
        
        logStep("Step 3: Enter valid password 'secret_sauce'");
        loginPage.enterPassword("secret_sauce");
        
        logStep("Step 4: Click Login button");
        loginPage.clickLoginButton();
        
        logStep("Step 5: Verify successful navigation to Products page");
        ProductsPage productsPage = new ProductsPage(driver);
        boolean isProductsPageLoaded = productsPage.isPageLoaded();
        
        logger.info("Expected Result: User should be redirected to Products page");
        logger.info("Actual Result: Products page loaded = " + isProductsPageLoaded);
        
        Assert.assertTrue(isProductsPageLoaded, "Failed to login - Products page not loaded");
        logStep("Test Passed: User successfully logged in");
    }
    
    @Test(priority = 2, description = "Verify login fails with invalid credentials")
    public void testInvalidLogin() {
        logStep("Test Case: Invalid Login");
        logStep("Step 1: Initialize Login Page");
        LoginPage loginPage = new LoginPage(driver);
        
        logStep("Step 2: Enter invalid username 'invalid_user'");
        loginPage.enterUsername("invalid_user");
        
        logStep("Step 3: Enter invalid password 'wrong_password'");
        loginPage.enterPassword("wrong_password");
        
        logStep("Step 4: Click Login button");
        loginPage.clickLoginButton();
        
        logStep("Step 5: Verify error message is displayed");
        boolean isErrorDisplayed = loginPage.isErrorDisplayed();
        String errorMessage = loginPage.getErrorMessage();
        
        logger.info("Expected Result: Error message should be displayed");
        logger.info("Actual Result: Error displayed = " + isErrorDisplayed);
        logger.info("Error Message: " + errorMessage);
        
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed for invalid credentials");
        Assert.assertTrue(errorMessage.contains("Username and password do not match"), 
            "Incorrect error message displayed");
        logStep("Test Passed: Invalid login properly rejected with error message");
    }
    
    @Test(priority = 3, description = "Verify login fails with locked out user")
    public void testLockedOutUser() {
        logStep("Test Case: Locked Out User Login");
        logStep("Step 1: Initialize Login Page");
        LoginPage loginPage = new LoginPage(driver);
        
        logStep("Step 2: Enter locked out username 'locked_out_user'");
        loginPage.enterUsername("locked_out_user");
        
        logStep("Step 3: Enter valid password 'secret_sauce'");
        loginPage.enterPassword("secret_sauce");
        
        logStep("Step 4: Click Login button");
        loginPage.clickLoginButton();
        
        logStep("Step 5: Verify locked out error message is displayed");
        boolean isErrorDisplayed = loginPage.isErrorDisplayed();
        String errorMessage = loginPage.getErrorMessage();
        
        logger.info("Expected Result: Locked out error message should be displayed");
        logger.info("Actual Result: Error displayed = " + isErrorDisplayed);
        logger.info("Error Message: " + errorMessage);
        
        Assert.assertTrue(isErrorDisplayed, "Error message not displayed for locked out user");
        Assert.assertTrue(errorMessage.contains("Sorry, this user has been locked out"), 
            "Incorrect error message for locked out user");
        logStep("Test Passed: Locked out user properly prevented from logging in");
    }
}
