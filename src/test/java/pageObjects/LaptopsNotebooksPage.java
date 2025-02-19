package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LaptopsNotebooksPage extends BasePage {

	public LaptopsNotebooksPage(WebDriver driver)
	{
		super(driver);
	}
		
	@FindBy(xpath="//a[normalize-space()='HP LP3065']")
	WebElement select_laptop;
	
	public void SelectLaptop()
	{
		select_laptop.click();
	}
}
