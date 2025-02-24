package pageObjects;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ElementUtil;

public class ClassPage extends CommonPage {

	private WebDriver driver;
	private WebDriverWait wait;
	Actions actions;

	ElementUtil elementUtil;
	JavascriptExecutor js;
	List<WebElement> manageProgramMenuItems = new ArrayList<>();

	private By classBtn = By.xpath("//span[text()='Class']");

	// Manage class page
	private By ManageHeader = By.xpath("//div[normalize-space()='Manage Class']");
	private By searchBox = By.xpath("//input[@id='filterGlobal']");
	private By ManageTable = By.xpath("//thead[@class='p-datatable-thead']/tr");
	private By paginationfooter = By.xpath("//p-table/div/div[2]/div");
	private By sortingBtn = By.className("p-sortable-column-icon");
	private By deleteBtnMC = By.cssSelector("button[class='p-button-danger p-button p-component p-button-icon-only']");
	private By showingEnteries = By.cssSelector(".p-paginator-current.ng-star-inserted");

	// Add new class
	 private By addNewClassBtn = By.xpath("//button[text()='Add New Class']");
	//private By addNewClassBtn = By.xpath("//button[@role='menuitem']");

	private By cancelBtn = By.xpath("//button[@label='Cancel']");
	private By saveBtn = By.xpath("//button[@label='Save']");
	private By crossBtn = By.cssSelector(".p-dialog-header-close");
	// private By footer = By.cssSelector(".p-datatable-footer.ng-star-inserted");
	// no usage
	private By batchNamePopup = By.xpath("//label[normalize-space()='Batch Name']");

	// Add new class page
	private By batchNameDrpdw = By
			.xpath("//label[text()='Batch Name']//following-sibling::p-dropdown//div[@role='button']");
	private By batchNameTextArea = By.xpath("//input[@placeholder='Select a Batch Name']");
	private By classTopicPopup = By.xpath("//label[text()='Class Topic ']");
	private By classTopicTextbox = By.xpath("//input[@id='classTopic']");
	private By managePage = By.xpath("//div[normalize-space()='Manage Class']");
	private By ClassDescription = By.xpath("//label[normalize-space()='Class Description']");
	private By ClassDescriptionTextbox = By.xpath("//input[@id='classDescription']");
	private By No_of_Classes = By.xpath("//label[normalize-space()='No of Classes']");
	private By No_of_ClassesTextbox = By.xpath("//input[@id='classNo']");

	private By statusActive = By.xpath("//div[normalize-space()='Active']/p-radiobutton");
	private By statusInActive = By.xpath("(//div[normalize-space()='Inactive']/p-radiobutton");
	private By StaffName = By.xpath("//label[normalize-space()='Staff Name']");
	private By staffName = By.xpath("//input[@placeholder='Select a Staff Name']");
	private By Comments = By.xpath("//label[normalize-space()='Comments']");
	private By Notes = By.xpath("//label[normalize-space()='Notes']");
	private By Recording = By.xpath("//label[normalize-space()='Recording']");
	private By Status = By.xpath("//lable[@for='online']");
	private By ClassDates = By.xpath("//label[normalize-space()='Select Class Dates']");
	private By saveAddClass = By.id("saveClass");

	// required fields
	private By batchNameReq = By.xpath("//small[normalize-space()='Batch Name is required.']");
	private By classTopicReq = By.xpath("//small[normalize-space()='Class Topic is required.']");
	private By classDateReq = By.xpath("//small[normalize-space()='Class Date is required.']");
	private By staffNameReq = By.xpath("//small[normalize-space()='Staff Name is required.']");
	private By noOfClassesReq = By.xpath("//small[normalize-space()='No. of Classes is required.']");

	// optional fields
	private By classComments = By.xpath("//input[@id='classComments']");
	private By notes = By.xpath("//input[@id='classNotes']");
	private By recording = By.xpath("//input[@id='classRecordingPath']");

// class SuccessMessage
	private By classCreated = By.xpath("//div[text()='Successful']");

// Edit Window
	private By editBtn = By.xpath("//button[@icon='pi pi-pencil']");
	private By editPopup = By.xpath(
			"//div[@class='ng-trigger ng-trigger-animation ng-tns-c81-10 p-fluid p-dialog p-component p-dialog-draggable p-dialog-resizable ng-star-inserted']");

// Delete
	private By deletebtn = By.xpath("(//button[@icon='pi pi-trash'])[2]");
	private By confirmyes = By.xpath("//button//span[text()='Yes']");
	private By confirmno = By.xpath("//button//span[text()='No']");
	private By successdelete = By.xpath("//div[text()='Successful']");

