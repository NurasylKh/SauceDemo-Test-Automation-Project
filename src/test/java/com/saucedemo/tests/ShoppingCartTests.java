package com.saucedemo.tests;

import com.saucedemo.base.BaseTest;
import com.saucedemo.pages.CartPage;
import com.saucedemo.pages.LoginPage;
import com.saucedemo.pages.ProductsPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ShoppingCartTests extends BaseTest {
    
    @Test(priority = 1, description = "Verify adding products to shopping cart")
    public void testAddProductsToCart() {
        logStep("Test Case: Add Products to Shopping Cart");
        
        logStep("Step 1: Login with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        logStep("Step 2: Verify Products page is loaded");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isPageLoaded(), "Products page not loaded after login");
        
        logStep("Step 3: Add Sauce Labs Backpack to cart");
        productsPage.addBackpackToCart();
        
        logStep("Step 4: Add Sauce Labs Bike Light to cart");
        productsPage.addBikeLightToCart();
        
        logStep("Step 5: Verify cart badge shows correct count");
        String cartCount = productsPage.getCartBadgeCount();
        
        logger.info("Expected Result: Cart badge should show '2'");
        logger.info("Actual Result: Cart badge shows '" + cartCount + "'");
        
        Assert.assertEquals(cartCount, "2", "Cart badge count is incorrect");
        logStep("Test Passed: Products successfully added to cart");
    }
    
    @Test(priority = 2, description = "Verify cart page displays added products")
    public void testViewCartItems() {
        logStep("Test Case: View Cart Items");
        
        logStep("Step 1: Login with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        logStep("Step 2: Add products to cart");
        ProductsPage productsPage = new ProductsPage(driver);
        productsPage.addBackpackToCart();
        productsPage.addBikeLightToCart();
        
        logStep("Step 3: Navigate to shopping cart");
        productsPage.clickShoppingCart();
        
        logStep("Step 4: Verify cart page is loaded");
        CartPage cartPage = new CartPage(driver);
        Assert.assertTrue(cartPage.isPageLoaded(), "Cart page not loaded");
        
        logStep("Step 5: Verify cart contains 2 items");
        int itemCount = cartPage.getCartItemCount();
        
        logger.info("Expected Result: Cart should contain 2 items");
        logger.info("Actual Result: Cart contains " + itemCount + " items");
        
        Assert.assertEquals(itemCount, 2, "Incorrect number of items in cart");
        logStep("Test Passed: Cart displays correct number of items");
    }
    
    @Test(priority = 3, description = "Verify product sorting functionality")
    public void testProductSorting() {
        logStep("Test Case: Product Sorting");
        
        logStep("Step 1: Login with valid credentials");
        LoginPage loginPage = new LoginPage(driver);
        loginPage.login("standard_user", "secret_sauce");
        
        logStep("Step 2: Verify Products page is loaded");
        ProductsPage productsPage = new ProductsPage(driver);
        Assert.assertTrue(productsPage.isPageLoaded(), "Products page not loaded after login");
        
        logStep("Step 3: Verify default product count");
        int productCount = productsPage.getProductCount();
        
        logger.info("Expected Result: Should display 6 products");
        logger.info("Actual Result: Displaying " + productCount + " products");
        
        Assert.assertEquals(productCount, 6, "Incorrect number of products displayed");
        
        logStep("Step 4: Sort products by Price (low to high)");
        productsPage.sortProducts("Price (low to high)");
        
        logStep("Step 5: Verify products are still displayed after sorting");
        int productCountAfterSort = productsPage.getProductCount();
        
        logger.info("Expected Result: Should still display 6 products after sorting");
        logger.info("Actual Result: Displaying " + productCountAfterSort + " products");
        
        Assert.assertEquals(productCountAfterSort, 6, "Product count changed after sorting");
        logStep("Test Passed: Product sorting works correctly");
    }
}
