package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

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

}
