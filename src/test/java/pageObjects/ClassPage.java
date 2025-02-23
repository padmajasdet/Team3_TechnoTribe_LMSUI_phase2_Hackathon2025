package pageObjects;

import java.time.Duration;
//import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.text.SimpleDateFormat;
import java.text.*;

import org.apache.poi.ss.formula.functions.T;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import utilities.ElementUtil;

import hooks.TestContext;


public class ClassPage {

	private WebDriver driver;
	private WebDriverWait wait;
	Actions actions;

	TestContext context;
	ElementUtil elementUtil;
	JavascriptExecutor js;
	List<WebElement> manageProgramMenuItems = new ArrayList<>();

	public ClassPage(WebDriver driver, TestContext context) {

		this.driver = context.getDriver();

		this.context = context;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		this.actions = new Actions(driver);
		this.elementUtil = new ElementUtil(driver);
		this.js = (JavascriptExecutor) driver;
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
	@FindBy(xpath = "//p-table/div/div[2]/div")
	private WebElement paginationfooter;
	@FindBy(className = "p-sortable-column-icon")
	private List<WebElement> sortingBtn;
	@FindBy(css = "button[class='p-button-danger p-button p-component p-button-icon-only']")
	private WebElement deleteBtnMC;
	@FindBy(css = ".p-paginator-current.ng-star-inserted")
	private WebElement showingEnteries;

	// Add new class
	// popup new window elements
	@FindBy(xpath = "//button[text()='Add New Class']")
	private WebElement addNewClassBtn;
	@FindBy(xpath = "//button[@label='Cancel']")
	private WebElement cancelBtn;
	@FindBy(xpath = "//button[@label='Save']")
	private WebElement saveBtn;
	@FindBy(css = ".p-dialog-header-close")
	private WebElement crossBtn;
	@FindBy(css = ".p-datatable-footer.ng-star-inserted")
	private WebElement footer;
	@FindBy(xpath = "//label[normalize-space()='Batch Name']")
	private WebElement batchNamePopup;

	// Add new class page 
	//@FindBy(xpath = "//input[@placeholder='Select a Batch Name']")
	@FindBy(xpath = "//label[text()='Batch Name']//following-sibling::p-dropdown//div[@role='button']")
	private WebElement batchNameDrpdw;

	// @FindBy(id = "batchName")
	// private WebElement batchNameDrpdw;
	// @FindBy(xpath = "(//span[contains(@class,'p-dropdown-trigger-icon')]/..)[1]")
	// private WebElement batchNameDrpdw;

	@FindBy(xpath = "//label[text()='Class Topic ']")
	private WebElement classTopicPopup;
	@FindBy(xpath = "//input[@id='classTopic']")
	private WebElement classTopicTextbox;

	@FindBy(xpath = "//div[normalize-space()='Manage Class']")
	private WebElement managePage;
	@FindBy(xpath = "//label[normalize-space()='Class Description']")
	private WebElement ClassDescription;
	@FindBy(xpath = "//input[@id='classDescription']")
	private WebElement ClassDescriptionTextbox;

	@FindBy(xpath = "//label[normalize-space()='No of Classes']")
	private WebElement No_of_Classes;
	@FindBy(xpath = "//input[@id='classNo']")
	private WebElement No_of_ClassesTextbox;
	@FindBy(xpath = "//span[@class='p-dropdown-trigger-icon ng-tns-c88-29 pi pi-chevron-down']")
	private WebElement StaffNameDropDown;
	@FindBy(xpath = "//div[normalize-space()='Active']/p-radiobutton")
	private WebElement statusActive;
	@FindBy(xpath = "(//div[normalize-space()='Inactive']/p-radiobutton")
	private WebElement statusInActive;

	@FindBy(xpath = "//label[normalize-space()='Staff Name']")
	private WebElement StaffName;
	@FindBy(xpath = "//input[@placeholder='Select a Staff Name']")
	private WebElement staffName;
	@FindBy(xpath = "//label[normalize-space()='Comments']")
	private WebElement Comments;
	@FindBy(xpath = "//label[normalize-space()='Notes']")
	private WebElement Notes;
	@FindBy(xpath = "//label[normalize-space()='Recording']")
	private WebElement Recording;
	@FindBy(xpath = "//lable[@for='online']")
	private WebElement Status;
	@FindBy(xpath = "//label[normalize-space()='Select Class Dates']")
	private WebElement ClassDates;
	@FindBy(id = "saveClass")
	private WebElement saveAddClass;

	// required fields

	@FindBy(xpath = "//small[normalize-space()='Batch Name is required.']")
	private WebElement batchNameReq;
	@FindBy(xpath = "//small[normalize-space()='Class Topic is required.']")
	private WebElement classTopicReq;
	@FindBy(xpath = "//small[normalize-space()='Class Date is required.']")
	private WebElement classDateReq;
	@FindBy(xpath = "//small[normalize-space()='Staff Name is required.']")
	private WebElement staffNameReq;
	@FindBy(xpath = "//small[normalize-space()='No. of Classes is required.']")
	private WebElement noOfClassesReq;
// optional fields
	@FindBy(xpath = "//input[@id='classComments']")
	private WebElement classComments;
	@FindBy(xpath = "//input[@id='classNotes']")
	private WebElement notes;
	@FindBy(xpath = "//input[@id='classRecordingPath']")
	private WebElement recording;

// class SuccessMessage
	@FindBy(xpath = "//div[text()='Successful']")
	private WebElement classCreated;

// Edit Window
	@FindBy(xpath = "//button[@icon='pi pi-pencil']")
	private WebElement editBtn;
	//@FindBy(xpath = "//div[@class='p-dialog-header ng-tns-c168-7 ng-star-inserted']")
	@FindBy(xpath = "//div[@class='ng-trigger ng-trigger-animation ng-tns-c81-10 p-fluid p-dialog p-component p-dialog-draggable p-dialog-resizable ng-star-inserted']")
	private WebElement editPopup;
	

// Delete

	@FindBy(xpath = "(//button[@icon='pi pi-trash'])[2]")
	private WebElement deletebtn;
	@FindBy(xpath = "//button//span[text()='Yes']")
	private WebElement confirmyes;
	@FindBy(xpath = "//button//span[text()='No']")
	private WebElement confirmno;
	@FindBy(xpath = "//div[text()='Successful']")
	private WebElement successdelete;
	// delete multiple program locators
		@FindBy(xpath = "//div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr[1]/td[1]/p-tablecheckbox/div/div[2]")
		private WebElement checkbox1;
		@FindBy(xpath = "//div/mat-card/mat-card-content/p-table/div/div[1]/table/tbody/tr[3]/td[1]/p-tablecheckbox/div/div[2]")
		private WebElement checkbox2;
		@FindBy(xpath = "//div/mat-card/mat-card-title/div[2]/div[1]/button/span[1]")
		private WebElement dubdelete_icon;
		@FindBy(xpath = "//button//span[text()='Yes']")
		private WebElement dubdelete_yes;
		@FindBy(xpath = "//div/p-toastitem/div/div/div/div[2]")
		private WebElement success_dbdelete;
		
		//Search
		@FindBy(xpath = "//tbody//td[2]")
		List<WebElement> listOfBatchNames;
		@FindBy(xpath = "//tbody//td[3]")
		List<WebElement> listOfClassTopic;
		@FindBy(xpath = "//tbody//td[7]")
		List<WebElement> listOfStaffNames;
		
		
		// sort element locators
		// sort
		@FindBy(xpath = "//thead//tr//th[2]//i")
		private WebElement BatchNameSort;
		@FindBy(xpath = "//thead//tr//th[3]//i")
		private WebElement classTopicSort;
		@FindBy(xpath = "//thead//tr//th[4]//i")
		private WebElement classDescripSort;
		@FindBy(xpath = "//thead//tr//th[5]//i")
		private WebElement StatusSort;
		@FindBy(xpath = "//thead//tr//th[6]//i")
		private WebElement ClassDateSort;
		@FindBy(xpath = "//thead//tr//th[7]//i")
		private WebElement StaffNameSort;
		
		
		
		
		// SortList
		@FindBy(xpath = "//tbody//td[2]")
		private List<WebElement> BatchNameList;
		@FindBy(xpath = "//tbody//td[3]")
		private List<WebElement> classTopicList;
		@FindBy(xpath = "//tbody//td[4]")
		private List<WebElement> classDescripList;
		
		@FindBy(xpath = "//tbody//td[5]")
		private List<WebElement> StatusList;
		@FindBy(xpath = "//tbody//td[6]")
		private List<WebElement> ClassDateList;
		@FindBy(xpath = "//tbody//td[6]")
		private List<WebElement> StaffNameList;
		
		

	// date picker

	@FindBy(xpath = "//input[@id='icon']")
	private WebElement datePicker;
	@FindBy(xpath = "//span[@class='p-datepicker-next-icon pi pi-chevron-right ng-tns-c92-13']")
	private WebElement nextMonth;
	@FindBy(xpath = "//span[@class='p-datepicker-month ng-tns-c92-13 ng-star-inserted']")
	private WebElement Currentmonth;
	@FindBy(xpath = "//span[normalize-space()='21']")
	private WebElement date;
	// @FindBy(xpath = "//table[@class='p-datepicker-calendar
	// ng-tns-c92-13']/tbody/tr/td/span")
	// private List<WebElement> dateSel;
	@FindBy(xpath = "//td[@ng-reflect-ng-class='[object Object]']")
	private List<WebElement> dateSel;

	@FindBy(xpath = "//input[@id='icon']")
	private WebElement calendarTextField;
	@FindBy(xpath = "//button[@ng-reflect-icon='pi pi-calendar']")
	private WebElement selectDateCalenderBtn;
	@FindBy(css = "span.p-disabled")
	private WebElement allDisabledDatesForCurrentMonth;
	@FindBy(tagName = "p-calendar")
	private WebElement elementStoringEnteredDate;
	
	//Class Page - Pagination locators
	
	private	By prevPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-prev')]");
	private	By firstPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-first')]");
	private By thirdPaginatorBtn = By.xpath("//button[normalize-space()='3']");
	private	By nextPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-next')]");
	private	By lastPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-last')]");
	private int lastPageEntryCount;
	private int lastPageFooterEntryCount;
		

	// String batchName, String ClassTopic, String ClassDescription, String month,
	// String date1, String date2, String StaffName, String Status
	public void openCalendar() throws Exception {
		// util.scrollIntoView(selectDateCalenderBtn);
		elementUtil.doClick(selectDateCalenderBtn);
		Thread.sleep(2000);
	}

	public void clickClassBtn() {
		JavascriptExecutor js = (JavascriptExecutor) driver;

		js.executeScript("arguments[0].click();", classBtn);

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

	public String testpaginationfooter() {

		String paginationTextfooter = paginationfooter.getText();

		return paginationTextfooter;
	}

	// popup methods

	public void clickAddNewClass() throws InterruptedException {

		js.executeScript("arguments[0].click();", addNewClassBtn);

	}

	public boolean cancelDisp() throws InterruptedException {
		Thread.sleep(3000);
		return cancelBtn.isDisplayed();
	}

	public boolean saveDisp() {
		return saveBtn.isDisplayed();
	}

	public boolean crossBtnDisp() {
		return crossBtn.isDisplayed();
	}

	public boolean batchNameOnPopupDisp() {

		return batchNamePopup.isDisplayed();
	}

	public boolean classTopicOnPopupDisp() {
		return classTopicPopup.isDisplayed();
	}

	public boolean classDescriptionOnPopupDisp() {
		return ClassDescription.isDisplayed();

	}

	public boolean noOfClassesonPopupDisp() {
		return No_of_Classes.isDisplayed();

	}

	public boolean staffNameOnPopupDisp() {
		js.executeScript("arguments[0].click();", StaffName);
		return StaffName.isDisplayed();
	}

	public boolean statusonPopupDisp() {
		return Status.isDisplayed();

	}

	public boolean recordingonPopupDisp() {
		return Recording.isDisplayed();

	}

	public boolean notesPopupDisp() {
		return Notes.isDisplayed();

	}

	public boolean commentsonPopupDisp() {
		return Comments.isDisplayed();

	}

	public boolean classDatesonPopupDisp() {
		return ClassDates.isDisplayed();

	}

	public boolean batchnamedropdownDisplayed() {
		return batchNameDrpdw.isDisplayed();

	}

	public String addingMandatoryFields(String batchName, String ClassTopic, String ClassDescription, String month,
			String date1, String StaffName, String Status) throws InterruptedException {
		// Thread.sleep(3000);
		
		//JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", batchNameDrpdw);
		//elementUtil.clickElementByJS(batchNameDrpdw, driver);
		
		
		batchNameDrpdw.sendKeys(batchName);

		js.executeScript("arguments[0].click();", classTopicTextbox);
		// classTopicTextbox.click();
		classTopicTextbox.sendKeys(ClassTopic);
		// js.executeScript("arguments[0].click();", batchNameDrpdw);
		// batchNameDrpdw.click();

		ClassDescriptionTextbox.click();
		ClassDescriptionTextbox.sendKeys(ClassDescription);
		datePicker.click();

		Thread.sleep(1000);

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='p-datepicker-group ng-tns-c92-13 ng-star-inserted']")));

		while (!Currentmonth.getText().contains(month)) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//span[@class='p-datepicker-next-icon pi pi-chevron-right ng-tns-c92-13']")));
			js.executeScript("arguments[0].click();", nextMonth);

		}
		actions.contextClick(calendarTextField).perform();
		calendarTextField.click();
		calendarTextField.sendKeys(date1);

		js.executeScript("arguments[0].scrollIntoView(true);", staffName);
		staffName.click();
		staffName.sendKeys(StaffName);
		js.executeScript("arguments[0].scrollIntoView(true);", No_of_ClassesTextbox);

		if (Status.equals("Active")) {
			statusActive.click();
		} else {
			statusInActive.click();

		}
		saveBtn.click();

		return classCreated.getText();
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
		return isSortingbuttonDisplayed(sortingBtn);
	}

