package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.Log;
import utilities.ElementUtil;

public class CommonPage {
	protected WebDriver driver;
	protected ElementUtil util;
	protected int selectedRows;
	protected int beforeCount;
	protected int afterCount;
	//Menu Locators
	protected By menu_Home = By.id("dashboard");
	protected By menu_Program = By.xpath("//span[contains(text(),'Program')]");
	protected By menu_Batch = By.xpath("//span[contains(text(),'Batch')]");
	protected By menu_Class = By.xpath("//span[contains(text(),'Class')]");
	protected By menu_logout = By.xpath("//button[@id='logout']");

	// Table locators
	protected By paginationButtons = By
			.xpath("//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']");
	protected By tableLocator = By.xpath("//*[@class='mat-card-content']");
	protected By tableHeaderLocator = By.xpath("//*[@class='mat-card-content']//thead/tr");
	protected String editIconLocator = ".//span[@class='p-button-icon pi pi-pencil']";
	protected String deleteIconLocator = ".//span[@class='p-button-icon pi pi-trash']";
	protected String checkboxLocator = ".//span[@class='p-checkbox-icon']";
	protected String sortIconLocator = ".//*[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']";
	protected By deleteNoButton = By.xpath("//span[normalize-space()='No']");
	protected By deleteYesButton = By.xpath("//span[normalize-space()='Yes']");
	protected By deleteCloseButton = By
			.xpath("//*[contains(@class,' p-dialog-header-close p-link ng-star-inserted')]/..");
	protected By deleteAllButton = By.xpath(
			"//button[@class='p-button-danger p-button p-component p-button-icon-only']//span[@class='p-button-icon pi pi-trash']");

	protected By deleteConfirmationPopUp = By.xpath(
			"//div[@class='ng-trigger ng-trigger-animation ng-tns-c118-10 p-dialog p-confirm-dialog p-component ng-star-inserted']");
	protected By toastMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Successful']");
	
	protected By toastErrorMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Failed']");
	protected By toastErrorMessageDetail = By.xpath("//div[contains(@class, 'p-toast-detail') and text()='programName Must contain only letters and sometimes hyphens']");
	protected By invalidError = By.xpath("//small[@class='p-invalid ng-star-inserted']");
	

	

