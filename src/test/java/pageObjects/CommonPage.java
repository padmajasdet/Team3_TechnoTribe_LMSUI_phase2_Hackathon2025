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

import utilities.ElementUtil;

public class CommonPage {
	private WebDriver driver;
	private ElementUtil util;

	public CommonPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this); // Initialize @FindBy elements
		util = new ElementUtil(this.driver);
	}

	// Table locators
	private By paginationButtons = By
			.xpath("//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']");
	private By tableLocator = By.xpath("//*[@class='mat-card-content']");
	private By tableHeaderLocator = By.xpath("//*[@class='mat-card-content']//thead/tr");
	private String editIconLocator = ".//span[@class='p-button-icon pi pi-pencil']";
	private String deleteIconLocator = ".//span[@class='p-button-icon pi pi-trash']";
	private String checkboxLocator = ".//span[@class='p-checkbox-icon']";
	private String sortIconLocator = ".//*[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']";
	private By deleteNoButton = By.xpath("//span[normalize-space()='No']");
	private By deleteYesButton = By.xpath("//span[normalize-space()='Yes']");
	private By deleteCloseButton = By.xpath("//*[contains(@class,' p-dialog-header-close p-link ng-star-inserted')]/..");
	private By deleteAllButton = By.xpath(
			"//button[@class='p-button-danger p-button p-component p-button-icon-only']//span[@class='p-button-icon pi pi-trash']");

	By deleteConfirmationPopUp = By.xpath(
			"//div[@class='ng-trigger ng-trigger-animation ng-tns-c118-10 p-dialog p-confirm-dialog p-component ng-star-inserted']");
	private By toastMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Successful']");
	private int selectedRows;
	private int beforeCount;
	private int afterCount;

	@FindBy(xpath = "//button[@id='logout']")
	WebElement logout;

	@FindBy(id = "dashboard")
	WebElement menu_Home;

	@FindBy(xpath = "//span[contains(text(),'Program')]")
	WebElement menu_Program;

	@FindBy(xpath = "//span[contains(text(),'Batch')]")
	WebElement menu_Batch;
	@FindBy(xpath = "//span[contains(text(),'Class')]")
	WebElement menu_Class;

	public String getPageTitle() {

		return driver.getTitle();
	}

	public void logout() {
		logout.click();
	}

	public Object selectOptionNavigationMenuBar(String menuName) throws Exception {

		switch (menuName.toLowerCase().trim()) {
		case "program":
			util.doClick(menu_Program);
			return new ProgramPage(driver);

		case "batch":
			util.doClick(menu_Batch);
			return new BatchPage(driver);

//		case "class":
//			//gotoClassMenuThroughTABBING();
//			return new ClassPage(driver);

		case "logout":
			util.doClick(logout);
			return new LoginPage(driver);
		default:
			throw new Exception("Something went wrong!");
		}

	}

	public String getToast() {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(toastMessage)));
		String toastMessageValue = driver.findElement(toastMessage).getText();
		return toastMessageValue;
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
					System.out.println(elementType + " is missing in row: " + (i + 1));
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
			System.out.println("Mismatch in number of header cells!");
			return false;
		}
	 

		for (int i = 1; i < expectedHeaders.size(); i++) {
			String actualHeaderText = actualHeaderCells.get(i).getText().trim();
			System.out.println(actualHeaderText);
			if (!actualHeaderText.equals(expectedHeaders.get(i - 1))) {
				System.out.println("Header mismatch at index " + i + ": expected '" + expectedHeaders.get(i)
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
		WebElement overlay = driver.findElement(By.className("cdk-overlay-backdrop"));
		overlay.click();
		if (buttonName.equalsIgnoreCase("yes")) {
			util.doClick(deleteYesButton);
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
//		clickdeleteAllButton();
//		verifyDeleteProgramPopUp();
//		clickDeleteButtons("yes");

	}
	public void verifyDeleteProgramPopUp() {

		util.isElementDisplayed(deleteConfirmationPopUp);

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
			flag=true;
		}
		return flag;
	}
}