	// delete multiple program locators
	private By checkbox1 = By.xpath(
			"//div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr[1]/td[1]/p-tablecheckbox/div/div[2]");
	private By checkbox2 = By.xpath(
			"//div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr[3]/td[1]/p-tablecheckbox/div/div[2]");
	private By dubdelete_icon = By.xpath("//div/mat-card/mat-card-title/div[2]/div[1]/button/span[1]");
	private By dubdelete_yes = By.xpath("//button//span[text()='Yes']");
	private By success_dbdelete = By.xpath("//div/p-toastitem/div/div/div/div[2]");

	// Search
	private By listOfBatchNames = By.xpath("//tbody//td[2]");
	private By listOfClassTopic = By.xpath("//tbody//td[3]");
	private By listOfStaffNames = By.xpath("//tbody//td[7]");

	// sort element locators
	// sort
	private By BatchNameSort = By.xpath("//thead//tr//th[2]//i");
	private By classTopicSort = By.xpath("//thead//tr//th[3]//i");
	private By classDescripSort = By.xpath("//thead//tr//th[4]//i");
	private By StatusSort = By.xpath("//thead//tr//th[5]//i");
	private By ClassDateSort = By.xpath("//thead//tr//th[6]//i");
	private By StaffNameSort = By.xpath("//thead//tr//th[7]//i");

	// SortList
	private By BatchNameList = By.xpath("//tbody//td[2]");
	private By classTopicList = By.xpath("//tbody//td[3]");
	private By classDescripList = By.xpath("//tbody//td[4]");
	private By StatusList = By.xpath("//tbody//td[5]");
	private By ClassDateList = By.xpath("//tbody//td[6]");
	private By StaffNameList = By.xpath("//tbody//td[7]");

	// date picker
	private By datePicker = By.xpath("//input[@id='icon']");
	private By nextMonth = By.xpath("//span[@class='p-datepicker-next-icon pi pi-chevron-right ng-tns-c92-13']");
	private By Currentmonth = By.xpath("//span[@class='p-datepicker-month ng-tns-c92-13 ng-star-inserted']");

	private By calendarTextField = By.xpath("//input[@id='icon']");
	private By selectDateCalenderBtn = By.xpath("//button[@ng-reflect-icon='pi pi-calendar']");
	private By calenderPop_Up = By.xpath("//div[@class='p-datepicker-group ng-tns-c92-13 ng-star-inserted']");

	// Class Page - Pagination locators
	private By prevPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-prev')]");
	private By firstPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-first')]");
	private By nextPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-next')]");
	private By lastPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-last')]");
	private int lastPageEntryCount;
	private int lastPageFooterEntryCount;
	
	//Class page logout
	private By logoutBtn = By.xpath("//span[text()='Logout']");
	

	public ClassPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		actions = new Actions(driver);
		elementUtil = new ElementUtil(driver);
		js = (JavascriptExecutor) driver;
	}

	public void clickLogout() {
		elementUtil.doClick(logoutBtn);
		}	
	
	public void openCalendar() throws Exception {
		elementUtil.doClick(selectDateCalenderBtn);
		Thread.sleep(2000);
	}

	public void clickClassBtn() {
		elementUtil.clickElementByJS(classBtn, driver);
	}

