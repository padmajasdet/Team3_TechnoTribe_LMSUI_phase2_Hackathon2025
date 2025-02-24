package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProgramPage;
import utilities.ReadConfig;

public class BatchPaginationDef {
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	BatchPage batchPage;

//	public BatchPaginationDef(TestContext context) {
//		this.context = context;
//	      this.driver = context.getDriver();
//		this.readConfig = new ReadConfig();
//		batchPage = new BatchPage(driver);
//	}
//	
//	@When("Admin clicks next page link on the data table")
//	public void admin_clicks_next_page_link_on_the_data_table() {
//		batchPage.clickOnNextPage();
//	}
//
//	@Then("Admin should see the Next enabled link")
//	public void admin_should_see_the_next_enabled_link() {
//		boolean nextPageActive = batchPage.nextPageEnabled();
//		Assert.assertTrue(nextPageActive);
//		
//	}
//	@When("Admin clicks last page link on the data table")
//	public void admin_clicks_last_page_link_on_the_data_table() {
//	    batchPage.clickOnLastPage();
//	}
//
//	@Then("Admin should see the last page link with next page link disabled on the table")
//	public void admin_should_see_the_last_page_link_with_next_page_link_disabled_on_the_table() {
//		boolean nextPageDisabled= batchPage.verifyNextPageBtnDisabled();
//		boolean lastPageDisplayed = batchPage.lastPageDisplayed();
//		Assert.assertTrue(nextPageDisabled);
//		Assert.assertTrue(lastPageDisplayed);
//	}
//	
//	@When("Admin clicks previous page link on the data table")
//	public void admin_clicks_previous_page_link_on_the_data_table() {
//	    batchPage.clickOnThirdPage();
//	    batchPage.clickOnPreviuosPage();
//	}
//
//	@Then("Admin should see the previous page on the table")
//	public void admin_should_see_the_previous_page_on_the_table() {
//		boolean previousPageActive = batchPage.previousPageEnabled();
//	    Assert.assertTrue(previousPageActive);
//	}
//	
//	@When("Admin clicks first page link on the data table")
//	public void admin_clicks_first_page_link_on_the_data_table() {
//		batchPage.clickOnFirstPage();
//	}
//
//	@Then("Admin should see the very first page on the data table")
//	public void admin_should_see_the_very_first_page_on_the_data_table() {
//	    String pageText = batchPage.firstPageValidation();
//	    System.out.println(pageText);
//	    Assert.assertTrue(pageText.contains("Showing 1"));
//	}
}
