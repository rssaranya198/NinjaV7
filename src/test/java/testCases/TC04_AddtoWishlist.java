package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.MacPage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC04_AddtoWishlist extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC04_AddtoWishlist.class);

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testWishlist() {
        logger.info("Starting test: testWishlist");

        try {
            // Navigate to Home Page and select Desktop option
            HomePage hp = new HomePage(getDriver());
            logger.debug("Selecting Desktop option...");
            hp.SelectDesktopOption();
            
            // Choose an item
            logger.debug("Choosing an item...");
            hp.Chooseitem();

            // Navigate to Mac Page and add item to wishlist
            MacPage mp = new MacPage(getDriver());
            logger.debug("Adding item to wishlist...");
            mp.AddtoWishlist();
            
            // Verify success message
            boolean status = mp.Message().isDisplayed();
            logger.debug("Success message displayed: " + status);
            
            try {
                // Assert if the message is displayed correctly
                logger.info("Asserting wishlist success message...");
                Assert.assertTrue(status, "Add to Wishlist failed! Message not displayed.");
                logger.info("Test Passed: Item successfully added to wishlist.");
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

