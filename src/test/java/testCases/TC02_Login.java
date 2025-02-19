package testCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import pageObjects.AccountPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import testBase.BaseClass;
import utilities.DataProviders;
import utilities.RetryAnalyzer;

public class TC02_Login extends BaseClass {

    private static final Logger logger = LogManager.getLogger(TC02_Login.class);

    @Test(groups = {"smoke", "regression", "datadriven"}, dataProvider = "LoginData", dataProviderClass = DataProviders.class, retryAnalyzer = RetryAnalyzer.class)
    void testLogin(String email, String pwd) {
        logger.info("Starting test: testLogin with email: " + email);

        HomePage hp = new HomePage(getDriver());
        try {
            logger.debug("Clicking on 'My Account'...");
            hp.clickMyAccount();
            
            logger.debug("Navigating to Login Page...");
            hp.goToLogin();
            
            LoginPage lp = new LoginPage(getDriver());
            logger.debug("Entering email and password...");
            lp.setEmail(email);
            lp.setPwd(pwd);
            lp.clickLogin();
            
            AccountPage ap = new AccountPage(getDriver());
            logger.debug("Checking if account confirmation is displayed...");
            boolean status = ap.getMyAccountConfirmation().isDisplayed();
            
            if (status) {
                logger.info("Account successfully logged in.");
                ap.clickMyAccountDropDown();
                ap.clickLogout();
                logger.debug("Logged out successfully.");
            } else {
                logger.error("Login failed. Account confirmation not displayed.");
            }

            try {
                logger.info("Asserting account login status...");
                Assert.assertTrue(status, "Login failed! Account confirmation not displayed.");
                logger.info("Test Passed: Login was successful.");
            } catch (AssertionError e) {
                logger.error("Assertion failed: " + e.getMessage(), e);
                throw e; // Rethrow the error to mark the test as failed
            }
        } catch (Exception e) {
            logger.error("Test failed due to an unexpected error: " + e.getMessage(), e);
            throw e; // Rethrow the error to mark the test as failed
        }
    }
}




	

