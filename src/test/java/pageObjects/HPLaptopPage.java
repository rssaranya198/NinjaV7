package pageObjects;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HPLaptopPage extends BasePage{

	public HPLaptopPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-option225']")
	WebElement input_date;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement add_cart;
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement success_msg;
	
	@FindBy(xpath="//span[@id='cart-total']")
	WebElement click_Cart;
	
	@FindBy(xpath="//strong[normalize-space()='Checkout']")
	WebElement click_Checkout;
	
	public void deliveryDate()
	{
		input_date.clear();
		LocalDate today= LocalDate.now();
		LocalDate delivery=today.plusDays(5);
		DateTimeFormatter f=DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String date = delivery.format(f);
		input_date.sendKeys(date);
	}
	
	public void AddtoCart()
	{
		add_cart.click();
	}
	
	public String SuccessMessage()
	{
		return success_msg.getText();
	}
	
	public void selectCart()
	{
		click_Cart.click();
	}
	
	public void selectCheckout()
	{
		click_Checkout.click();
	}
}
