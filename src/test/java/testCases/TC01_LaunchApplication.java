package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import pageObjects.HomePage;
import testBase.BaseClass;
import utilities.RetryAnalyzer;

public class TC01_LaunchApplication extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC01_LaunchApplication.class);

    @Test(groups = {"smoke", "regression"}, retryAnalyzer = RetryAnalyzer.class)
    void testLaunchApplication() {
        logger.info("Starting test: testLaunchApplication");

        HomePage hp = new HomePage(getDriver());
        try {
            logger.debug("Calling confirmHomepage method to get confirmation text...");
            String confirmation = hp.confirmHomepage();
            logger.debug("Received confirmation text: " + confirmation);
            
            logger.info("Asserting confirmation text...");
            Assert.assertEquals(confirmation, "Qafox.com", "HomePage confirmation failed!");
            logger.info("Test Passed: Homepage confirmation is correct.");
            
        } catch (AssertionError e) {
            logger.error("Assertion failed: " + e.getMessage(), e);
            throw e;  // Rethrow the error to ensure test failure
        } catch (Exception e) {
            logger.error("Test failed due to unexpected error: " + e.getMessage(), e);
            throw e;  // Rethrow the error to ensure test failure
        }
    }
}

