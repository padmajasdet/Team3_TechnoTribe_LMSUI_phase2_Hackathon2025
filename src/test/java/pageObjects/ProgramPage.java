package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import utilities.ElementUtil;

public class ProgramPage extends CommonPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	//To be removed FindBys
	@FindBy(xpath="//button[@id='program']") 
	 WebElement menu_Program;
	
	@FindBy (xpath="//*[contains(text(),'Manage Program')]")
	WebElement programPageTitle ;
	
	private By programPageLMSHeading = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	
	
	public ProgramPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new ElementUtil(this.driver);
	}

	
	public String getProgramPageTitle() {
		return util.getElementText(programPageTitle);
	}
	
	public void isLogoutDisplayedMenuBar() {
		logout.isDisplayed();
	}
	
	public String getLMSHeaderMenuBar() {
		
			return util.getElementText(programPageLMSHeading);
		
	}
	
	
	
	
	
	
	
	
	
	
	
}
