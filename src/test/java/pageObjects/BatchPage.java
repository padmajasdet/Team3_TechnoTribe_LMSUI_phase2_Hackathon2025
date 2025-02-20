package pageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.ElementUtil;

public class BatchPage{

	

	private WebDriver driver;
	private ElementUtil util;
	private By homeMenu = By.xpath("//span[normalize-space()='Home']");
	private By batchMenu = By.xpath("//span[contains(text(),'Batch')]");
	private By batchTitle = By.xpath("//*[contains(text(),'Manage Batch')]");
	private By batchPageTitle = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	private By deleteBatchHeader = By.xpath("//div[@class='box']//button[@icon=\"pi pi-trash\"]");
	
	//Batch page - Table locators
	private By paginationBotton = By.xpath("//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']");
	private By tableLocator = By.xpath("//*[@class='mat-card-content']");
	 private By tableHeaderLocator = By.xpath("//*[@class='mat-card-content']//thead/tr");
	private String editIconLocator = ".//span[@class='p-button-icon pi pi-pencil']";
	private String deleteIconLocator = ".//span[@class='p-button-icon pi pi-trash']";
	private String checkboxLocator = ".//span[@class='p-checkbox-icon']"; 
	private String sortIconLocator = ".//*[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']";

	
	//Batch Page - Add new batch popup locators
	private By addNewBatch = By.xpath("//button[normalize-space()='Add New Batch']");
	private By addNewBatchTitle = By.xpath("//*[contains(text(),'Batch Details')]");
	private By batchAddName = By.id("batchName");
	private By batchAddDesc = By.id("batchDescription");
	private By batchAddNoOfClasses = By.id("batchNoOfClasses");
	private By batchAddSuccess = By.cssSelector(".p-toast-detail");
	

	public BatchPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
	
	/**
	 * This method retrieves title of Batch Page
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
	
	public Boolean deleteBatchHeaderButton() {
		return util.isElementEnabled(deleteBatchHeader);
	}
	
	public Boolean isPaginationAvailable() {
		return util.isElementDisplayed(paginationBotton);
	}
	
	

	 public boolean isElementPresent(String elementType, String locationType) {
	        // Select the locator based on element type (edit, delete, checkbox, or sort icon)
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

	        if (actualHeaderCells.size() != expectedHeaders.size()+1) {
	            System.out.println("Mismatch in number of header cells!");
	            return false;
	        }

	        for (int i = 1; i < expectedHeaders.size(); i++) {
	            String actualHeaderText = actualHeaderCells.get(i).getText().trim();
	            if (!actualHeaderText.equals(expectedHeaders.get(i))) {
	                System.out.println("Header mismatch at index " + i + ": expected '" + expectedHeaders.get(i) + "', but found '" + actualHeaderText + "'");
	                return false;
	            }
	        }
	        return true;
	    }
	
	 
}
