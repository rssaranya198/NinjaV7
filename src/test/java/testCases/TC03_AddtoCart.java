package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.LaptopsNotebooksPage;
import testBase.BaseClass;
import pageObjects.HPLaptopPage;
import pageObjects.HomePage;
import utilities.RetryAnalyzer;

public class TC03_AddtoCart extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC03_AddtoCart.class);

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testAddCart() {
        logger.info("Starting test: testAddCart");

        try {
            // Navigate to Home Page and click Laptop Option
            HomePage hp = new HomePage(getDriver());
            logger.debug("Clicking on Laptop option...");
            hp.LaptopOption();
            
            // Show all laptops
            logger.debug("Showing all laptops...");
            hp.ShowallLaptops();

            // Select a laptop
            LaptopsNotebooksPage ln = new LaptopsNotebooksPage(getDriver());
            logger.debug("Selecting a laptop...");
            ln.SelectLaptop();

            // Go to HP Laptop Page
            HPLaptopPage hp1 = new HPLaptopPage(getDriver());
            logger.debug("Selecting delivery date...");
            hp1.deliveryDate();
            
            // Add laptop to cart
            logger.debug("Adding to cart...");
            hp1.AddtoCart();
            
            // Verify success message
            String status = hp1.SuccessMessage();
            logger.debug("Success message: " + status);
            
            try {
                // Assert the success message contains "Success"
                logger.info("Asserting the success message...");
                Assert.assertTrue(status.contains("Success"), "Add to cart failed!");
                logger.info("Test Passed: Item successfully added to cart.");
            } catch (AssertionError e) {
                logger.error("Assertion failed: " + e.getMessage(), e);
                throw e;  // Rethrow to ensure the test is marked as failed
            }
        } catch (Exception e) {
            logger.error("Test failed due to an unexpected error: " + e.getMessage(), e);
            throw e;  // Rethrow the error to ensure the test is marked as failed
        }
    }
}
