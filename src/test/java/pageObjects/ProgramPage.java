package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ElementUtil;
import utilities.ExcelReader;
import utilities.ReadConfig;

public class ProgramPage extends CommonPage {

	private WebDriver driver;
	private ElementUtil util;
	ReadConfig readConfig;

	public static String NewProgramName;

	private String filePath; // Excel file location
	private String sheetName = "Program"; // Sheet name";

	Map<String, String> programData;

	@FindBy(xpath = "//button[@id='program']")
	WebElement menu_Program;

	@FindBy(xpath = "//*[contains(text(),'Manage Program')]")
	WebElement programPageTitle;

	@FindBy(xpath = "//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']")
	WebElement headerBar;

	@FindBy(id = "filterGlobal")
	WebElement searchBox;

	@FindBy(xpath = "//table/tbody")
	WebElement programTable;

	// @FindBy(xpath = "//div[contains(@class,'p-toast-summary')]")WebElement
	// successPopupTitle;
	// @FindBy(xpath = "//div[contains(@class,'p-toast-detail')]")WebElement
	// successPopupContent;

	public ProgramPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new ElementUtil(this.driver);
		this.readConfig = new ReadConfig();
		this.filePath = readConfig.getExcelPath();
	}

	By programPageLMSHeading = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	By manageProgramTitle = By.xpath("//*[contains(text(),'Manage Program')]");
	By btn_AddNewProgram = By.xpath("//button[text()='Add New Program']");
	By programNameInput = By.id("programName");
	By programDescInput = By.id("programDescription");
	By saveButton = By.id("saveProgram");
	By successPopupTitle = By.xpath("//div[contains(@class,'p-toast-summary')]");
	By successPopupContent = By.xpath("//div[contains(@class,'p-toast-detail')]");

	By addNewProgramSuccessMsg = By.xpath("//div[@class='p-toast-message-content ng-tns-c20-13']");
	private By toastMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Successful']");

	By programNameHeading = By.xpath("//table/thead/tr/th[2]");
	By programDescHeading = By.xpath("//table/thead/tr/th[3]");
	By programStatusHeading = By.xpath("//table/thead/tr/th[4]");
	By deleteBtnManageProgram = By.xpath("//button[@class='p-button-danger p-button p-component p-button-icon-only']");
	// By programTable = By.xpath("//table/tbody");
	//

	public String getProgramPageTitle() {
		return util.getElementText(programPageTitle);
	}

	public void isLogoutDisplayedMenuBar() {
		logout.isDisplayed();
	}

	public String getLMSHeaderMenuBar() {

		return util.getElementText(programPageLMSHeading);

	}

	public void menuBarDisplay() {

		List<WebElement> buttons = headerBar.findElements(By.xpath("//div[@class='ng-star-inserted']//button"));
		Assert.assertTrue(buttons.size() > 0, "Menu headers are not present in the navigation bar");
		for (WebElement button : buttons) {
			String buttonText = button.getText().trim();
			Assert.assertFalse(buttonText.isEmpty(), "Navigation button text is missing for one of the buttons.");
			System.out.println("Button text: " + buttonText);
		}
	}

	public void clickAddNewProgramBtn() {
		WebElement addNewProgramButton = driver.findElement(btn_AddNewProgram);
		try {
			addNewProgramButton.click(); // Try regular click
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewProgramButton); // Fallback JS
																										// click
		}
	}

	public static char getRandomCharacter() {
		Random random = new Random();
		return (char) ('b' + random.nextInt(26)); // Generates a random lowercase letter
	}

	public void fillProgramForm(String testCase) {

		programData = ExcelReader.getTestData(filePath, sheetName, testCase);

		System.out.println("Program data from excel --");

		String programName = programData.get("ProgramName");
		String programDesc = programData.get("ProgramDescription");
		String status = programData.get("ProgramStatus");

		programName = programName + getRandomCharacter();
		System.out.println("Program Name Input :" + programName);

		if (programName != null && !programName.isEmpty()) {
			util.doSendKeys(programNameInput, programName);
		} else {
			System.out.println("Program Name is missing or empty");
		}

		if (programDesc != null && !programDesc.isEmpty()) {
			util.doSendKeys(programDescInput, programDesc);

		} else {
			System.out.println("Program Description is missing or empty");
		}

		By statusRadioBtn = By.xpath("//input[@id='" + status + "']");
		util.clickElementByJS(statusRadioBtn, driver);

		ExcelReader.updateTestData(filePath, sheetName, testCase, "ProgramName", programName);
		util.doClick(saveButton);

		if (getToast().equalsIgnoreCase("Successful") && testCase.equalsIgnoreCase("validInputData")) {
			System.out.println("Program created successfully");
			System.out.println("Program Name: " + programName);
			/// Set the program name after creation
			setProgramName(programName);

			System.out.println("Program name from get >>>" + getProgramName());

		} else {
			System.out.println("Program creation failed");
		}

	}

	public static void setProgramName(String programName) {
		ProgramPage.NewProgramName = programName; // Store the batch name in the static variable
	}

	public static String getProgramName() {
		return NewProgramName;
	}

	public String getToast() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(toastMessage)));
		String toastMessageValue = driver.findElement(toastMessage).getText();
		return toastMessageValue;
	}

	public void editTheProgramAndClickSave(String newProgram, String testCase) {

		newProgram = getProgramName();
		search(newProgram);
		clickEditProgram(newProgram);

	}

	public WebElement getProgramRowElement(String programName) {
		// util.getElement(programTable);
		return programTable.findElement(By.xpath("//tr/td[contains(text(),'" + programName + "')]/.."));

	}

	public void clickEditProgram(String programName) {
		WebElement editButton = getProgramRowElement(programName).findElement(By.id("editProgram"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);

	}

	public boolean verifySuccessMessage(String message) {

		util.isElementDisplayed(successPopupTitle);

		util.isElementDisplayed(successPopupContent);

		String content = util.getElementText(successPopupContent);

		if (content.equals(message)) {
			return true;
		}
		return false;
	}

	public String getManageProgramText() {
		return util.getElementText(manageProgramTitle);
	}

	public boolean verifyColumnHeader() {

		if (util.getElementText(programNameHeading).contains("Program Name")
				&& (util.getElementText(programDescHeading).contains("Program Description"))
				&& (util.getElementText(programStatusHeading).contains("Program Status"))) {
			return true;
		}
		return false;

	}

	public boolean verifyDeleteBtnDisabled() {

		if (!util.isElementEnabled(deleteBtnManageProgram)) {
			return true;
		}
		return false;
	}

	public void search(String newProgram) {

		searchBox.clear();
		util.doClick(searchBox);
		System.out.println("Program to search>>" + getProgramName());
		searchBox.sendKeys(getProgramName());

	}

	/*
	 * public void search() {
	 * 
	 * searchBox.clear(); util.doClick(searchBox);
	 * System.out.println(">>>>Fetched new program name -" + util.getProgramName());
	 * searchBox.sendKeys(this.progName); //
	 * searchBox.sendKeys(util.getProgramName());
	 * 
	 * }
	 */

	public void verifySearchResultProgramName(String newProgram) {
		newProgram = getProgramName();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'" + newProgram + "')]")));

		WebElement result = driver.findElement(By.xpath("//td[contains(text(),'" + newProgram + "')]"));

		String resultText = result.getText();
		System.out.println("Search result >>" + resultText);
		Assert.assertEquals(resultText, newProgram, "Searched Program Name does not match the result!");
		System.out.println("Search result validation passed: " + resultText);

	}

}
