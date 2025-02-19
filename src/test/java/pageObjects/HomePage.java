package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil util;
	
	private By dashboardHeader = By.xpath("//div[normalize-space()='Dashboard']");
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
	
	public boolean isDashboardHeaderVisible() {
		return util.isElementDisplayed(dashboardHeader);
	}
}