	public boolean deleteBtnDisplayed() {
		return deleteBtnMC.isDisplayed();
	}

	public boolean validateShowingEnteries() {
		return showingEnteries.isDisplayed();

	}

	public boolean onManagePage() {
		return managePage.isDisplayed();
	}

	public void clickOnSave() {
		saveAddClass.click();
	}

	public String getBatchNameReqText() {
		return getTextFromMandatoryFields(batchNameReq);
	}

	public String getClassTopicReqText() {
		return getTextFromMandatoryFields(classTopicReq);
	}

	public String getClassDateReqText() {
		return getTextFromMandatoryFields(classDateReq);
	}

	public String getStaffNameReqText() {
		return getTextFromMandatoryFields(staffNameReq);
	}

	public String getNoOfClassesReqText() {
		return getTextFromMandatoryFields(noOfClassesReq);
	}

	private String getTextFromMandatoryFields(WebElement element) {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView(true);", element);
		return element.getText();
	}

	public void selectOptionalFields(String comments, String Notes, String Recording) {

		JavascriptExecutor js = (JavascriptExecutor) driver;
		// js.executeScript("arguments[0].scrollIntoView(true);", statusInActive);
		js.executeScript("arguments[0].scrollIntoView(true);", Status);
		classComments.sendKeys(comments);
		notes.sendKeys(Notes);
		recording.sendKeys(Recording);

		saveAddClass.click();

	}

