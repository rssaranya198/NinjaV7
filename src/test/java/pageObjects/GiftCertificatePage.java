package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GiftCertificatePage extends HomePage {
	
	public GiftCertificatePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//input[@id='input-to-name']")
	WebElement txt_ReceiverName;
	
	@FindBy(xpath="//input[@id='input-to-email']")
	WebElement txt_ReceiverEmail;
	
	@FindBy(xpath="//input[@id='input-from-name']")
	WebElement txt_Name;
	
	@FindBy(xpath="//input[@id='input-from-email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[@value='7']")
	WebElement select_theme;
	
	@FindBy(xpath="//textarea[@id='input-message']")
	WebElement txt_Message;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement select_agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement select_continue;
	
	@FindBy(xpath="//p[contains(text(),'Thank you for purchasing a gift certificate! Once ')]")
	WebElement success_message;
	
	public void setReceiverName(String rname)
	{
		txt_ReceiverName.sendKeys(rname);
	}
	
	public void setReceiverEmail(String remail)
	{
		txt_ReceiverEmail.sendKeys(remail);
	}
	
	public void setName(String name)
	{
		txt_Name.sendKeys(name);
	}
	
	public void setEmail(String email)
	{
		txt_Email.sendKeys(email);
	}
	
	
	public void clickTheme()
	{
		select_theme.click();
	}
	
	public void setMessage(String msg)
	{
		txt_Message.sendKeys(msg);
	}
	
	public void clickAgree()
	{
		select_agree.click();
	}
	
	public void clickContinue()
	{
		select_continue.click();
	}
	
	public WebElement Message()
	{
		return success_message;
	}
	
}
