package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CheckoutPage extends BasePage {
	public CheckoutPage(WebDriver driver)
	{
		super(driver);
	}
	
	//WebDriverWait wait;
	
	@FindBy(id = "button-payment-address")
	WebElement paymentAddressButton;

	@FindBy(id = "button-shipping-address")
	WebElement shippingAddressButton;

	@FindBy(id = "button-shipping-method")
	WebElement shippingMethodButton;

	@FindBy(name = "agree")
	WebElement termsCheckbox;

	@FindBy(id = "button-payment-method")
	WebElement paymentMethodButton;

	@FindBy(id = "button-confirm")
	WebElement confirmOrderButton;
	
	public void completeCheckout() {
		
//		paymentAddressButton.click();
//		shippingAddressButton.click();
//		shippingMethodButton.click();
//		termsCheckbox.click();
//		paymentMethodButton.click();
//		confirmOrderButton.click();;
		
		
		wait.until(ExpectedConditions.elementToBeClickable(paymentAddressButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(shippingAddressButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(shippingMethodButton)).click();
		wait.until(ExpectedConditions.elementToBeClickable(termsCheckbox)).click();
		wait.until(ExpectedConditions.elementToBeClickable(paymentMethodButton)).click();
		  
		wait.until(ExpectedConditions.jsReturnsValue("return document.readyState == 'complete'"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("loading-spinner")));

		if (!confirmOrderButton.isEnabled()) {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].click();", confirmOrderButton);
		} else {
			confirmOrderButton.click();
		}
	}

	}


