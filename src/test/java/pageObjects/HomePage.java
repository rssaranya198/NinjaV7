package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage extends BasePage{

	
	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//a[normalize-space()='Qafox.com']")
	WebElement content_HomePage;
	
	@FindBy(xpath="//a[@title='My Account']")
	WebElement link_MyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Login']")
	WebElement link_Login;	
	
	@FindBy(xpath="//a[normalize-space()='Laptops & Notebooks']")
	WebElement laptop_option_select;
	
	@FindBy(xpath="//a[normalize-space()='Show AllLaptops & Notebooks']")
	WebElement show_all;
	
	@FindBy(xpath="//a[normalize-space()='Desktops']")
	WebElement select_desktop;
	
	@FindBy(xpath="//a[normalize-space()='Mac (1)']")
	WebElement choose_item;
	
	@FindBy(xpath="//a[normalize-space()='Gift Certificates']")
	WebElement gift_certificate;
	
	public String confirmHomepage()
	{
		return content_HomePage.getText();
	}
	
	public void clickMyAccount()
	{
		link_MyAccount.click();
	}
	
	public void goToLogin()
	{
		link_Login.click();
	}
	
	public void LaptopOption()
	{
		laptop_option_select.click();
	}
	
	public void ShowallLaptops()
	{
		show_all.click();
	}
	
	public void SelectDesktopOption()
	{
		select_desktop.click();
	}
	
	public void Chooseitem()
	{
		choose_item.click();
	}
	
	public void clickGiftCertificate()
	{
		gift_certificate.click();
	}


}