//manage class page methods
	public String getManageHeader() {
		// return ManageHeader.getText();
		return elementUtil.getElementText(ManageHeader);

	}

	public boolean validatesearchbox() {
		return elementUtil.isElementDisplayed(searchBox);
	}

	public List<String> validateManageTableHeader() {

		manageProgramMenuItems = elementUtil.getElement(ManageTable).findElements(By.tagName("th"));
		List<String> itemTextList = new ArrayList<>();
		for (WebElement item : manageProgramMenuItems) {
			String itemText = item.getText();
			itemTextList.add(itemText);
		}

		return itemTextList;

	}

	public String testpaginationfooter() {
		return elementUtil.getElementText(paginationfooter);
	}

	// popup methods

	public void clickAddNewClass() throws InterruptedException {
		// elementUtil.doClick(addNewClassBtn);
		elementUtil.mouseclickUsingAction(addNewClassBtn);
		// elementUtil.clickElementByJS(addNewClassBtn, driver);
		Thread.sleep(5000);
	}

	public boolean cancelDisp() throws InterruptedException {
		// return cancelBtn.isDisplayed();
		elementUtil.scrollIntoView(cancelBtn);
		return elementUtil.isElementDisplayed(cancelBtn);
	}

	public boolean saveDisp() {
		return elementUtil.isElementDisplayed(saveBtn);
	}

	public boolean crossBtnDisp() {
		return elementUtil.isElementDisplayed(crossBtn);
	}

	public boolean batchNameOnPopupDisp() {
		return elementUtil.isElementDisplayed(batchNamePopup);
	}

	public boolean classTopicOnPopupDisp() {
		return elementUtil.isElementDisplayed(classTopicPopup);

	}

	public boolean classDescriptionOnPopupDisp() {
		return elementUtil.isElementDisplayed(ClassDescription);

	}

	public boolean noOfClassesonPopupDisp() {
		return elementUtil.isElementDisplayed(No_of_Classes);
	}

	public boolean staffNameOnPopupDisp() {
		elementUtil.clickElementByJS(StaffName, driver);
		return elementUtil.isElementDisplayed(StaffName);
	}

	public boolean statusonPopupDisp() {
		return elementUtil.isElementDisplayed(Status);
	}

	public boolean recordingonPopupDisp() {
		return elementUtil.isElementDisplayed(Recording);
	}

	public boolean notesPopupDisp() {
		return elementUtil.isElementDisplayed(Notes);
	}

	public boolean commentsonPopupDisp() {
		return elementUtil.isElementDisplayed(Comments);
	}

	public boolean classDatesonPopupDisp() {
		return elementUtil.isElementDisplayed(ClassDates);
	}

	public boolean batchnamedropdownDisplayed() {
		return elementUtil.isElementDisplayed(batchNameDrpdw);

	}

	public String addingMandatoryFields(String batchName, String ClassTopic, String ClassDescription, String month,
			String date, String StaffName, String Status) throws Exception {

		
		elementUtil.clickElementByJS(classBtn, driver);
		Thread.sleep(1000);
		elementUtil.clickElementByJS(addNewClassBtn, driver);
		//js.executeScript("arguments[0].click();", classBtn);
		//js.executeScript("arguments[0].click();", addNewClassBtn);
		Thread.sleep(3000);
		elementUtil.clickElementByJS(batchNameTextArea, driver);
		// elementUtil.doSendKeys(batchNameDrpdw, batchName);
		elementUtil.doSendKeys(batchNameTextArea, batchName);

		// Enter Class Topic
		elementUtil.clickElementByJS(classTopicTextbox, driver);
		elementUtil.doSendKeys(classTopicTextbox, ClassTopic);

		// Enter Class Description
		elementUtil.doClick(ClassDescriptionTextbox);
		elementUtil.doSendKeys(ClassDescriptionTextbox, ClassDescription);

		// Select Class Dates
		elementUtil.doClick(datePicker); // clicking on date box. Calendar pops up

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath("(//div[contains(@class,'p-datepicker-group')])[1]")));

		while (!elementUtil.getElementText(Currentmonth).contains(month)) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//span[@class='p-datepicker-next-icon pi pi-chevron-right ng-tns-c92-13']")));
			elementUtil.clickElementByJS(nextMonth, driver);
		}

		actions.contextClick(elementUtil.getElement(calendarTextField)).perform();
		elementUtil.doClick(calendarTextField);
		elementUtil.doSendKeys(calendarTextField, date);

		// Enter Staff Name
		elementUtil.scrollIntoView(staffName);
		elementUtil.doClick(staffName);
		elementUtil.doSendKeys(staffName, StaffName);
		elementUtil.scrollIntoView(No_of_ClassesTextbox);

		if (Status.equals("Active")) {
			elementUtil.doClick(statusActive);
		} else {
			elementUtil.doClick(statusInActive);
		}

		elementUtil.doClick(saveBtn);

		return elementUtil.getElementText(classCreated);
		
		
	}

	public boolean isSortingbuttonDisplayed(List<WebElement> elements) {
		boolean flag = true;
		if (elements.size() > 1) {
			for (int i = 1; i < elements.size(); i++) {
				WebElement element = elements.get(i);
				if (!element.isDisplayed()) {
					flag = false;
					break;
				}
			}
		} else {
			flag = false;
		}
		return flag;
	}

	public boolean validateSortingBtn() {
		// return isSortingbuttonDisplayed(sortingBtn);
		return isSortingbuttonDisplayed(elementUtil.getElements(sortingBtn));
	}

	public boolean deleteBtnDisplayed() {
		// return deleteBtnMC.isDisplayed();
		return elementUtil.isElementDisplayed(deleteBtnMC);
	}

	public boolean validateShowingEnteries() {
		// return showingEnteries.isDisplayed();
		return elementUtil.isElementDisplayed(showingEnteries);
	}

	public boolean onManagePage() {
		return elementUtil.isElementDisplayed(managePage);
	}

	public void clickOnSave() {
		elementUtil.doClick(saveAddClass);
	}

	public String getBatchNameReqText() {
		return getTextFromMandatoryFields(elementUtil.getElement(batchNameReq));
	}

	public String getClassTopicReqText() {
		return getTextFromMandatoryFields(elementUtil.getElement(classTopicReq));
	}

	public String getClassDateReqText() {
		return getTextFromMandatoryFields(elementUtil.getElement(classDateReq));
	}

	public String getStaffNameReqText() {
		return getTextFromMandatoryFields(elementUtil.getElement(staffNameReq));
	}

	public String getNoOfClassesReqText() {
		return getTextFromMandatoryFields(elementUtil.getElement(noOfClassesReq));
	}

	private String getTextFromMandatoryFields(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		return element.getText();
	}

	public void selectOptionalFields(String comments, String Notes, String Recording) throws InterruptedException {

		elementUtil.scrollIntoView(Status);
		elementUtil.doSendKeys(classComments, comments);
		elementUtil.doSendKeys(notes, Notes);
		elementUtil.doSendKeys(recording, Recording);
		elementUtil.doClick(saveAddClass);

	}

	/*---edit page--------*/

	public void clickOnEdit() {
		actions.doubleClick(elementUtil.getElement(editBtn)).perform();
	}

	public boolean editPopup() {
		return elementUtil.isElementDisplayed(editPopup);
	}

	public boolean batchNameDisabled() {
		return elementUtil.isElementEnabled(batchNameDrpdw);
	}

	public boolean classTopicDisabled() {
		return elementUtil.isElementEnabled(classTopicTextbox);
	}

	public void editClassDetails(String editClass) {
		elementUtil.doSendKeys(ClassDescriptionTextbox, editClass);
	}

	public String saveEditClass() {
		elementUtil.doClick(saveAddClass);
		return elementUtil.getElementText(classCreated);
	}

	/*----delete---------*/
	public void clickOnDeleteIcon() {
		Actions actions = new Actions(driver);
		actions.doubleClick(elementUtil.getElement(deletebtn)).perform();
	}

	public void deleteSingleProgram() {
		elementUtil.doClick(confirmyes);
		String text1 = elementUtil.getElementText(successdelete);
		System.out.println(text1);
	}

	public void DropDeleteSingleProgram() {
		elementUtil.doClick(confirmno);
	}

	public void SelectCheckBoxes() {
		Actions actions = new Actions(driver);
		actions.doubleClick(elementUtil.getElement(checkbox1)).perform();
		elementUtil.doClick(checkbox2);
	}

	public void MultipleDelete() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(dubdelete_icon)).click();

	}

	public void DeleteSuccess() {
		elementUtil.doClick(dubdelete_yes);
		String text2 = elementUtil.getElementText(success_dbdelete);
		System.out.println(text2);
	}

	public void searhBoxValidation(String field, String value) throws InterruptedException {
		/*
		 * JavascriptExecutor js = (JavascriptExecutor) driver;
		 * js.executeScript("arguments[0].click();", searchBox);
		 */
		elementUtil.clickElementByJS(searchBox, driver);
		boolean found = false; // where are we using this?? --> PADMAJA
		switch (field) {
		case "Batch Name":
			// searchBox.sendKeys(value);
			elementUtil.doSendKeys(searchBox, value);
			logicForValidatingSearch(elementUtil.getElements(listOfBatchNames), value);
			// logicForValidatingSearch(listOfBatchNames, value);
			break;

		case "Class Topic":
			// searchBox.sendKeys(value);
			elementUtil.doSendKeys(searchBox, value);
			logicForValidatingSearch(elementUtil.getElements(listOfClassTopic), value);
			// logicForValidatingSearch(listOfClassTopic, value);
			break;

		case "Staff Name":
			// searchBox.sendKeys(value);
			elementUtil.doSendKeys(searchBox, value);
			logicForValidatingSearch(elementUtil.getElements(listOfStaffNames), value);
			// logicForValidatingSearch(listOfStaffNames, value);
			break;
		}
	}

	public void logicForValidatingSearch(List<WebElement> searchedValues, String value) {
		boolean found = false;
		for (WebElement v : searchedValues) {
			if (v.getText().equalsIgnoreCase(value)) {
				System.out.println("Search is success for value: " + value);
				found = true;
				break;
			}
		}

		if (!found) {
			System.out.println("Search is not success for value: " + value);
		}

	}

	// sorting
	public void clickBatchNameSort() {
		actions.click(elementUtil.getElement(BatchNameSort)).perform();
		actions.click(elementUtil.getElement(BatchNameSort)).perform();
	}

	public void clickBatchNameSortDec() {
		actions.click(elementUtil.getElement(BatchNameSort)).perform();
		actions.click(elementUtil.getElement(BatchNameSort)).perform();
		actions.click(elementUtil.getElement(BatchNameSort)).perform();

	}

	public void clickclassTopicSort() {
		actions.click(elementUtil.getElement(classTopicSort)).perform();
		actions.click(elementUtil.getElement(classTopicSort)).perform();

	}

	public void clickclassTopicSortDes() {
		actions.click(elementUtil.getElement(classTopicSort)).perform();
		actions.click(elementUtil.getElement(classTopicSort)).perform();
		actions.click(elementUtil.getElement(classTopicSort)).perform();

	}

	public void clickclassDescriptionSort() {
		actions.click(elementUtil.getElement(classDescripSort)).perform();
		actions.click(elementUtil.getElement(classDescripSort)).perform();

	}

	public void clickclassDescriptionSortDes() {
		actions.click(elementUtil.getElement(classDescripSort)).perform();
		actions.click(elementUtil.getElement(classDescripSort)).perform();
		actions.click(elementUtil.getElement(classDescripSort)).perform();

	}

	public void clickStatusSort() {
		actions.click(elementUtil.getElement(StatusSort)).perform();
		actions.click(elementUtil.getElement(StatusSort)).perform();
	}

	public void clickStatusSortDec() {
		actions.click(elementUtil.getElement(StatusSort)).perform();
		actions.click(elementUtil.getElement(StatusSort)).perform();
		actions.click(elementUtil.getElement(StatusSort)).perform();

	}

	public void clickClassDateSort() {
		actions.click(elementUtil.getElement(ClassDateSort)).perform();
		actions.click(elementUtil.getElement(ClassDateSort)).perform();
	}

	public void clickClassDateSortDec() {
		actions.click(elementUtil.getElement(ClassDateSort)).perform();
		actions.click(elementUtil.getElement(ClassDateSort)).perform();
		actions.click(elementUtil.getElement(ClassDateSort)).perform();

	}

	public void clickStaffNameSort() {
		actions.click(elementUtil.getElement(StaffNameSort)).perform();
		actions.click(elementUtil.getElement(StaffNameSort)).perform();
	}

	public void clickStaffNameSortDec() {
		actions.click(elementUtil.getElement(StaffNameSort)).perform();
		actions.click(elementUtil.getElement(StaffNameSort)).perform();
		actions.click(elementUtil.getElement(StaffNameSort)).perform();

	}

	// get and return original list
	public List<String> getOriginalList(String type) {
		List<String> originalList = null;

		if (type.equals("BatchName")) {
			// originalList = printWebElements(BatchNameList);
			originalList = printWebElements(elementUtil.getElements(BatchNameList));

		} else if (type.equals("ClassTopic")) {
			// originalList = printWebElements(classTopicList);
			originalList = printWebElements(elementUtil.getElements(classTopicList));

		} else if (type.equals("Status")) {
			// originalList = printWebElements(StatusList);
			originalList = printWebElements(elementUtil.getElements(StatusList));

		} else if (type.equals("Class Date")) {
			// originalList = printWebElements(ClassDateList);
			originalList = printWebElements(elementUtil.getElements(ClassDateList));

		} else if (type.equals("Staff Name")) {
			// originalList = printWebElements(StaffNameList);
			originalList = printWebElements(elementUtil.getElements(StaffNameList));

		}

		else {
			// originalList = printWebElements(classDescripList);
			originalList = printWebElements(elementUtil.getElements(classDescripList));

		}
		return originalList;
	}

	public List<Date> getClassDatesOriginalList() {

		// List to store the dates
		List<Date> dates = new ArrayList<>();

		// Define the date format used on the webpage
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // Adjust the format accordingly

		// Extract dates from elements and convert to Date objects
		// for (WebElement element : ClassDateList) {

		for (WebElement element : elementUtil.getElements(ClassDateList)) {
			String dateStr = element.getText(); // Get the text representing the date
			try {
				Date classdates = dateFormat.parse(dateStr); // Parse the string to Date object
				dates.add(classdates);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return dates;

	}

	public List<Date> getClassDatesSortedList() {
		// List to store the dates
		List<Date> dates = new ArrayList<>();

		// Define the date format used on the webpage
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // Adjust the format accordingly

		// Extract dates from elements and convert to Date objects
//    for (WebElement element : ClassDateList) {

		for (WebElement element : elementUtil.getElements(ClassDateList)) {
			String dateStr = element.getText(); // Get the text representing the date
			try {
				Date classdates = dateFormat.parse(dateStr); // Parse the string to Date object
				dates.add(classdates);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		Collections.sort(dates);
		return dates;

	}

// this method will sort the given list
	public List<String> getSortedList(List<String> originalList) {
		System.out.println("Original List Before sorting is" + originalList);
		List<String> sortedList = new ArrayList<>(originalList);
		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		System.out.println("Sorted List After sorting is" + sortedList);
		return sortedList;
	}

	public List<String> getSortedListDescending(List<String> originalList) {

		System.out.println("Original List Before sorting is" + originalList);
		List<String> sortedList = new ArrayList<>(originalList);
//        Collections.sort(sortedList, (s1, s2) -> s2.compareToIgnoreCase(s1));
//        Collections.sort(sortedList, Collections.reverseOrder());
		Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER.reversed());
		System.out.println("Sorted List After sorting is" + sortedList);
		return sortedList;
	}

// covert web element to java string list	
	public List<String> printWebElements(List<WebElement> options) {
		List<String> texts = new ArrayList<String>();
		int i = 0;
		for (WebElement option : options) {
			texts.add(i, option.getText());
			i++;
		}
		System.out.println("The number of items in the list are: " + texts.size());
		return texts;
	}

	public void clickOnNextPage() {
		elementUtil.clickElementByJS(nextPaginatorBtn, driver);

	}

	public boolean nextPageEnabled() {
		return elementUtil.isElementEnabled(nextPaginatorBtn);

	}

	public String nextPageValidation() {

		String pageEntryText = driver.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']"))
				.getText();
		return pageEntryText;
	}

	public void clickOnLastPage() {
		elementUtil.clickElementByJS(lastPaginatorBtn, driver);

	}

	public boolean lastPageDisplayed() {
		return elementUtil.isElementDisplayed(lastPaginatorBtn);

	}

	public boolean verifyNextPageBtnDisabled() {

		if (!elementUtil.isElementEnabled(nextPaginatorBtn)) {
			return true;
		}
		return false;
	}

	public int lastPageRecord() {

		String pageEntryText = driver.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']"))
				.getText();
		String[] lastPageEntry = pageEntryText.split("of ");
		lastPageEntryCount = Integer.parseInt(lastPageEntry[1].trim().split(" ")[0]);
		return lastPageEntryCount;
	}

	public int lastPageFootCount() {
		String footerMessageText = driver.findElement(By.xpath("//p-table/div/div[2]/div")).getText();
		String[] lastPageFooterEntry = footerMessageText.split("are ");
		lastPageFooterEntryCount = Integer.parseInt(lastPageFooterEntry[1].trim().split(" ")[0]);
		return lastPageFooterEntryCount;

	}

	public void clickOnFirstPage() {
		elementUtil.clickElementByJS(firstPaginatorBtn, driver);

	}

	public boolean verifyPreviousPageBtnEnabled() {

		if (elementUtil.isElementEnabled(prevPaginatorBtn)) {
			return true;
		}
		return false;
	}

	public boolean verifyPreviousPageBtnDisabled() {

		if (!elementUtil.isElementEnabled(prevPaginatorBtn)) {
			return true;
		}
		return false;
	}

	// date picker for weekend date validation
	public void clickDatePicker() {
		elementUtil.doClick(datePicker);
		wait.until(ExpectedConditions.visibilityOfElementLocated(calenderPop_Up));
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void weekendDaysDisabled() {
		WebElement calendarTable = driver
				.findElement(By.xpath("//table[@class='p-datepicker-calendar ng-tns-c92-13']"));

		for (WebElement row : calendarTable.findElements(By.tagName("tr"))) {
			// Get all the days (td elements) in the current row
			for (WebElement day : row.findElements(By.tagName("td"))) {

				
				String disabled = day.getDomAttribute("class");
				if (!disabled.contains("p-disabled")) {
					throw new AssertionError("Weekend date is disabled: " + day.getText());
				}

				if (day.getText().matches("[0-9]+")) {
					int dayOfWeek = Integer.parseInt(day.getText());
					if (dayOfWeek == 6 || dayOfWeek == 7) { // Saturday (6) or Sunday (7)
						if (!day.getCssValue("pointer-events").equals("none")) {
							throw new AssertionError("Weekend date is selectable: " + day.getText());
						}
					}
				}

			}
		}

	}

	
	public boolean areWeekendDatesDisabled() {
		
		boolean flag = false;
		List<String> weekendDates = disabledweekend();
		int numberOfWeekendDates = 	weekendDates.size();
		
		int counter=0;
		
		for (String date: weekendDates) {
			//By weekendDate = ;
			WebElement weekendDate = elementUtil.getElement(By.xpath("(//tbody)[2]//tr//td//span[text()='"+date+"']"));
			if(weekendDate.getDomAttribute("class").contains("disabled")) {
				counter++;
			}
				
		}
		System.out.println("counter size = " + counter);
		System.out.println("numberOfWeekendDates size = " + numberOfWeekendDates);

		if(counter == numberOfWeekendDates) {
			flag = true;
		}
		
		return flag;
	}
	
	
	
	public List<String> disabledweekend() {

		List<String> weekendDates = new ArrayList<String>();

		By sundayDates = By.xpath("//table[contains(@class,'datepicker-calendar')]//tbody/tr/td["
				+ dynamicallyGetAnyDaysColumnNumber("Su") + "]/span");
		By saturdayDates = By.xpath("//table[contains(@class,'datepicker-calendar')]//tbody/tr/td["
				+ dynamicallyGetAnyDaysColumnNumber("Sa") + "]/span");

		List<WebElement> sundayDatesList = util.getElements(sundayDates);
		List<WebElement> saturdayDatesList = util.getElements(saturdayDates);
		sundayDatesList.addAll(saturdayDatesList);

		for (WebElement e : sundayDatesList) {
			weekendDates.add(e.getText());
		}

		if ((Integer.parseInt(weekendDates.get(0))) > 1) {
			weekendDates.remove(0);
		}

		return weekendDates;

	}

	private byte dynamicallyGetAnyDaysColumnNumber(String day) {

		By days = By.xpath("//table[contains(@class,'p-datepicker-calendar')]//th/span");
		byte colNumber = 1;
		List<WebElement> x = util.getElements(days);

		for (WebElement e : x) {
			String dayValue = e.getText();
			if (dayValue.equalsIgnoreCase(day)) {
				break;
			} else
				colNumber++;
		}
		return colNumber;
	}

}
