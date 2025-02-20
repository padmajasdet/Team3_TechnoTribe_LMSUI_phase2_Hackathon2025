package pageObjects;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ElementUtil;

public class ProgramPage extends CommonPage {
	
	private WebDriver driver;
	private ElementUtil util;
	Map<String, String> programdata;
	
	//To be removed FindBys
	@FindBy(xpath="//button[@id='program']") 
	 WebElement menu_Program;
	
	@FindBy (xpath="//*[contains(text(),'Manage Program')]")
	WebElement programPageTitle ;
	
	/*
	 * @FindBy (id ="Active") WebElement statusActiveRadioBtn;
	 * 
	 * @FindBy (id ="Inactive") WebElement statusInactiveRadioBtn;
	 */
	
	 
	public ProgramPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new ElementUtil(this.driver);
	}

	private By programPageLMSHeading = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	private By btn_AddNewProgram = By.xpath("//button[text()='Add New Program']");
	private By programNameInput = By.id("programName");
	private By programDescInput = By.id("programDescription");
	private By saveButton = By.id("saveProgram");
	
	
	
	public String getProgramPageTitle() {
		return util.getElementText(programPageTitle);
	}
	
	public void isLogoutDisplayedMenuBar() {
		logout.isDisplayed();
	}
	
	public String getLMSHeaderMenuBar() {
		
			return util.getElementText(programPageLMSHeading);
		
	}
	public void clickAddNewProgramBtn() {
	    WebElement addNewProgramButton = driver.findElement(btn_AddNewProgram);
	    try {
	        addNewProgramButton.click(); // Try regular click
	    } catch (Exception e) {
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewProgramButton); // Fallback JS click
	    }
	}
	
	/*
	 * public void fillProgramForm(String programName, String description, String
	 * status) { driver.findElement(programNameField).sendKeys(programName);
	 * driver.findElement(descriptionField).sendKeys(description);
	 * driver.findElement(statusActiveRadioBtn).sendKeys(status); }
	 */
	public void fillProgramForm(Map<String, String> programData) {
	    String programName = programData.get("ProgramName");
	    String programDesc = programData.get("ProgramDescription");
	    String status = programData.get("ProgramStatus");
	   	    
	    //WebElement programNameField = driver.findElement(programNameInput);
	    //WebElement programDescField = driver.findElement(programDescInput);
	   // WebElement activeStatusRadioBtn = driver.findElement(statusActiveRadioBtn);
	    //WebElement inactiveStatusRadioBtn = driver.findElement(statusInactiveRadioBtn);

	    if (programName != null && !programName.isEmpty()) {
	    	util.doSendKeys(programNameInput, programName);
	    	//programNameField.sendKeys(programName);
	    } else {
	        System.out.println("Program Name is missing or empty");
	    }

	    if (programDesc != null && !programDesc.isEmpty()) {
	    	util.doSendKeys(programDescInput, programDesc);

	    	//programDescField.sendKeys(programDesc);
	    } else {
	        System.out.println("Program Description is missing or empty");
	    }

		By statusRadioBtn = By.xpath("//input[@id='"+status+"']");
		util.clickElementByJS(statusRadioBtn, driver);
		
		util.doClick(saveButton);

	}

		
	
	
	
	
	
	
	
	
	
	
}
