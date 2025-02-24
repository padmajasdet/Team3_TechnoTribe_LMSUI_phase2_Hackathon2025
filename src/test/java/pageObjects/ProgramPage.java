package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
	public static String UpdatedProgramName;
	public static String UpdatedProgramDesc;
	public static String UpdatedProgramStatus;

	private String filePath; // Excel file location
	private String sheetName = "Program";

	Map<String, String> programData;

	/*
	 * @FindBy(xpath = "//button[@id='program']") WebElement menu_Program;
	 */

	/*
	 * @FindBy(xpath = "//*[contains(text(),'Manage Program')]") WebElement
	 * programPageTitle;
	

	@FindBy(xpath = "//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']")
	WebElement headerBar;
 */
	/*
	@FindAll(value = { @FindBy(xpath = "//table/tbody//tr") })
	List<WebElement> programResults;
	
	
	@FindBy(id = "filterGlobal")
	WebElement searchBox;

	
	 * @FindBy(xpath = "//table/tbody") WebElement programTable;
	 */

	/*
	 * @FindBy(xpath =
	 * ".//table/thead/tr/th[1]/p-tableheadercheckbox/div/div[2]/span") WebElement
	 * checkBoxHeader;
	 */

	public ProgramPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
		util = new ElementUtil(this.driver);
		readConfig = new ReadConfig();
		filePath = readConfig.getExcelPath();
	}

	By programPageLMSHeading = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	By manageProgramTitle = By.xpath("//*[contains(text(),'Manage Program')]");
	By addNewProgramPopUp = By.xpath("//app-program/p-dialog/div/div");
	By addNewProgramTitle = By.xpath("//*[contains(text(),'Program Details')]");
	By btn_AddNewProgram = By.xpath("//button[text()='Add New Program']");
	By requiredFieldErrorMsgs = By.xpath("//small[@class='p-invalid ng-star-inserted']");
	By searchBar = By.xpath("//input[@id='filterGlobal']");

	By headerBar = By.xpath("//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']");
	By programResultsTable = By.xpath("//table/tbody//tr");
	By programNameInput = By.id("programName");
	By programDescInput = By.id("programDescription");
	By saveButton = By.id("saveProgram");
	By cancelButton = By.xpath("//button[@label='Cancel']");
	By xButton = By.xpath("//*[@header='Program Details']//button[@type='button']");
	By xDeleteButton = By.xpath("//*[contains(@class,' p-dialog-header-close p-link ng-star-inserted')]/..");

	By deleteButton = By.xpath("//div[@class='action']//button[@icon='pi pi-trash']");
	By deleteYesBtn = By.xpath("//span[normalize-space()='Yes']");
	By deleteNoBtn = By.xpath("//button/span[@class='p-button-label' and text()='No']");

	By successPopupTitle = By.xpath("//div[contains(@class,'p-toast-summary')]");
	By successPopupContent = By.xpath("//div[contains(@class,'p-toast-detail')]");

	By addNewProgramSuccessMsg = By.xpath("//div[@class='p-toast-message-content ng-tns-c20-13']");

	// Table
	By programNameHeading = By.xpath("//table/thead/tr/th[2]");
	By programDescHeading = By.xpath("//table/thead/tr/th[3]");
	By programStatusHeading = By.xpath("//table/thead/tr/th[4]");
	By deleteBtnManageProgram = By.xpath("//button[@class='p-button-danger p-button p-component p-button-icon-only']");
	By programTable = By.xpath("//table/tbody");
	By footerPrograms = By.xpath("//div[@class='p-d-flex p-ai-center p-jc-between ng-star-inserted']");

	//Tohfa
	By checkBoxHeader = By.xpath(".//table/thead/tr/th[1]/p-tableheadercheckbox/div/div[2]/span");
	By searchBox = By.id("filterGlobal");

	// Pagination
	By prevPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-prev')]");
	By firstPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-first')]");
	By nextPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-next')]");
	By lastPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-last')]");

	public String getProgramPageTitle() {
		//return util.getElementText(programPageTitle);
		return util.getElementText(manageProgramTitle);
	}

	public void isLogoutDisplayedMenuBar() {
		util.isElementDisplayed(menu_logout);
		//menu_logout.isDisplayed();
	}

	public String getLMSHeaderMenuBar() {

		return util.getElementText(programPageLMSHeading);

	}

	public String getAddNewProgramSubMenu() {

		return util.getElementText(btn_AddNewProgram);

	}

	public void menuBarDisplay() {
		
		WebElement headerBarEle = util.getElement(headerBar);
		//List<WebElement> buttons = headerBar.findElements(By.xpath("//div[@class='ng-star-inserted']//button"));
		List<WebElement> buttons = headerBarEle.findElements(By.xpath("//div[@class='ng-star-inserted']//button"));

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
			addNewProgramButton.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewProgramButton);

		}
	}

	public String getAddNewProgramPopUpTitle() {
		return util.getElementText(addNewProgramTitle);
	}

	public void verifyAddNewProgramPopUpDisplay() {
		util.isElementDisplayed(addNewProgramPopUp);
	}

	public static char getRandomCharacter() {
		Random random = new Random();
		return (char) ('b' + random.nextInt(26));
	}

	public void fillProgramForm(String testCase) {

		//programData = ExcelReader.getTestData(filePath, sheetName, testCase);
		programData = ExcelReader.getTestData(sheetName, testCase);

		System.out.println("Program data from excel --" + programData);

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

		// ExcelReader.updateTestData(filePath, sheetName, testCase, "ProgramName",
		// programName);
		util.doClick(saveButton);

		if (getToast().equalsIgnoreCase("Successful") && testCase.equalsIgnoreCase("validInputData")) {
			System.out.println("Program created successfully");
			System.out.println("Program Name: " + programName);
			/// Set the program name after creation
			setProgramName(programName);

		} else {
			System.out.println("Program creation failed");
		}

	}

	public void clickSaveBtn() {
		util.doClick(saveButton);
	}

	public static void setProgramName(String programName) {
		ProgramPage.NewProgramName = programName; // Store the program name in the static variable
	}

	public static String getProgramName() {
		return NewProgramName;
	}

	public void editTheProgramAndClickSave(String newProgram, String testCase) throws InterruptedException {

		newProgram = getProgramName();
		search(newProgram);
		clickEditProgramBtn(newProgram);

		Assert.assertEquals(getAddNewProgramPopUpTitle(), "Program Details");

		//programData = ExcelReader.getTestData(filePath, sheetName, testCase);
		programData = ExcelReader.getTestData(sheetName, testCase);

		String programNameEdit = programData.get("ProgramName");
		String programDescEdit = programData.get("ProgramDescription");
		String programStatusEdit = programData.get("ProgramStatus");

		System.out.println("Program name to update from excel >>" + programNameEdit);

		programNameEdit = programNameEdit + getRandomCharacter();
		util.mouseclickUsingAction(programNameInput);
		util.clearField(programNameInput);
		util.doSendKeys(programNameInput, programNameEdit);

		util.mouseclickUsingAction(programDescInput);
		util.clearField(programDescInput);
		util.doSendKeys(programDescInput, programDescEdit);

		By statusRadioBtn = By.xpath("//input[@id='" + programStatusEdit + "']");
		util.clickElementByJS(statusRadioBtn, driver);

		util.doClick(saveButton);

		if (getToast().equalsIgnoreCase("Successful") && testCase.equalsIgnoreCase("validInputEditData")) {
			System.out.println("Program updated successfully");
			System.out.println("Updated Program Name: " + programNameEdit);
			setUpdatedProgramName(programNameEdit);
			setUpdatedProgramDesc(programDescEdit);
			setUpdatedProgramStatus(programStatusEdit);

		} else {
			System.out.println("Program update failed");
		}

	}

	public void deleteTheProgramAndClickSave(String newProgram, String testCase) throws InterruptedException {

		// programData = ExcelReader.getTestData(filePath, sheetName, testCase);
		newProgram = getProgramName();
		// String programNameDelete = programData.get("ProgramName");
		search(newProgram);
		clickDeleteProgramBtn(newProgram);
		util.isElementDisplayed(deleteConfirmationPopUp);

		util.doClick(deleteYesBtn);

		if (getToast().equalsIgnoreCase("Successful")) {
			System.out.println("Program deleted successfully");
			System.out.println("Deleted Program Name: " + newProgram);

		} else {
			System.out.println("Program deletion failed");
		}

	}

	public static void setUpdatedProgramName(String updatedProgramName) {
		ProgramPage.UpdatedProgramName = updatedProgramName; // Store the program name in the static variable
	}

	public static String getUpdatedProgramName() {
		return UpdatedProgramName;
	}

	public static void setUpdatedProgramDesc(String updatedProgramDesc) {
		ProgramPage.UpdatedProgramDesc = updatedProgramDesc; // Store the program desc in the static variable
	}

	public static void setUpdatedProgramStatus(String updatedProgramStatus) {
		ProgramPage.UpdatedProgramStatus = updatedProgramStatus; // Store the program status in the static variable
	}

	public static String getUpdatedProgramDescS() {
		return UpdatedProgramDesc;
	}

	public static String getUpdatedProgramStatus() {
		return UpdatedProgramStatus;
	}

	public WebElement getProgramRowElement(String programName) {
		WebElement ele = util.getElement(programTable);
		// return programTable.findElement(By.xpath("//tr/td[contains(text(),'" +
		// programName + "')]/.."));
		return ele.findElement(By.xpath("//tr/td[contains(text(),'" + programName + "')]/.."));

	}

	public void clickEditProgramBtn(String programName) {
		WebElement editButton = getProgramRowElement(programName).findElement(By.id("editProgram"));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", editButton);

	}

	public void clickDeleteProgramBtn(String programName) {

		programName = getProgramName();
		System.out.println("Program to be deleted >>>" + programName);
		WebElement deleteProgBtn = getProgramRowElement(programName).findElement(deleteButton);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteProgBtn);

	}

	public void clickCancelProgramBtn() {
		util.doClick(cancelButton);
	}

	public void clickYesDeleteBtn() throws InterruptedException {

		Thread.sleep(500);
		util.clickElementByJS(deleteYesBtn, driver);

		System.out.println("Clicked on Yes...");

	}

	public void clickNoDeleteBtn() {

		util.clickElementByJS(deleteNoBtn, driver);

	}

	public boolean verifySuccessMessage(String message) {

		util.isElementDisplayed(successPopupTitle);

		util.isElementDisplayed(successPopupContent);

		String content = util.getElementText(successPopupContent);
		System.out.println("Message >>>>" + content);

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

		//searchBox.clear();
		util.getElement(searchBox).clear();		
		util.doClick(searchBox);
		//System.out.println("Program to search>>" + getProgramName());
		//searchBox.sendKeys(getProgramName());
		util.doSendKeys(searchBox, getProgramName());

	}

	public void searchForEditDeleteProgram(String newProgram) {
		//searchBox.clear();
		util.getElement(searchBox).clear();	
		util.doClick(searchBox);
		//searchBox.sendKeys(newProgram);
		util.doSendKeys(searchBox, newProgram);
	}

	public void searchUpdatedProgram(String updatedProgram) {

		//searchBox.clear();
		util.getElement(searchBox).clear();
		util.doClick(searchBox);
		//System.out.println("Program to search>>" + getProgramName());
		//searchBox.sendKeys(getUpdatedProgramName());
		util.doSendKeys(searchBox, getUpdatedProgramName());

	}

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

	public void verifyUpdatedProgramDetails(String updatedProgram, String updatedProgramDesc, String updatedStatus) {
		updatedProgram = getUpdatedProgramName();
		updatedProgramDesc = getUpdatedProgramDescS();
		updatedStatus = getUpdatedProgramStatus();

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.presenceOfElementLocated(By.xpath("//td[contains(text(),'" + updatedProgram + "')]")));

		WebElement resultName = driver.findElement(By.xpath("//td[contains(text(),'" + updatedProgram + "')]"));
		WebElement resultDesc = driver.findElement(By.xpath("//td[contains(text(),'" + updatedProgramDesc + "')]"));
		WebElement resultStatus = driver.findElement(By.xpath("//td[contains(text(),'" + updatedStatus + "')]"));

		String resultNameText = resultName.getText();
		String resultDescText = resultDesc.getText();
		String resultStatusText = resultStatus.getText();
		System.out.println("Search result Name >>" + resultNameText);
		System.out.println("Search result Desc >>" + resultDescText);
		Assert.assertEquals(resultNameText, updatedProgram, "Searched Program Name does not match the result!");
		Assert.assertEquals(resultDescText, updatedProgramDesc, "Searched Program Desc does not match the result!");
		Assert.assertEquals(resultStatusText, updatedStatus, "Searched Program Status does not match the result!");
		System.out.println("Search result validation passed: ");

	}

	public void verifySearchBarManageProgram(String searchBarText) {

		//searchBox.isDisplayed();
		util.isElementDisplayed(searchBox);
		System.out.println("Search bar text -" + util.getAttributeVal(searchBar, "placeholder"));
		Assert.assertEquals(util.getAttributeVal(searchBar, "placeholder"), searchBarText);

	}

	public boolean verifyCheckBoxUnchecked() {
//		if (!checkBoxHeader.isSelected()) {
		if (!util.getElement(checkBoxHeader).isSelected()) {
			System.out.println("Checkbox is unchecked");

			return true;
		} else {
			System.out.println("Checkbox is checked");
			return false;
		}

	}

	public void verifyFooterOfManageProgram() {

		WebElement paginationInfo = driver
				.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']"));
		String text = paginationInfo.getText(); // "Showing 1 to 10 of 50 entries"

		// Split the text and get the second last word (the total count)
		String[] words = text.split(" ");
		String totalEntries = words[words.length - 2];

		int totalPrograms = Integer.parseInt(totalEntries);

		String actualFooterText = util.getElementText(footerPrograms);
		String expectedText = "In total there are " + totalPrograms + " programs.";

		Assert.assertEquals(actualFooterText, expectedText);

	}

	public void clickOnNextPage() {
		util.clickElementByJS(nextPaginatorBtn, driver);

	}

	public boolean nextPageEnabled() {
		return util.isElementEnabled(nextPaginatorBtn);

	}

	public void clickOnLastPage() {
		util.clickElementByJS(lastPaginatorBtn, driver);

	}

	public boolean verifyNextPageBtnDisabled() {

		if (!util.isElementEnabled(nextPaginatorBtn)) {
			return true;
		}
		return false;
	}

	public void clickOnPreviuosPage() {
		util.clickElementByJS(prevPaginatorBtn, driver);

	}

	public boolean previousPageEnabled() {
		return util.isElementEnabled(prevPaginatorBtn);

	}

	public void clickOnFirstPage() {
		util.clickElementByJS(firstPaginatorBtn, driver);

	}

	public boolean verifyPreviousPageBtnDisabled() {

		if (!util.isElementEnabled(prevPaginatorBtn)) {
			return true;
		}
		return false;
	}

	public void verifyRequiredFieldErrorMessage() {

		String expProgNameErrorMsg = "Program name is required.";
		String expProgDescErrorMsg = "Description is required.";
		String expStatusErrorMsg = "Status is required.";

		List<WebElement> actualMsgs = driver.findElements(requiredFieldErrorMsgs);
		String progNameErrorMsg = actualMsgs.get(0).getText();
		String progDescErrorMsg = actualMsgs.get(1).getText();
		String statusErrorMsg = actualMsgs.get(2).getText();

		Assert.assertEquals(progNameErrorMsg, expProgNameErrorMsg);
		Assert.assertEquals(progDescErrorMsg, expProgDescErrorMsg);
		Assert.assertEquals(statusErrorMsg, expStatusErrorMsg);

	}

	public void clickXProgramBtn() {
		util.doClick(xButton);

	}

	public void clickXDeleteConfirmationBtn() {
		util.doClick(xDeleteButton);

	}

	public boolean verifyZeroSearchResultsAfterDeletion() throws InterruptedException {

		Thread.sleep(500);
		List<WebElement> programResults = driver.findElements(programResultsTable);
		System.out.println("Results list size ---" + programResults.size());
		if (programResults.size() < 1)
			return true;

		return false;
	}

}