	public CommonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); 
		util = new ElementUtil(this.driver);
	}


	@FindBy(xpath = "//button[@id='logout']")
	WebElement logout;

	

	public String getPageTitle() {

		return driver.getTitle();
	}

	public void logout() {
		util.doClick(menu_logout);
	}

	public Object selectOptionNavigationMenuBar(String menuName) throws Exception {

		switch (menuName.toLowerCase().trim()) {
		case "program":
			util.doClick(menu_Program);
			return new ProgramPage(driver);

		case "batch":
			util.doClick(menu_Batch);
			return new BatchPage(driver);

		case "class": 
			util.doClick(menu_Class);
			return new ClassPage(driver);

		case "logout":
			util.doClick(menu_logout);
			return new LoginPage(driver);
			
		default:
			throw new Exception("Something went wrong!");
		}

	}
	
	public String getErrorMessage() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(invalidError)));
		String err = driver.findElement(invalidError).getText();
		return err;
	}

	public String getToast() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(toastMessage)));
		String toastMessageValue = driver.findElement(toastMessage).getText();
		Log.logInfo("Toast Message >>"+toastMessageValue);
		return toastMessageValue;
	}
	
	public String getErrorToast() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(toastErrorMessage)));
		String toastMessageValue = driver.findElement(toastErrorMessage).getText();
		Log.logInfo("Toast Message >>"+toastMessageValue);
		return toastMessageValue;
	}
	
	public String getErrorToastMessageContent() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(1));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(toastErrorMessageDetail)));
		String toastMessageValueContent = driver.findElement(toastErrorMessageDetail).getText();
		Log.logInfo("Toast Message >>"+toastMessageValueContent);
		return toastMessageValueContent;
	}

	public boolean isElementPresent(String elementType, String locationType) {
		// Select the locator based on element type (edit, delete, checkbox, or sort
		// icon)
		String elementLocator;
		switch (elementType.toLowerCase()) {
		case "edit-icon":
			elementLocator = editIconLocator;
			break;
		case "delete-icon":
			elementLocator = deleteIconLocator;
			break;
		case "checkbox":
			elementLocator = checkboxLocator;

			break;
		case "sort-icon":
			elementLocator = sortIconLocator;
			break;
		default:
			throw new IllegalArgumentException("Invalid element type: " + elementType);
		}

		// If the location is header, validate in the table header
		if (locationType.equalsIgnoreCase("header")) {
			WebElement headerRow = driver.findElement(tableHeaderLocator);
			return !headerRow.findElements(By.xpath(elementLocator)).isEmpty();
		} else if (locationType.equalsIgnoreCase("row")) { // If the location is row, validate in the table rows
			WebElement table = driver.findElement(tableLocator);
			List<WebElement> rows = table.findElements(By.xpath(".//tbody/tr"));

			boolean allRowsHaveElement = true;

			for (int i = 0; i < rows.size(); i++) {
				WebElement row = rows.get(i);
				boolean hasElement = !row.findElements(By.xpath(elementLocator)).isEmpty();

				if (!hasElement) {
					Log.logInfo(elementType + " is missing in row: " + (i + 1));
					allRowsHaveElement = false;
				}
			}
			return allRowsHaveElement;
		} else {
			throw new IllegalArgumentException("Invalid location type: " + locationType);
		}
	}

	public boolean verifyTableHeaders(List<String> expectedHeaders) {

		WebElement headerRow = driver.findElement(tableHeaderLocator);
		List<WebElement> actualHeaderCells = headerRow.findElements(By.tagName("th"));

		if (actualHeaderCells.size() != expectedHeaders.size() + 1) {
			Log.logInfo("Mismatch in number of header cells!");
			return false;
		}

		for (int i = 1; i < expectedHeaders.size(); i++) {
			String actualHeaderText = actualHeaderCells.get(i).getText().trim();
		
			if (!actualHeaderText.equals(expectedHeaders.get(i - 1))) {
				Log.logInfo("Header mismatch at index " + i + ": expected '" + expectedHeaders.get(i)
						+ "', but found '" + actualHeaderText + "'");
				return false;
			}
		}
		return true;
	}

	public Boolean isPaginationAvailable() {
		return util.isElementDisplayed(paginationButtons);
	}

	public void clickDeleteButtons(String buttonName) throws Exception {
		try {
		WebElement overlay = driver.findElement(By.className("cdk-overlay-backdrop"));
		overlay.click();
		}
		catch(Exception e) {}
		if (buttonName.equalsIgnoreCase("yes")) {
			util.clickElementByJS(deleteYesButton, driver);
		} else if (buttonName.equalsIgnoreCase("no")) {
			util.doClick(deleteNoButton);
		} else {
			util.doClick(deleteCloseButton);
		}
	}

	public void clickdeleteAllButton() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));
		WebElement deleteButton = driver
				.findElement(By.xpath("//*[@class='mat-card-title']//button[contains(@class, 'p-button-danger')]"));
		wait.until(ExpectedConditions.visibilityOf(deleteButton));
		wait.until(ExpectedConditions.elementToBeClickable(deleteButton));

		try {
			deleteButton.click();
		} catch (ElementClickInterceptedException e) {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", deleteButton);
		}

	}

	public void storeBeforeCount() {
		String countText = driver.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']"))
				.getText();
		String[] parts = countText.split("of ");
		this.beforeCount = Integer.parseInt(parts[1].trim().split(" ")[0]);
	}

	public void selectCheckboxes(int rows) {

		List<WebElement> checkboxes = driver
				.findElements(By.xpath("//tbody[@class='p-datatable-tbody']//div[@role='checkbox']"));

		if (rows > checkboxes.size()) {
			throw new IllegalArgumentException("Count exceeds the number of available checkboxes");
		}
		this.selectedRows = rows;
		for (int i = 0; i < rows; i++) {
			WebElement checkbox = checkboxes.get(i);
			if (!checkbox.isSelected()) {

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
				wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));

				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", checkbox);
				try {

					wait.until(ExpectedConditions.elementToBeClickable(checkbox)).click();
				} catch (ElementClickInterceptedException e) {

					((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
				}
			}
		}

	}

	public boolean verifyDeleteProgramPopUp() {

		return util.isElementDisplayed(deleteConfirmationPopUp);

	}

	public void storeAfterCount() {
		String countText = driver.findElement(By.xpath("//span[@class='p-paginator-current ng-star-inserted']"))
				.getText();
		String[] parts = countText.split("of ");
		this.afterCount = Integer.parseInt(parts[1].trim().split(" ")[0]);
	}

	public boolean validateCount() {
		boolean flag = false;
		int expectedCount = beforeCount - selectedRows;
		storeAfterCount();
		if (afterCount != expectedCount) {
			throw new AssertionError(
					"Count validation failed: Expected " + expectedCount + ", but found " + afterCount);
		} else {
			flag = true;
		}
		return flag;
	}
}
