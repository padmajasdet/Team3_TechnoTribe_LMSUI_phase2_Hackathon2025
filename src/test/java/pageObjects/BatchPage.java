package pageObjects;

import java.time.Duration;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import utilities.ElementUtil;
import utilities.ExcelReader;
import utilities.ReadConfig;

public class BatchPage{

	
	  public static String BatchName; //apiChaining	
	  
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

	
	//Batch Page - Add new batch popup locators
	private By addNewBatch = By.xpath("//button[normalize-space()='Add New Batch']");
	private By addNewBatchTitle = By.xpath("//*[contains(text(),'Batch Details')]");
	private By addBatchProgramNameDD = By.xpath("//div[@role='button']");
	private By addBatchFirstName = By.xpath("//input[@id=\"batchProg\"]");
	private By addBatchName = By.id("batchName");
	private By addBatchDesc = By.id("batchDescription");
	private By addBatchStatus = By.xpath("//*[@class=\"radio ng-star-inserted\"]");
	private By activeStatus = By.xpath("(//p-radiobutton[@name='category']//div[@class='p-radiobutton-box'])[1]");
	private By inactiveStatus = By.xpath("(//p-radiobutton[@name='category']//div[@class='p-radiobutton-box'])[2]");
	private By addBatchNoOfClasses = By.id("batchNoOfClasses");
	private By saveButton = By.xpath("//button[@label='Save']");
	private By cancelButton = By.xpath("//button[@label='Cancel']");
	private By toastMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Successful']");
	

	 private String filePath ; // Excel file location
     private String sheetName = "Batch"; // Sheet name"; 
     
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
	
	public Boolean deleteBatchHeaderButton() {
		return util.isElementEnabled(deleteBatchHeader);
	}
	
	public Boolean isPaginationAvailable() {
		return util.isElementDisplayed(paginationBotton);
	}
	
	 
	 public boolean isAddBatchNameEnabled() {
	        return util.isElementEnabled(addBatchName);
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
			WebElement programNameListBox = driver.findElement(
					By.xpath("//ul[@role='listbox']/p-dropdownitem/li[@aria-label='" + selectDataForProgramName(testcaseName) + "']"));
			programNameListBox.click();
		}
		
		public String selectDataForProgramName(String testcaseName) {
			
			testData = ExcelReader.getTestData(filePath, sheetName, testcaseName);
		      
			return  testData.get("ProgramName");
		} 

	    // Get Batch Name Prefix Value
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
			testData = ExcelReader.getTestData(filePath, sheetName,testcaseName);
			selectProgramNameDD();
			selectProgramNameListBox(testcaseName);
			String finalBatchNamePrefix = selectDataForProgramName(testcaseName);
			 // Get the ProgramName value from Excel
		  //  String excelProgramName = testData.get("ProgramName");

//		    // Check if the program name in Excel is "apichainin" and replace it with the variable from ProgramData
//		    if (excelProgramName.equalsIgnoreCase("apichainin")) {
//		        excelProgramName = ProgramPage.getProgramName();  // Replace with the global variable
//		    }

		    // Now use the modified program name in the UI
		//    util.doSendKeys(addBatchName, excelProgramName);
		    
			// Get the current BatchName value and increment it
			String batchNameStr = testData.get("BatchName");
			int batchNumber;
			try {
			    batchNumber = Integer.parseInt(batchNameStr.split("\\.")[0]); // Convert to integer
			} catch (NumberFormatException e) {
			    batchNumber = 0; // Default value if parsing fails
			}
			batchNumber++; // Increment the value by 1
		    
		    // Convert it back to a string to use in the UI and Excel
		    String newBatchName = String.valueOf(batchNumber);
			util.doSendKeys(addBatchName, newBatchName);
			  // Update the BatchName in Excel for the next run
		    ExcelReader.updateTestData(filePath, sheetName, testcaseName, "BatchName", newBatchName);

			util.doSendKeys(addBatchDesc, testData.get("Description"));
			getActiveStatusRadioButton();
			String noOfClassesStr = testData.get("NoOfClasses");
			int noOfClasses = Integer.parseInt(noOfClassesStr.split("\\.")[0]); 
			String newnoOfClasses= String.valueOf(noOfClasses);
			util.doSendKeys(addBatchNoOfClasses, newnoOfClasses);
			
			if (saveCancel.equalsIgnoreCase("Save")) {
				saveButtonClick();
				if (getToast().equalsIgnoreCase("Successful") && testcaseName.equalsIgnoreCase("validAll")) {
					System.out.println("Batch created successfully");
					String finalBatchName = finalBatchNamePrefix+ newBatchName;
					System.out.println("Batch Name: " + finalBatchName);
					/// Set the batch name after creation
	                setBatchName(finalBatchName);
	                
					
				} else {
					System.out.println("Batch creation failed");
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
			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
			wait.until(ExpectedConditions.visibilityOf(driver.findElement(toastMessage)));
			String toastMessageValue = driver.findElement(toastMessage).getText();
			return toastMessageValue;
		}
		
		
	
}
