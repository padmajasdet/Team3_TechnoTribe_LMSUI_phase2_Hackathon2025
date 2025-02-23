package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.languagetool.JLanguageTool;
import org.languagetool.language.AmericanEnglish;
import org.languagetool.rules.RuleMatch;

import java.io.IOException;
import java.util.List;

import utilities.ElementUtil;

public class HomePage extends CommonPage {

	private WebDriver driver;
	private ElementUtil util;

	private By dashboardHeader = By.xpath("//div[normalize-space()='Dashboard']");
	private By dashboardTitile = By.xpath("//*[contains(text(),' Dashboard')]");
	private By lmsTitleBar = By
			.xpath("//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']");
	private By lmsTitile = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	private By navigationelement = By.xpath(
			"//button[@class='mat-focus-indicator mat-button mat-button-base' or @class='mat-focus-indicator mat-button mat-button-base ng-star-inserted' or @class='mat-focus-indicator mat-menu-trigger mat-button mat-button-base'  ]");
	private By pie_chart = By.xpath("//app-doughnutchart");
	private By welcomeMessage = By.xpath("//strong[normalize-space()='Welcome sdetnumpyninja@gmail.com']");
	private By barChartMembers = By.xpath("//mat-card-content[@class='mat-card-content']//canvas[@class='chartjs-render-monitor']");
	private By userCount = By.xpath("//div[normalize-space()='49']");
	private By staffCount = By.xpath("//div[normalize-space()='21']");
	private By programCount = By.xpath("//div[normalize-space()='73']");
	private By batchCount = By.xpath("//div[normalize-space()='59']");
	private By staffTable = By.xpath("//mat-table[@class='mat-table cdk-table table-container mat-elevation-z8']");
	private By staffTablePagination = By.xpath("//div[@class='mat-paginator-range-actions']");
	private By previousPageIcon = By.xpath("//button[@aria-label='First page']//span[@class='mat-button-wrapper']//*[name()='svg']");
	private By firstPageIcon = By.xpath("//*[name()='path' and contains(@d,'M15.41 7.4')]");
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}

	public boolean isDashboardHeaderVisible() {
		return util.isElementDisplayed(dashboardHeader);
	}

	public Point lmsTitleLocation() {
		WebElement title_element = driver.findElement(lmsTitleBar);
		return title_element.getLocation();

	}

	public String getDashboardText() {
		return util.getElementText(dashboardTitile);

	}

	public String getLMSPageTitleText() {
		return util.getElementText(lmsTitile);
	}

	public int lmsTitleLocationRightNavigation() {
		WebElement title_element = driver.findElement(lmsTitleBar);
		// Get the X-coordinate of the element
		int xCoordinate = title_element.getLocation().getX();
		// Get the width of the element;
		int width = title_element.getSize().getWidth();
		// Calculate the right-most position
		int rightMostLocation = xCoordinate + width;
		return rightMostLocation;

	}

	public List<WebElement> getElements() {

		List<WebElement> elements = util.getElements(navigationelement);

		return elements;

	}
	
	public void navigateBack() {
		
		driver.navigate().back();
		util.getPageLoadStatus();
	}

	public boolean isPieChartDisplayed() {
		return util.isElementDisplayed(pie_chart);
	}
	
	public String welcomeMessage() {
		return util.getElementText(welcomeMessage);
	}
	public boolean isBarChartMembersDisplayed() {
		return util.isElementDisplayed(barChartMembers);
	}
	
	public String userCount() {
		return util.getElementText(userCount);
	}
	public String staffCount() {
		return util.getElementText(staffCount);
	}
	
	public String programCount() {
		return util.getElementText(programCount);
	}
	public String batchCount() {
		return util.getElementText(batchCount);
	}
	//staffTable
	public boolean isStaffTableDisplayed() {
		return util.isElementDisplayed(staffTable);
	}
	
	public boolean isStaffTablePaginationDisplayed() {
		return util.isElementDisplayed(staffTablePagination);
	}
	public boolean isPreviousPagePaginationDisabled() {
		return (!util.isElementEnabled(previousPageIcon));
	}
	
	public boolean isFirstPagePaginationDisabled() {
		return (!util.isElementEnabled(firstPageIcon));
	}
	public int getStaffRowcount() {
	
		List<WebElement> row_count = util.getElements(staffTable);
		
		
		return row_count.size();

	}
	public void textDashBooardSpellings() {
		
		String dashBoardText = util.getElementText(dashboardTitile);
		  	  
		JLanguageTool languageTool = new JLanguageTool (new AmericanEnglish());
		  
		  try {
			  
			  List<RuleMatch> errors = languageTool.check(dashBoardText);  //perform spell check  in navigation bar text
			   if(errors.isEmpty()) {
				   System.out.println("No spelling mistake");
			   }else {
				   System.out.println("Spelling mistake found");
				   for (RuleMatch error : errors) {
					  
					   System.out.println("error on word:" + error.getLine() +", column" + error.getColumn() + ": " + error.getMessage());  
				   }
			   }
		  }catch (IOException e) {
			  
			  e.printStackTrace();
		  }		  
	  }
	
public void textLMSSpellings() {
		
		String lmsTitleText = util.getElementText(lmsTitile);
		  	  
		JLanguageTool languageTool = new JLanguageTool (new AmericanEnglish());
		  
		  try {
			  
			  List<RuleMatch> errors = languageTool.check(lmsTitleText);  //perform spell check  in navigation bar text
			   if(errors.isEmpty()) {
				   System.out.println("No spelling mistake");
			   }else {
				   System.out.println("Spelling mistake found");
				   for (RuleMatch error : errors) {
					  
					   System.out.println("error on word:" + error.getLine() +", column" + error.getColumn() + ": " + error.getMessage());  
				   }
			   }
		  }catch (IOException e) {
			  
			  e.printStackTrace();
		  }		  
	  }
}