	/*---edit page--------*/

	public void clickOnEdit() {
		// Actions actions = new Actions(driver);
		actions.doubleClick(editBtn).perform();
	}

	public boolean editPopup() {
		return editPopup.isDisplayed();
	}

	public boolean batchNameDisabled() {
		return batchNameDrpdw.isEnabled();
	}

	public boolean classTopicDisabled() {
		return classTopicTextbox.isEnabled();
	}

	public void editClassDetails(String editClass) {
		ClassDescriptionTextbox.sendKeys(editClass);
	}

	public String saveEditClass() {
		saveAddClass.click();
		return classCreated.getText();
	}
	
	/*----delete---------*/
	public void clickOnDeleteIcon() {
		Actions actions = new Actions(driver);
		actions.doubleClick(deletebtn).perform();
	}

	public void deleteSingleProgram() {
		confirmyes.click();
		String text1;
		text1 = successdelete.getText();
		System.out.println(text1);
	}

	public void DropDeleteSingleProgram() {
		confirmno.click();

	}

	public void SelectCheckBoxes() {
		Actions actions = new Actions(driver);
		actions.doubleClick(checkbox1).perform();
		checkbox2.click();
		System.out.println("this function executed:");

	}

	public void MultipleDelete() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.elementToBeClickable(dubdelete_icon)).click();

	}

	public void DeleteSuccess() {
		dubdelete_yes.click();
		String text2;
		text2 = success_dbdelete.getText();
		System.out.println(text2);
	}
	public void searhBoxValidation(String field, String value) throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("arguments[0].click();", searchBox);
		boolean found = false;
		switch (field) {
		case "Batch Name":
			searchBox.sendKeys(value);
			logicForValidatingSearch(listOfBatchNames, value);
			break;
		case "Class Topic":
			searchBox.sendKeys(value);
			logicForValidatingSearch(listOfClassTopic, value);
			break;
		case "Staff Name":
			searchBox.sendKeys(value);
			logicForValidatingSearch(listOfStaffNames, value);
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
	
	
	//sorting
	public void clickBatchNameSort() {
		actions.click(BatchNameSort).perform();
		actions.click(BatchNameSort).perform();
	}

	public void clickBatchNameSortDec() {
		actions.click(BatchNameSort).perform();
		actions.click(BatchNameSort).perform();
		actions.click(BatchNameSort).perform();

	}

	public void clickclassTopicSort() {
		actions.click(classTopicSort).perform();
		actions.click(classTopicSort).perform();

	}

	public void clickclassTopicSortDes() {
		actions.click(classTopicSort).perform();
		actions.click(classTopicSort).perform();
		actions.click(classTopicSort).perform();

	}

	public void clickclassDescriptionSort() {
		actions.click(classDescripSort).perform();
		actions.click(classDescripSort).perform();

	}

	public void clickclassDescriptionSortDes() {
		actions.click(classDescripSort).perform();
		actions.click(classDescripSort).perform();
		actions.click(classDescripSort).perform();

	}
	public void clickStatusSort() {
		actions.click(StatusSort).perform();
		actions.click(StatusSort).perform();
	}

	public void clickStatusSortDec() {
		actions.click(StatusSort).perform();
		actions.click(StatusSort).perform();
		actions.click(StatusSort).perform();

	}
	public void clickClassDateSort() {
		actions.click(ClassDateSort).perform();
		actions.click(ClassDateSort).perform();
	}

	public void clickClassDateSortDec() {
		actions.click(ClassDateSort).perform();
		actions.click(ClassDateSort).perform();
		actions.click(ClassDateSort).perform();

	}
	public void clickStaffNameSort() {
		actions.click(StaffNameSort).perform();
		actions.click(StaffNameSort).perform();
	}

	public void clickStaffNameSortDec() {
		actions.click(StaffNameSort).perform();
		actions.click(StaffNameSort).perform();
		actions.click(StaffNameSort).perform();

	}

//get and return original list	
	public List<String> getOriginalList(String type) {
		List<String> originalList = null;

		if (type.equals("BatchName")) {
			originalList = printWebElements(BatchNameList);

		} else if (type.equals("ClassTopic")) {
			originalList = printWebElements(classTopicList);

		} else if (type.equals("Status")) {
			originalList = printWebElements(StatusList);
		}else if (type.equals("Class Date")) {
			originalList = printWebElements(ClassDateList);
		}else if (type.equals("Staff Name")) {
			originalList = printWebElements(StaffNameList);
		}
		
		else {
			originalList = printWebElements(classDescripList);
		}
		return originalList;
	}

	
	public List<Date> getClassDatesOriginalList() {
		
		// List to store the dates
        List<Date> dates = new ArrayList<>();

        // Define the date format used on the webpage
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy"); // Adjust the format accordingly

        // Extract dates from elements and convert to Date objects
        for (WebElement element : ClassDateList) {
            String dateStr = element.getText(); // Get the text representing the date
            try {
                  Date classdates =  dateFormat.parse(dateStr); // Parse the string to Date object
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
    for (WebElement element : ClassDateList) {
        String dateStr = element.getText(); // Get the text representing the date
        try {
              Date classdates =  dateFormat.parse(dateStr); // Parse the string to Date object
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
		String footerMessageText = driver.findElement(By.xpath("//p-table/div/div[2]/div"))
				.getText();
		String[] lastPageFooterEntry = footerMessageText.split("are ");
		lastPageFooterEntryCount = Integer.parseInt(lastPageFooterEntry[1].trim().split(" ")[0]);
		return lastPageFooterEntryCount;
		
	}
	
	

}
