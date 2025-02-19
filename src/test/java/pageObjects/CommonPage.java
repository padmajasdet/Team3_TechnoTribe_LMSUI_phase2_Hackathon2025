package pageObjects;

import java.time.Duration;

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
	
//	public void selectOptionNavigationMenuBar(String menuOption) {
//		
//		WebElement menuElement;
//
//		switch (menuOption.toLowerCase()) {
//	        case "dashboard":
//	            menuElement = menu_Home;
//	            util.doClick(menuElement);
//	             break;
//	           // return new HomePage(driver);
//	          
//	        case "program":
//	            menuElement = menu_Program;
//	            break;
//	        case "batch":
//	            menuElement = menu_Batch;
//	            break;
//	        case "class":
//	            menuElement = menu_Class;
//	            break;
//	        case "logout":
//	            menuElement = logout;
//	            break;
//	        default:
//	            throw new IllegalArgumentException("Invalid menu option: " + menuOption);
//	    }
//		
//		  
//		  try {
//			    //menuElement.click();  // Regular click
//			  util.doClick(menuElement);
//			} catch (Exception e) {
//			    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", menuElement);
//			}
//	}
	
	
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

}
