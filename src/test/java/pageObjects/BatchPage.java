package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import utilities.ElementUtil;

public class BatchPage {

	

	private WebDriver driver;
	private ElementUtil util;
	private By homeMenu = By.xpath("//button[@id='dashboard']");
	private By batchMenu = By.xpath("//span[contains(text(),'Batch')]");
	private By batchTitle = By.xpath("//*[contains(text(),'Manage Batch')]");
	private By batchPageTitle = By.xpath("//*[contains(text(),'LMS - Learning Management System')]");
	private By deleteBatchHeader = By.xpath("//div[@class='box']//button[@icon=\"pi pi-trash\"]");
	
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
	
	public void homeMenuClick() {
		 util.doClick(homeMenu);
	}
	
	public void batchMenuClick() {
		util.doClick(batchMenu);
	}
	
	public Boolean deleteBatchHeaderButton() {
		return util.isElementEnabled(deleteBatchHeader);
	}
	
	
	
	
	
	
	
	 
}
