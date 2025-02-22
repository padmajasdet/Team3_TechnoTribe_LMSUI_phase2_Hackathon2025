package pageObjects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Keys;

import utilities.ElementUtil;
import utilities.ReadConfig;

public class LoginPage {

	private WebDriver driver;
	private ElementUtil util;
	private ReadConfig readConfig;

	private By signInText = By.xpath("//form/p");
	private By username = By.xpath("//input[@id='username']");
	private By password = By.xpath("//input[@id='password']");
	private By dropDownArraow = By
			.xpath("//span[text()='Select the role']/..//following-sibling::div[contains(@class,'arrow-wrapper')]");
	private By loginbutton = By.xpath("//button[@id='login']");

	// Element not available yet. Temporary Xpath
	private By errorMessage_nullUsername = By.xpath("//mat-error[normalize-space()='Please enter your user name']");
	private By errorMessage_nullPassword = By.xpath("//mat-error[normalize-space()='Please enter your password']");
	private By errorMessage_nullRole = By.xpath("//mat-error[@role='alert']");

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
		readConfig = new ReadConfig();
	}

	/**
	 * This method retrieves title of Login Page
	 * 
	 * @return title in String
	 */
	public String getPageTitle() {
		return util.getPageTitle();
	}

	/**
	 * This method retrieves URL of Login Page
	 * 
	 * @return title in String
	 */
	public String getPageURL() {
		return util.getPageURL();
	}

	/**
	 * This method performs login with valid credentials
	 * 
	 * @param username
	 * @param password
	 * @param role
	 * @return HomePage
	 */
	public Object doLoginWithValidCredentials(String username, String password, String role,
			String... keyboardOrMouseClick) {

		Object obj = null;

		// Credentials
		util.doSendKeys(this.username, username);
		util.doSendKeys(this.password, password);

		// Role
		util.doClick(dropDownArraow);

		if (role == null || role.isBlank()) {
			// press esc btn using Actions
			util.pressKey(driver, Keys.ESCAPE);
			util.doClick(this.username);
		} else {
			By roleOption = By.xpath("//span[normalize-space()='" + role + "']");
			util.doClick(roleOption);
		}

		// Clicking on Button option
		if (keyboardOrMouseClick.length == 0) {// if nothing is specified in 4th parameter
			// perform usual Selenium click
			util.doClick(loginbutton);
		} else {// perform mouse or keyboard click

			if (keyboardOrMouseClick[0].trim().toLowerCase().equals("mouse")) {
				util.mouseclickUsingAction(loginbutton);
			} else {
				util.pressdownButtonUsingAction(loginbutton);
			}
		}

		// Check what object to return
		if (username == null || username.isBlank()) {
			// throw error if either of credentials are empty or missing
			obj = util.getElementText(errorMessage_nullUsername);
		} else if (password == null || password.isBlank()) {
			obj = util.getElementText(errorMessage_nullPassword);
		}

		else if (role == null || role.isBlank()) {
			obj = util.getElementText(errorMessage_nullRole);
		}
		else { // No error. Happy path

			obj = new HomePage(driver);
		}
		return obj;

	}

	/**
	 * This method returns error msg text in String for all invalid credentials
	 * @param username
	 * @param password
	 * @param role
	 * @return
	 */
	public String loginWithInvalidCredentials(String username, String password, String role) {

		String errTextMsg = null;
		// Credentials
		util.doSendKeys(this.username, username);
		util.doSendKeys(this.password, password);

		// Role
		util.doClick(dropDownArraow);
		By roleOption = By.xpath("//span[normalize-space()='" + role + "']");
		util.doClick(roleOption);
		
		if(util.getElementSize(By.xpath("//*[text()='Invalid username and password Please try again']"))>0) {
			
			errTextMsg = util.getElementText(By.xpath("//*[text()='Invalid username and password Please try again']"));
		} else errTextMsg= "";
		
		return errTextMsg;
	}

	/**
	 * This method performs invalid login with empty credentials with optional role
	 * value
	 * 
	 * @param role
	 * @return
	 */
	public List<String> doLoginWithEmptyCredentials(String... role) {

		// Credentials
		util.doSendKeys(this.username, "");
		util.doSendKeys(this.password, "");

		// Role
		util.doClick(dropDownArraow);

		if (role.length == 0) {
			util.doClick(this.username);
			util.pressKey(driver, Keys.ESCAPE);
		} else {
			By roleOption = By.xpath("//span[normalize-space()='" + role[0] + "']");
			util.doClick(roleOption);
		}

		// Login Btn
		util.doClick(loginbutton);

		// Collect list of msgs
		List<String> errMsgTexts = new ArrayList<String>();
		List<WebElement> errMsgsList = util.getElements(By.tagName("mat-error"));
		errMsgsList.forEach(ele -> errMsgTexts.add(ele.getText().trim()));

		return errMsgTexts;
	}

	/**
	 * This method returns list of dropdown option texts in ArrayList
	 * 
	 * @return
	 */
	public List<String> getRoleOptions() {

		List<String> optionTexts = new ArrayList<String>();
		// Click on DropDown
		util.doClick(dropDownArraow);

		By optionBlock = By.xpath("//div[@role='listbox']");

		if (util.isElementDisplayed(optionBlock)) {

			// Create element for option area
			WebElement parentElement = util.getElement(optionBlock);

			// Create xpath for all options
			List<WebElement> dropdownOptions = parentElement.findElements(By.xpath(".//span"));

			dropdownOptions.forEach(ele -> optionTexts.add(ele.getText().trim()));
		}

		return optionTexts;
	}

	public String getSignInContent() {
		return util.getElementText(signInText);
	}
	
	public boolean isPasswordFieldPresent() {
		return util.isElementDisplayed(password);
	}
}
