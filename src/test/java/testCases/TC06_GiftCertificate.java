package testCases;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.GiftCertificatePage;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC06_GiftCertificate extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC06_GiftCertificate.class);

    @Test(groups = {"regression"}, retryAnalyzer = RetryAnalyzer.class)
    public void testGiftCertificate() {
        logger.info("Starting the test: testGiftCertificate");

        try {
            // Navigate to Gift Certificate page
            HomePage hp = new HomePage(getDriver());
            logger.debug("Clicking Gift Certificate link...");
            hp.clickGiftCertificate();

            // Fill out Gift Certificate form
            GiftCertificatePage gf = new GiftCertificatePage(getDriver());
            logger.debug("Filling out the form...");
            gf.setReceiverName("test");
            gf.setReceiverEmail("test@abc.com");
            gf.setName("Saranya");
            gf.setEmail("saran@abc.com");
            gf.clickTheme();
            gf.setMessage("Happy Birthday");
            gf.clickAgree();
            gf.clickContinue();

            // Verify that the success message is displayed
            boolean status = gf.Message().isDisplayed();
            logger.debug("Checking if the success message is displayed: " + status);

            // Assert if the message is displayed
            try {
                logger.info("Asserting that the success message is displayed...");
                Assert.assertTrue(status, "Gift Certificate was not successfully created!");
                logger.info("Test Passed: Gift Certificate was created successfully.");
            } catch (AssertionError e) {
                logger.error("Assertion failed: " + e.getMessage(), e);
                throw e; // Rethrow to mark the test as failed
            }
        } catch (Exception e) {
            logger.error("Test failed due to an unexpected error: " + e.getMessage(), e);
            throw e; // Rethrow to mark the test as failed
        }
    }
}

