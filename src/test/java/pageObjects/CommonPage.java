package pageObjects;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
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
	    PageFactory.initElements(driver, this);  // Initialize @FindBy elements
	    util = new ElementUtil(this.driver);
	}
	
	//Table locators
		private By paginationButtons = By.xpath("//div[@class='p-paginator-bottom p-paginator p-component ng-star-inserted']");
		private By tableLocator = By.xpath("//*[@class='mat-card-content']");
		 private By tableHeaderLocator = By.xpath("//*[@class='mat-card-content']//thead/tr");
		private String editIconLocator = ".//span[@class='p-button-icon pi pi-pencil']";
		private String deleteIconLocator = ".//span[@class='p-button-icon pi pi-trash']";
		private String checkboxLocator = ".//span[@class='p-checkbox-icon']"; 
		private String sortIconLocator = ".//*[@class='p-sortable-column-icon pi pi-fw pi-sort-alt']";
		
		By toastMessage = By.xpath("//div[contains(@class, 'p-toast-summary') and text()='Successful']");
	
	 @FindBy(xpath="//button[@id='logout']") 
	 WebElement logout;
	 
	 @FindBy(id="dashboard") 
	 WebElement menu_Home;
	 
	 @FindBy(xpath="//span[contains(text(),'Program')]") 
	 WebElement menu_Program;
	 
	 @FindBy(xpath="//span[contains(text(),'Batch')]") 
	 WebElement menu_Batch;
	 @FindBy(xpath="//span[contains(text(),'Class')]") 
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

	        System.out.println("Header 0>>>"+expectedHeaders.get(0).toString());
	        System.out.println("Header 1>>>"+expectedHeaders.get(1).toString());
	        System.out.println("Header 2>>>"+expectedHeaders.get(2).toString());
	        System.out.println("Header 3>>>"+expectedHeaders.get(3).toString());
	        
	        System.out.println("Header 0>>>"+actualHeaderCells.get(0).getText());
	        System.out.println("Header 1>>>"+actualHeaderCells.get(1).getText());
	        System.out.println("Header 2>>>"+actualHeaderCells.get(2).getText());
	        System.out.println("Header 3>>>"+actualHeaderCells.get(3).getText());
	        if (actualHeaderCells.size() != expectedHeaders.size()+1) {
	            System.out.println("Mismatch in number of header cells!");
	            return false;
	        }

	        for (int i = 1; i < expectedHeaders.size(); i++) {
	            String actualHeaderText = actualHeaderCells.get(i).getText().trim();
	            System.out.println(actualHeaderText);
	            if (!actualHeaderText.equals(expectedHeaders.get(i))) {
	                System.out.println("Header mismatch at index " + i + ": expected '" + expectedHeaders.get(i) + "', but found '" + actualHeaderText + "'");
	                return false;
	            }
	        }
	        return true;
	    }
	 
	 public Boolean isPaginationAvailable() {
			return util.isElementDisplayed(paginationButtons);
		}

}
