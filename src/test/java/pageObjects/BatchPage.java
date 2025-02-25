package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ElementUtil;
import utilities.ExcelReader;
import utilities.Log;
import utilities.ReadConfig;
import utilities.RunTimeData;

public class BatchPage extends CommonPage {

	private WebDriver driver;
	private ElementUtil util;
	ReadConfig readConfig;
	Map<String, String> testData;
	ExcelReader excelReader = new ExcelReader();
	private By homeMenu = By.xpath("//span[normalize-space()='Home']");
	private By batchMenu = By.xpath("//span[contains(text(),'Batch')]");
	private By batchTitle = By.xpath("//*[contains(text(),'Manage Batch')]");
	private By batchPageTitle = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	private By deleteBatchHeader = By.xpath("//div[@class='box']//button[@icon=\"pi pi-trash\"]");

	// Batch page - Table locators
	private By paginationBotton = By
			.xpath("//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']");
	private By programNameList = By.xpath("//tbody//td[6]");
	private By batchNameList = By.xpath("//tbody//td[2]");
	private By batchDescriptionList = By.xpath("//tbody//td[3]");
	private By batchStatusList = By.xpath("//tbody//td[4]");
	private By noOfClassesList = By.xpath("//tbody//td[5]");
	private By deleteTitle = By.xpath("//span[normalize-space()='Confirm']");
	private By deleteNoButton = By.xpath("//span[normalize-space()='No']");
	private By deleteYesButton = By.xpath("//span[normalize-space()='Yes']");
	private By searchBox = By.id("filterGlobal");

	// Batch Page - Add new batch locators
	private By addNewBatch = By.xpath("//button[normalize-space()='Add New Batch']");
	private By addNewBatchTitle = By.xpath("//*[contains(text(),'Batch Details')]");
	private By addBatchProgramNameDD = By.xpath("//div[@role='button']");
	private By programNameLocator = By.xpath("//input[@placeholder='Select a Program name']");
	private By addBatchFirstName = By.xpath("//input[@id='batchProg']");
	private By addBatchName = By.xpath("//*[@id='batchName'][1]");
	private By editBatchName = By.xpath("//body//app-root//input[3]");
	private By addBatchDesc = By.id("batchDescription");
	private By addBatchStatus = By.xpath("//*[@class='radio ng-star-inserted']");
	private By activeStatus = By.xpath("(//p-radiobutton[@name='category']//div[@class='p-radiobutton-box'])[1]");
	private By inactiveStatus = By.xpath("(//p-radiobutton[@name='category']//div[@class='p-radiobutton-box'])[2]");
	private By addBatchNoOfClasses = By.id("batchNoOfClasses");
	private By saveButton = By.xpath("//button[@label='Save']");
	private By cancelButton = By.xpath("//button[@label='Cancel']");
	private By toastMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Successful']");
	private By invalidError = By.xpath("//small[@class='p-invalid ng-star-inserted']");
	private By closeButton = By.xpath("//*[@header='Batch Details']//button[@type='button']");

	// Batch Page - Pagination locators
	private By prevPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-prev')]");
	private By firstPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-first')]");
	private By thirdPaginatorBtn = By.xpath("//button[normalize-space()='3']");
	private By nextPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-next')]");
	private By lastPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-last')]");

	public static String BatchName;
	public static String BatchName1;
	private String sheetName = "Batch";
	private WebDriverWait wait;

