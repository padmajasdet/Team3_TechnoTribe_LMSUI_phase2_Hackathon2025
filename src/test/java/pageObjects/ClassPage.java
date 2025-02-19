package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import hooks.TestContext;

public class ClassPage {

	private WebDriver driver;
	private WebDriverWait wait;
	Actions action;

	TestContext context;
	JavascriptExecutor js = (JavascriptExecutor) driver;
	List<WebElement> manageProgramMenuItems = new ArrayList<>();

	public ClassPage(WebDriver driver, TestContext context) {

		this.driver = context.getDriver();

		this.context = context;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.action = new Actions(driver);
		PageFactory.initElements(driver, this);

	}

//login
	@FindBy(id = "username")
	private WebElement userName;
	@FindBy(id = "password")
	private WebElement password;

	@FindBy(xpath = "//span[text()='Select the role']")
	private WebElement selectRole;

	@FindBy(xpath = "//span[normalize-space()='Admin']")
	private WebElement Role;
	@FindBy(xpath = "//button[@id='login']")
	private WebElement Login;
	@FindBy(xpath = "//span[text()='Class']")
	private WebElement classBtn;

//Manage class page
	@FindBy(xpath = "//div[normalize-space()='Manage Class']")
	private WebElement ManageHeader;
	@FindBy(xpath = "//input[@id='filterGlobal']")
	private WebElement searchBox;
	@FindBy(xpath = "//thead[@class='p-datatable-thead']/tr")
	private WebElement ManageTable;
	@FindBy(xpath = "//p-table/div/p-paginator/div/span[1]")
	private WebElement paginationInfo;
	
	 
	

//login methods
	public void addUsername(String UName) {
		userName.sendKeys(UName);

	}

	public void addPassword(String Pword) {
		password.sendKeys(Pword);

	}

	public void selectRole() {
		selectRole.click();

	}

	public void role() {
		Role.click();
	}

	public void clickLogin() {
		Login.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
	}

	public void clickClassBtn() {

		classBtn.click();
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

	}

//manage class page methods
	public String getManageHeader() {
		return ManageHeader.getText();

	}

	public boolean validatesearchbox() {

		return searchBox.isDisplayed();

	}

	public List<String> validateManageTableHeader() {

		manageProgramMenuItems = ManageTable.findElements(By.tagName("th"));
		List<String> itemTextList = new ArrayList<>();
		for (WebElement item : manageProgramMenuItems) {
			String itemText = item.getText();
			itemTextList.add(itemText);
		}

		return itemTextList;

	}
	
	

}
