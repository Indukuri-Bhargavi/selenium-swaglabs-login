package testcases;

import org.testng.annotations.Test;
import base.BaseTest;
import helpers.DriverFactory;
import pages.CartPage;
import pages.CheckoutPage;
import pages.LoginPage;
import pages.ProductsPage;

public class E2EFlowTest extends BaseTest{

	 @Test
	    public void loginAddToCartCheckout() {
	        try {
	            // 1️⃣ Login
	            LoginPage login = new LoginPage(DriverFactory.getDriver());
	            login.login("standard_user", "secret_sauce");

	            Thread.sleep(5000);
	            // 2️⃣ Add to Cart
	            ProductsPage products = new ProductsPage(DriverFactory.getDriver());
	            products.addFirstProductToCart();
	            products.goToCart();

	            // 3️⃣ Checkout
	            CartPage cart = new CartPage(DriverFactory.getDriver());
	            cart.clickCheckout();

	            CheckoutPage checkout = new CheckoutPage(DriverFactory.getDriver());
	            checkout.enterDetails("Bhargavi", "Indukuri", "500001");
	            checkout.completePurchase();

	            // ✅ Optional: print success
	            System.out.println("E2E Flow completed successfully.");

	        } catch (Exception e) {
	            // Print exception for debugging
	            e.printStackTrace();

	            // Fail the test if any exception occurs
	            org.testng.Assert.fail("E2E Flow failed due to exception: " + e.getMessage());
	        }
	    }
}
