package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ElementUtil;
import utilities.ExcelReader;
import utilities.ReadConfig;

public class BatchPage{

	
	 
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
	
	//Batch page - Table locators
	private By paginationBotton = By.xpath("//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']");
	private By tableLocator = By.xpath("//*[@class='mat-card-content']");
	 private By tableHeaderLocator = By.xpath("//*[@class='mat-card-content']//thead/tr");
	private String editIconLocator = ".//span[@class='p-button-icon pi pi-pencil']";
	private String deleteIconLocator = ".//span[@class='p-button-icon pi pi-trash']";
	private String checkboxLocator = ".//span[@class='p-checkbox-icon']"; 
	private String sortIconLocator = ".//*[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']";
	private By deleteTitle = By.xpath("//span[normalize-space()='Confirm']");
	private By deleteNoButton = By.xpath("//span[normalize-space()='No']");
	private By deleteYesButton = By.xpath("//span[normalize-space()='Yes']");
	private By deleteCloseButton = By.xpath("//*[@class='p-dialog-header-icons ng-tns-c118-11']//span");
	private By deleteAllButton = By.xpath("//button[@class='p-button-danger p-button p-component p-button-icon-only']//span[@class='p-button-icon pi pi-trash']");

	//Batch Page - Add new batch locators
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
	private By invalidError = By.xpath("//small[@id='text-danger']");
	private By closeButton = By.xpath("//*[@header='Batch Details']//button[@type='button']");
	
	//Batch Page - Pagination locators
	
	private	By prevPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-prev')]");
	private	By firstPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-first')]");
	private By thirdPaginatorBtn = By.xpath("//button[normalize-space()='3']");
	private	By nextPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-next')]");
	private	By lastPaginatorBtn = By.xpath("//button[contains(@class,'p-paginator-last')]");
	
	public static String BatchName;  
	private String filePath ; 
    private String sheetName = "Batch";
    private int beforeCount;
	private int afterCount;
	private int selectedRows;
     
	public BatchPage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
		this.readConfig = new ReadConfig();
		this.filePath = readConfig.getExcelPath();
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
	
	 public boolean isElementPresent(String elementType, String locationType) {
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
	        } else if (locationType.equalsIgnoreCase("row")) { 
	        	// If the location is row, validate in the table rows
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
	
	 public String[] verifyTableHeaders() {
	        WebElement headerRow = driver.findElement(tableHeaderLocator);
	        List<WebElement> actualHeaderCells = headerRow.findElements(By.tagName("th"));
	        String[] headers = new String[actualHeaderCells.size() - 1];
			for (int i = 1; i < actualHeaderCells.size(); i++) {
				headers[i - 1] = actualHeaderCells.get(i).getText();
			}
	        return headers;
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

		public void selectProgramNameListBox(String testcaseName) {
			String excelProgramName = selectDataFromExcel(testcaseName, "ProgramName");
			 // Check if it should be replaced with the chain variable
		    if (excelProgramName.equalsIgnoreCase("chaining")) {
		        excelProgramName = ProgramPage.getProgramName(); // Use chain variable
		    }
			WebElement programNameListBox = driver.findElement(
					By.xpath("//ul[@role='listbox']/p-dropdownitem/li[@aria-label='"+excelProgramName+"']"));
			programNameListBox.click();
		}
		
		
		public String selectDataFromExcel(String testcaseName, String columnName) {	
			testData = ExcelReader.getTestData(sheetName, testcaseName);
			return  testData.get(columnName);
		} 
		
		public void enterBatchNamePrefix() {
			util.doSendKeys(addBatchFirstName, selectDataFromExcel("invalidBatchNamePrefix", "BatchNamePrefix"));
		}

		public Boolean isBatchNamePrefixEditable() {
			return util.isFieldEditable(addBatchFirstName);
			
		}
		public void enterBatchNameSuffix() {
			util.doSendKeys(addBatchName, selectDataFromExcel("invalidBatchNameSuffix", "BatchName"));
		}
		
	    public String getBatchNamePrefix() {
	        return driver.findElement(addBatchFirstName).getDomProperty("value");
	    }
	    
	    public void getActiveStatusRadioButton() { 
	    	WebElement activeStatusElement = driver.findElement(activeStatus);
	    	((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", activeStatusElement);
	    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    	wait.until(ExpectedConditions.invisibilityOfElementLocated(By.className("cdk-overlay-backdrop")));
	    	((JavascriptExecutor) driver).executeScript("arguments[0].click();", activeStatusElement); }

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
			testData = ExcelReader.getTestData(sheetName,testcaseName);
			selectProgramNameDD();
			selectProgramNameListBox(testcaseName);
			String finalBatchNamePrefix = selectDataFromExcel(testcaseName, "ProgramName");
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
			int noOfClasses = Integer.parseInt(noOfClassesStr.split("\\.")[0]); 
			String newnoOfClasses= String.valueOf(noOfClasses);
			util.doSendKeys(addBatchNoOfClasses, newnoOfClasses);
			if (saveCancel.equalsIgnoreCase("Save")) {
				saveButtonClick();
				String toastMessage = getToast(); 
			    if (!toastMessage.isEmpty()) { // If toast appears, process it
			        if (toastMessage.equalsIgnoreCase("Successful")) {
			            if (testcaseName.equalsIgnoreCase("validAll")) {
			                System.out.println("Batch created successfully - chaining");
			                String finalBatchName = finalBatchNamePrefix + newBatchName;
			                System.out.println("Batch Name: " + finalBatchName);
			                setBatchName(finalBatchName);
			            } else {
			                System.out.println("Batch created successfully - " +toastMessage);
			            }
			        } else {
			            System.out.println("Unexpected Toast Message: " + toastMessage);
			        }
			    } else { 
			        // If no toast message appears, fetch the error message
			        String errorMessage = getErrorMessage();
			        if (!errorMessage.isEmpty()) {
			            System.out.println("Error: " + errorMessage);
			        } else {
			            System.out.println("No toast or error message found.");
			        }
			    }
				
			} else {
				cancelButtonClick();
			}
		}
		
		 // Setter method to set the batch name
	    public static void setBatchName(String batchName) {
	        BatchPage.BatchName = batchName;  // Store the batch name in the static variable
	    }

	    // Getter method to get the batch name
	    public static String getBatchName() {
	        return BatchName;  // Return the stored batch name
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
		    	actionIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@icon='pi pi-pencil']")));
		    } else if (actionType.equalsIgnoreCase("delete")) {
		        actionIcon = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='action']//button[@icon='pi pi-trash']")));
		    } else {
		        throw new IllegalArgumentException("Invalid action type: " + actionType);
		    }
		    // Scroll into view and click the action icon
		    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", actionIcon);
		    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", actionIcon);
		}
		
		public void editAllDetails(String saveCancel, String testcaseName) {
			testData = ExcelReader.getTestData(sheetName,testcaseName);
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
			                System.out.println("Batch created successfully");  
			        }
			     else { 
			        String errorMessage = getErrorMessage();
			        if (!errorMessage.isEmpty()) {
			            System.out.println("Error: " + errorMessage);
			        } else {
			            System.out.println("No toast or error message found.");
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
		public void clickDeleteButtons(String buttonName) {
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
			clickdeleteAllButton();
			clickDeleteButtons("yes");

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
		
		
		

		
		
		
		
		
		
}
