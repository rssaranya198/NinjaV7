package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MacPage extends BasePage{


	public MacPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//button[@type='button']//i[@class='fa fa-heart']")
	WebElement add_wishlist;
	
	@FindBy(xpath="//div[@class='alert alert-success alert-dismissible']")
	WebElement message;
	
	public void AddtoWishlist()
	{
		add_wishlist.click();
	}
	
	public WebElement Message()
	{
		return message;
	}
}
