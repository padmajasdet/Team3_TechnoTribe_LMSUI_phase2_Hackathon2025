package pageObjects;

import org.openqa.selenium.WebDriver;

import utilities.ElementUtil;

public class HomePage {

	private WebDriver driver;
	private ElementUtil util;
	
	public HomePage(WebDriver driver) {
		this.driver = driver;
		util = new ElementUtil(this.driver);
	}
}
