package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.CheckoutPage;
import pageObjects.ConfirmationPage;
import pageObjects.HPLaptopPage;
import pageObjects.HomePage;
import pageObjects.LaptopsNotebooksPage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC05_CompletePurchase extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC05_CompletePurchase.class);

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testCompletePurchase() {
        logger.info("Starting the test: testCompletePurchase");

        try {
            // Navigate to Home Page and select Laptop Option
            HomePage hp = new HomePage(getDriver());
            logger.debug("Selecting Laptop option...");
            hp.LaptopOption();

            // Show all laptops
            logger.debug("Displaying all laptops...");
            hp.ShowallLaptops();

            // Select a laptop
            LaptopsNotebooksPage ln = new LaptopsNotebooksPage(getDriver());
            logger.debug("Selecting a laptop...");
            ln.SelectLaptop();

            // Go to HP Laptop Page, select delivery date, and add to cart
            HPLaptopPage hp1 = new HPLaptopPage(getDriver());
            logger.debug("Selecting delivery date...");
            hp1.deliveryDate();
            logger.debug("Adding to cart...");
            hp1.AddtoCart();
            
            // Proceed to cart and checkout
            logger.debug("Selecting cart...");
            hp1.selectCart();
            logger.debug("Proceeding to checkout...");
            hp1.selectCheckout();

            // Login Page actions
            LoginPage lp = new LoginPage(getDriver());
            logger.debug("Entering login credentials...");
            lp.setEmail("rssaranya198@gmail.com");
            lp.setPwd("Demo@123");
            lp.clickLogin();

            // Complete the checkout process
            CheckoutPage cp = new CheckoutPage(getDriver());
            logger.debug("Completing checkout...");
            cp.completeCheckout();

            // Verify order confirmation
            ConfirmationPage co = new ConfirmationPage(getDriver());
            logger.debug("Verifying order confirmation...");
            try {
                logger.info("Asserting order confirmation...");
                Assert.assertTrue(co.verifyOrderConfirmation(), "Order confirmation failed!");
                logger.info("Test Passed: Order completed successfully.");
            } catch (AssertionError e) {
                logger.error("Assertion failed: " + e.getMessage(), e);
                throw e; // Rethrow to mark the test as failed
            }
        } catch (Exception e) {
            logger.error("Test failed due to an unexpected error: " + e.getMessage(), e);
            throw e; // Rethrow the error to ensure the test is marked as failed
        }
    }
}

