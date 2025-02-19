package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import utilities.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By dashboardHeader = By.xpath("//div[normalize-space()='Dashboard']");
	private By lmsTitleBar = By.xpath("//mat-toolbar[@class='mat-toolbar mat-primary mat-toolbar-single-row ng-star-inserted']");
	
	public HomePage(WebDriver driver) {
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
}