	public BatchPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new ElementUtil(this.driver);
		readConfig = new ReadConfig();
	}

	/**
	 * This method retrieves title of Batch Page
	 * 
	 * @return title in String
	 */
	public String getManageBatchText() {
		return util.getElementText(batchTitle);
	}

	public String getBatchPageTitle() {
		return util.getElementText(batchPageTitle);
	}

	public String getAddNewPageText() {
		return util.getElementText(addNewBatch);
	}

	public String getAddNewPageTitle() {
		return util.getElementText(addNewBatchTitle);
	}

	public void addBatchClick() {
		util.doClick(addNewBatch);
	}

	public void homeMenuClick() {
		util.doClick(homeMenu);
	}

	public void batchMenuClick() {
		util.doClick(batchMenu);
	}

	public void closeButtonClick() {
		util.doClick(closeButton);
	}

	public Boolean deleteBatchHeaderButton() {
		return util.isElementEnabled(deleteBatchHeader);
	}

	public Boolean isPaginationAvailable() {
		return util.isElementDisplayed(paginationBotton);
	}

	public boolean isAddBatchNameEnabled() {
		return util.isElementEnabled(addBatchName);
	}

	public boolean isEditBatchNameEnabled() {
		return util.isElementEnabled(editBatchName);
	}

	public Boolean isAddBatchDescEnabled() {
		return util.isElementEnabled(addBatchDesc);
	}

	public boolean isAddBatchNoOfClassesEnabled() {
		return util.isElementEnabled(addBatchNoOfClasses);
	}

	public boolean isAddBatchProgramNameEnabled() {
		return util.isElementEnabled(addBatchProgramNameDD);
	}

	public boolean isEditBatchProgramNameEnabled() {
		return util.isElementEnabled(programNameLocator);
	}

	public boolean isStatusRadioButtonsPresentAndEnabled() {
		List<WebElement> statusRadioButtons = driver.findElements(addBatchStatus);
		if (statusRadioButtons.isEmpty()) {
			return false;
		}
		for (WebElement radio : statusRadioButtons) {
			if (!radio.isEnabled()) {
				return false;
			}
		}
		return true;
	}

	public void verifyBatchPopupFieldsAreEnabled() {
		Assert.assertEquals(driver.findElement(addNewBatch).isDisplayed(), true);
		Assert.assertEquals(driver.findElement(addBatchName).isEnabled(), true);
		Assert.assertEquals(driver.findElement(addBatchDesc).isEnabled(), true);
		Assert.assertEquals(driver.findElement(addBatchNoOfClasses).isEnabled(), true);
		Assert.assertEquals(driver.findElement(addBatchProgramNameDD).isEnabled(), true);
		List<WebElement> statusRadioButtons = driver.findElements(addBatchStatus);
		Assert.assertEquals(statusRadioButtons.isEmpty(), false);
		for (WebElement radio : statusRadioButtons) {
			Assert.assertEquals(radio.isEnabled(), true);
		}
	}

	public void selectProgramNameDD() {
		util.doClick(addBatchProgramNameDD);
	}

	public void selectProgramNameListBox(String testcaseName) throws Exception {
		String excelProgramName = selectDataFromExcel(testcaseName, "ProgramName");
//		// Check if it should be replaced with the chain variable

		String existingProgram = null;
		if (excelProgramName.equalsIgnoreCase("chaining")) {

			existingProgram = (String) RunTimeData.getData("programNameEdit");

			Log.logInfo("ProgramName at run time received in line 193 in EditProgramPage = " + existingProgram);

			while (existingProgram == null) {
				Thread.sleep(1000);
				// Then fetch data again
				existingProgram = (String) RunTimeData.getData("programNameEdit");
			}
		}

		By optionList = By.xpath("//ul[@role='listbox']");
		if (util.isElementDisplayed(optionList)) {

			Log.logInfo("Option Section is visible");

			By option = By.xpath("//li[@role='option' and @aria-label='" + existingProgram + "']");
			if (util.getElementSize(option) == 1) {
				Log.logInfo("Option is found");

				util.doClick(option);
			}
	
		}

	}

	public String selectDataFromExcel(String testcaseName, String columnName) {
		testData = ExcelReader.getTestData(sheetName, testcaseName);
		return testData.get(columnName);
	}

	public void enterBatchNamePrefix() throws Exception {
		selectProgramNameDD();
		selectProgramNameListBox("invalidBatchNamePrefix");
		util.doSendKeys(addBatchFirstName, selectDataFromExcel("invalidBatchNamePrefix", "BatchNamePrefix"));
	}

	public Boolean isBatchNamePrefixEditable() {
		return util.isEditablefield(addBatchFirstName);

	}

	public void enterBatchNameSuffix(String nonNumeric) {
		util.doSendKeys(addBatchName, nonNumeric);
	}

	public String getBatchNamePrefix() {
		return driver.findElement(addBatchFirstName).getDomProperty("value");
	}

	public void getActiveStatusRadioButton() {
		WebElement activeStatusElement = driver.findElement(activeStatus);
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", activeStatusElement);
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", activeStatusElement);
	}

	public WebElement getInActiveStatusRadioButton() {
		return driver.findElement(inactiveStatus);
	}

	public void saveButtonClick() {
		util.doClick(saveButton);
	}

	public void cancelButtonClick() {
		util.doClick(cancelButton);
	}

	public void enterAllDetails(String saveCancel, String testcaseName) {
		testData = ExcelReader.getTestData(sheetName, testcaseName);
		selectProgramNameDD();
		try {
			selectProgramNameListBox(testcaseName);
		} catch (Exception e) {

		}
		// Get the current BatchName value and increment it
		String batchNameStr = testData.get("BatchName");
		String newBatchName = "";
		if (batchNameStr != null && !batchNameStr.trim().isEmpty()) {
			try {
				int batchNumber = Integer.parseInt(batchNameStr.split("\\.")[0]); // Convert to integer
				batchNumber++; // Increment only if it's not empty
				newBatchName = String.valueOf(batchNumber); // Assign incremented value
			} catch (NumberFormatException e) {
				newBatchName = batchNameStr; // If parsing fails, retain the original value
			}
		}
		// Only enter a value if newBatchName is not empty
		if (!newBatchName.isEmpty()) {
			util.doSendKeys(addBatchName, newBatchName);
			// Update the BatchName in Excel for the next run
			ExcelReader.updateTestData(sheetName, testcaseName, "BatchName", newBatchName);
		}
		util.doSendKeys(addBatchDesc, testData.get("Description"));
		getActiveStatusRadioButton();

		String noOfClassesStr = testData.get("NoOfClasses");

		if (noOfClassesStr == null || noOfClassesStr.trim().isEmpty()) {
			Log.logInfo("NoOfClasses is missing or empty. Skipping input.");
		} else {
			try {
				int noOfClasses = Integer.parseInt(noOfClassesStr.split("\\.")[0]);
				String newNoOfClasses = String.valueOf(noOfClasses);
				util.doSendKeys(addBatchNoOfClasses, newNoOfClasses);
			} catch (NumberFormatException e) {
				Log.logInfo("Invalid number format for NoOfClasses: " + noOfClassesStr);
			}
		}

		if (saveCancel.equalsIgnoreCase("Save")) {
			saveButtonClick();
			String toastMessage = getToast();
			if (!toastMessage.isEmpty()) { // If toast appears, process it
				if (toastMessage.equalsIgnoreCase("Successful")) {
					if (testcaseName.equalsIgnoreCase("validAll")) {
						Log.logInfo("Batch created successfully - chaining");
						String finalBatchName = (String) RunTimeData.getData("programNameEdit") + newBatchName;
						Log.logInfo("BatchName_All: " + finalBatchName);
						RunTimeData.setData("BatchName_All", finalBatchName);
					} else {

						Log.logInfo("Batch created successfully - " + toastMessage);
						String finalBatchName1 = (String) RunTimeData.getData("programNameEdit") + newBatchName;

						Log.logInfo("BatchName_Mandatory: " + finalBatchName1);
						RunTimeData.setData("BatchName_Mandatory", finalBatchName1);

					}
				} else {
					Log.logInfo("Unexpected Toast Message: " + toastMessage);
				}
			} else {
				// If no toast message appears, fetch the error message
				String errorMessage = getErrorMessage();
				if (!errorMessage.isEmpty()) {
					Log.logInfo("Error: " + errorMessage);
				} else {
					Log.logInfo("No toast or error message found.");
				}
			}

		} else {
			cancelButtonClick();
		}
	}



	public String getToast() {
		try {
			List<WebElement> toastElements = driver.findElements(toastMessage);

			if (!toastElements.isEmpty()) {
				return toastElements.get(0).getText();
			} else {
				return "";
			}
		} catch (Exception e) {
			return "";
		}
	}

	public String getErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(invalidError)));
		String err = driver.findElement(invalidError).getText();
		return err;
	}

	public void clickAction(String actionType) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		// Wait for the overlay to disappear
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cdk-overlay-backdrop")));
		WebElement actionIcon = null;
		if (actionType.equalsIgnoreCase("edit")) {
			actionIcon = wait
					.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@icon='pi pi-pencil']")));
		} else if (actionType.equalsIgnoreCase("delete")) {
			actionIcon = wait.until(ExpectedConditions
					.elementToBeClickable(By.xpath("//div[@class='action']//button[@icon='pi pi-trash']")));
		} else {
			throw new IllegalArgumentException("Invalid action type: " + actionType);
		}
		// Scroll into view and click the action icon
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionIcon);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionIcon);
	}

	public void editAllDetails(String saveCancel, String testcaseName) {
		testData = ExcelReader.getTestData(sheetName, testcaseName);
		String descriptionStr = testData.get("Description");
		String descFinal;
		if (descriptionStr.matches("\\d+(\\.\\d+)?")) { // Check if it's numeric
			int desc = Integer.parseInt(descriptionStr.split("\\.")[0]);
			descFinal = String.valueOf(desc);
		} else {
			descFinal = descriptionStr;
		}
		driver.findElement(addBatchDesc).clear();
		driver.findElement(addBatchDesc).sendKeys(descFinal);
		getActiveStatusRadioButton();
		String noOfClassesStr = testData.get("NoOfClasses");
		if (noOfClassesStr != null && !noOfClassesStr.trim().isEmpty()) {
			int noOfClasses = Integer.parseInt(noOfClassesStr.split("\\.")[0]);
			String newNoOfClasses = String.valueOf(noOfClasses);
			driver.findElement(addBatchNoOfClasses).clear();
			driver.findElement(addBatchNoOfClasses).sendKeys(newNoOfClasses);
		}
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cdk-overlay-backdrop")));

		if (saveCancel.equalsIgnoreCase("Save")) {
			// Wait for the overlay to disappear before clicking Save
			WebElement saveBtn = driver.findElement(saveButton);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", saveBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", saveBtn);

			if (getToast().equalsIgnoreCase("Successful")) {
				Log.logInfo("Batch updated successfully");
			} else {
				String errorMessage = getErrorMessage();
				if (!errorMessage.isEmpty()) {
					Log.logInfo("Error: " + errorMessage);
				} else {
					Log.logInfo("No toast or error message found.");
				}
			}

		} else {
			// Wait for the overlay to disappear before clicking Cancel
			WebElement CancelBtn = driver.findElement(cancelButton);
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", CancelBtn);
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", CancelBtn);
		}
	}

	public void clickDelete() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".cdk-overlay-backdrop")));
		WebElement deleteIcon = wait.until(ExpectedConditions
				.elementToBeClickable(By.xpath("//div[@class='action']//button[@icon='pi pi-trash']")));
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", deleteIcon);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteIcon);

	}

	public String getDeleteTitle() {
		return util.getElementText(deleteTitle);
	}

	public String getDeleteNoButton() {
		return util.getElementText(deleteNoButton);
	}

	public String getDeleteYesButton() {
		return util.getElementText(deleteYesButton);
	}

	public void isElementIntercepted() {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement overlay = driver.findElement(By.className("cdk-overlay-backdrop"));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));
		overlay.click();
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

	public boolean lastPageDisplayed() {
		return util.isElementDisplayed(lastPaginatorBtn);

	}

	public void clickOnFirstPage() {
		util.clickElementByJS(firstPaginatorBtn, driver);

	}

	public void clickOnPreviuosPage() {
		util.clickElementByJS(prevPaginatorBtn, driver);

	}

	public boolean verifyPreviousPageBtnDisabled() {

		if (!util.isElementEnabled(prevPaginatorBtn)) {
			return true;
		}
		return false;
	}

	public boolean previousPageEnabled() {
		return util.isElementEnabled(prevPaginatorBtn);

	}

	public void clickOnThirdPage() {
		util.clickElementByJS(thirdPaginatorBtn, driver);
	}

	public String firstPageValidation() {
		String pageEntryText = driver.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']"))
				.getText();
		return pageEntryText;
	}

	public static void setBatchName1(String batchName1) {
		BatchPage.BatchName1 = batchName1;
	}

	public static String getBatchName1() {
		return BatchName1;
	}

	public void enterSearch(String search) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.elementFromPoint(0, 0).click();");
		WebElement Searchtext = wait.until(ExpectedConditions.presenceOfElementLocated(searchBox));
		util.doClick(Searchtext);
		Searchtext.clear();
		Log.logInfo("Batch name is: " + search);
		Searchtext.sendKeys(search);
	}

	public boolean validateSearch() {
		boolean flag = false;
		WebElement dataTable = driver
				.findElement(By.xpath("//div[@class='p-d-flex p-ai-center p-jc-between ng-star-inserted']"));
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOf(dataTable));
		int retryCount = 0;
		while (retryCount < 3) {
			try {
				List<WebElement> batchRows = driver
						.findElements(By.xpath("//tbody[@class='p-datatable-tbody']/tr/td[2]"));
				if (batchRows.size() > 0) {
					for (WebElement row : batchRows) {
						String rowText = row.getText();
				Log.logInfo("search text is: " + (String)RunTimeData.getData("BatchName_All"));
						if (rowText.contains((String)RunTimeData.getData("BatchName_All"))) {
							flag = true;
							break;
						}
					}
				} else {
					Log.logInfo("No results found in the data table.");
				}
				break;
			} catch (StaleElementReferenceException e) {
				retryCount++;
				Log.logInfo("StaleElementReferenceException encountered. Retrying... " + retryCount);
			}
		}
		if (retryCount >= 3) {
			Log.logInfo("Failed to validate search due to stale elements after multiple retries.");
		}
		return flag;
	}

	public void columnNameSorting(String columnName, int noOfTimesClick) {
		isElementIntercepted();
		WebElement columnNameSort = driver.findElement(By.xpath("//th[normalize-space()='" + columnName
				+ "']//i[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']"));
		for (int i = 1; i <= noOfTimesClick; i++) {
			columnNameSort.click();
		}
	}

	// convert web element to java string list
	public List<String> printWebElements(List<WebElement> options) {
		List<String> texts = new ArrayList<String>();
		int i = 0;
		for (WebElement option : options) {
			texts.add(i, option.getText());
			i++;
		}
		return texts;
	}

	public List<String> getOriginalList(String type) {
		List<String> originalList = null;

		if (type.equals("Program Name")) {
			originalList = printWebElements(util.getElements(programNameList));

		} else if (type.equals("Batch Name")) {
			originalList = printWebElements(util.getElements(batchNameList));

		} else if (type.equals("Batch Description")) {
			originalList = printWebElements(util.getElements(batchDescriptionList));

		} else if (type.equals("Batch Status")) {
			originalList = printWebElements(util.getElements(batchStatusList));

		} else if (type.equals("No Of Classes")) {
			originalList = printWebElements(util.getElements(noOfClassesList));

		}
		return originalList;
	}

	public List<String> getSortedList(List<String> originalList) {
		List<String> sortedList = new ArrayList<>(originalList);
		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		return sortedList;
	}

}
