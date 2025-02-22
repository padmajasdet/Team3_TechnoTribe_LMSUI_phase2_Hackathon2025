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
	public static String UpdatedProgramName;
	public static String UpdatedProgramDesc;
	

	private String filePath; // Excel file location
	private String sheetName = "Program";

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
	
	@FindBy(xpath = ".//table/thead/tr/th[1]/p-tableheadercheckbox/div/div[2]/span")
	WebElement checkBoxHeader;

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
	By searchBar = By.xpath("//input[@id='filterGlobal']");
	
	By programNameInput = By.id("programName");
	By programDescInput = By.id("programDescription");
	By saveButton = By.id("saveProgram");
	By successPopupTitle = By.xpath("//div[contains(@class,'p-toast-summary')]");
	By successPopupContent = By.xpath("//div[contains(@class,'p-toast-detail')]");

	By addNewProgramSuccessMsg = By.xpath("//div[@class='p-toast-message-content ng-tns-c20-13']");
	

	By programNameHeading = By.xpath("//table/thead/tr/th[2]");
	By programDescHeading = By.xpath("//table/thead/tr/th[3]");
	By programStatusHeading = By.xpath("//table/thead/tr/th[4]");
	By deleteBtnManageProgram = By.xpath("//button[@class='p-button-danger p-button p-component p-button-icon-only']");
	// By programTable = By.xpath("//table/tbody");
	By footerPrograms = By.xpath("//div[@class='p-d-flex p-ai-center p-jc-between ng-star-inserted']");
	
	//Pagination
	
	By prevPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-prev')]");
	By firstPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-first')]");
	By nextPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-next')]");
	By lastPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-last')]");
	
	//button[contains(@class,'p-paginator-next')]"
	//span[@class='p-paginator-icon pi pi-angle-right']

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
			addNewProgramButton.click();
		} catch (Exception e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", addNewProgramButton); 
																										
		}
	}

	public static char getRandomCharacter() {
		Random random = new Random();
		return (char) ('b' + random.nextInt(26));
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


		} else {
			System.out.println("Program creation failed");
		}

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
		
		
		System.out.println("newProgram name -" +newProgram);
		System.out.println("testcase name -" +testCase);
		
		programData = ExcelReader.getTestData(filePath, sheetName, testCase);

		System.out.println("Program data from excel --" +programData);

		String programNameEdit = programData.get("ProgramName");
		String programDescEdit = programData.get("ProgramDescription");
		
		System.out.println("Program name to update from excel >>" +programNameEdit);
		
		programNameEdit = programNameEdit + getRandomCharacter();
		util.mouseclickUsingAction(programNameInput);
		util.clearField(programNameInput);
		util.doSendKeys(programNameInput, programNameEdit);
		
		util.mouseclickUsingAction(programDescInput);
		util.clearField(programDescInput);
		util.doSendKeys(programDescInput, programDescEdit);
		
		util.doClick(saveButton);
		
		if (getToast().equalsIgnoreCase("Successful") && testCase.equalsIgnoreCase("validInputEditData")) {
			System.out.println("Program updated successfully");
			System.out.println("Updated Program Name: " + programNameEdit);
			setUpdatedProgramName(programNameEdit);
			setUpdatedProgramDesc(programDescEdit);
			
		} else {
			System.out.println("Program update failed");
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

	public static String getUpdatedProgramDescS() {
		return UpdatedProgramDesc;
	}

	public WebElement getProgramRowElement(String programName) {
		// util.getElement(programTable);
		return programTable.findElement(By.xpath("//tr/td[contains(text(),'" + programName + "')]/.."));

	}

	public void clickEditProgramBtn(String programName) {
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
	public void searchUpdatedProgram(String updatedProgram) {

		searchBox.clear();
		util.doClick(searchBox);
		System.out.println("Program to search>>" + getProgramName());
		searchBox.sendKeys(getUpdatedProgramName());

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

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(
				ExpectedConditions.presenceOfElementLocated(By.xpath("//td[contains(text(),'" + updatedProgram + "')]")));

		WebElement resultName = driver.findElement(By.xpath("//td[contains(text(),'" + updatedProgram + "')]"));
		WebElement resultDesc = driver.findElement(By.xpath("//td[contains(text(),'" + updatedProgramDesc + "')]"));

		String resultNameText = resultName.getText();
		String resultDescText = resultDesc.getText();
		System.out.println("Search result Name >>" + resultNameText);
		System.out.println("Search result Desc >>" + resultDescText);
		Assert.assertEquals(resultNameText, updatedProgram, "Searched Program Name does not match the result!");
		Assert.assertEquals(resultDescText, updatedProgramDesc, "Searched Program Desc does not match the result!");
		System.out.println("Search result validation passed: ");

	}

	public void verifySearchBarManageProgram(String searchBarText) {

		searchBox.isDisplayed();
		System.out.println("Search bar text -"+util.getAttributeVal(searchBar, "placeholder"));
		Assert.assertEquals(util.getAttributeVal(searchBar, "placeholder"), searchBarText);
		
	}
	
	public boolean verifyCheckBoxUnchecked() {
		 if (!checkBoxHeader.isSelected()) {
             System.out.println("Checkbox is unchecked");
             
             return true;
         } else {
             System.out.println("Checkbox is checked");
             return false;
         }
		
	}

	
	public void verifyFooterOfManageProgram() {

		WebElement paginationInfo = driver.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']")); 
		String text = paginationInfo.getText();  //"Showing 1 to 10 of 50 entries"
		
		//Split the text and get the second last word (the total count)
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

}