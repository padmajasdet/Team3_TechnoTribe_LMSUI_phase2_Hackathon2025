package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ElementUtil;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil util;
	
	private By username= By.xpath("//input[@id='username']");
	private By password=By.xpath("//input[@id='password']");
	private By dropDownArraow = By.xpath("//span[text()='Select the role']/..//following-sibling::div[contains(@class,'arrow-wrapper')]");
	private By loginbutton= By.xpath("//button[@id='login']");
	
	
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
	
	/**
	 * This method retrieves title of Login Page
	 * @return title in String
	 */
	public String getPageTitle() {
		return util.getPageTitle();
	}
	
	/**
	 * This method retrieves URL of Login Page
	 * @return title in String
	 */
	public String getPageURL() {
		return util.getPageURL();
	}

	/**
	 * This method performs login with valid credentials
	 * @param username
	 * @param password
	 * @param role
	 * @return HomePage
	 */
	public HomePage doLoginWithValidCredentials(String username, String password, String role) {
		
		util.doSendKeys(this.username, username);
		util.doSendKeys(this.password, password);

		util.doClick(dropDownArraow);
		By roleOption= By.xpath("//span[normalize-space()='"+role+"']");
		util.doClick(roleOption);

		util.doClick(loginbutton);
		return new HomePage(driver);
	}
	
	/**
	 * This method performs valid login through using mouse or keyboard click on Login button
	 * @param username
	 * @param password
	 * @param role
	 * @param keyboardOrMouseClick
	 * @return HomePage
	 */
	public HomePage doLoginWithKeyboardOrMouseClick(String username, String password, String role, String keyboardOrMouseClick) {
		
		util.doSendKeys(this.username, username);
		util.doSendKeys(this.password, password);

		util.doClick(dropDownArraow);
		By roleOption= By.xpath("//span[normalize-space()='"+role+"']");
		util.doClick(roleOption);

		if(keyboardOrMouseClick.trim().toLowerCase().equals("mouse")) {
			util.mouseclickUsingAction(loginbutton);
		}else { util.pressdownButtonUsingAction(loginbutton);}

		return new HomePage(driver);
	}
	
	
	
	
	
	
	
	
	
	
}
