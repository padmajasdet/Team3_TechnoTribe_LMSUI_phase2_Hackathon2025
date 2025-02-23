package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import hooks.TestContext;
import pageObjects.ClassPage;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class ClassPaginationStepDef {
	private WebDriver driver;
	private TestContext context;
	private ClassPage classPage;
	private ReadConfig readConfig;
	LoginPage loginPage;
	public ClassPaginationStepDef(TestContext context) {
		this.context = context;
		this.driver = context.getDriver();
		this.classPage = new ClassPage(driver, context);
		this.readConfig = new ReadConfig();

	}
	
	@When("Admin clicks Next page link on the class table")
	public void admin_clicks_next_page_link_on_the_class_table() {
	    classPage.clickOnNextPage();
	}

	@Then("Admin should see the next page record on the table  with Pagination has next active link enabled")
	public void admin_should_see_the_next_page_record_on_the_table_with_pagination_has_next_active_link_enabled() {
		String pageText = classPage.nextPageValidation();
		boolean nextPageActive = classPage.nextPageEnabled();
	    Assert.assertTrue(nextPageActive);
	    Assert.assertTrue(pageText.contains("Showing 11"));
	}
	
	@When("Admin clicks Last page link of class data table")
	public void admin_clicks_last_page_link_of_class_data_table() {
	    classPage.clickOnLastPage();
	}

	@Then("Admin should see the last page record on the table with Next page link are disabled for class data table")
	public void admin_should_see_the_last_page_record_on_the_table_with_next_page_link_are_disabled_for_class_data_table() {
		boolean nextPageDisabled= classPage.verifyNextPageBtnDisabled();
		boolean lastPageDisplayed = classPage.lastPageDisplayed();
		int lastPageRecord = classPage.lastPageRecord();
		int lastPageFootCount = classPage.lastPageFootCount();
		Assert.assertTrue(nextPageDisabled);
		Assert.assertTrue(lastPageDisplayed);
		Assert.assertEquals(lastPageRecord, lastPageFootCount);
	}
	
	@When("Admin clicks next page link of class data table")
	public void admin_clicks_next_page_link_of_class_data_table() {
	    classPage.clickOnNextPage();
	}

	@Then("Admin should see the previous page record on the table with pagination has previous page link enabled for class data table")
	public void admin_should_see_the_previous_page_record_on_the_table_with_pagination_has_previous_page_link_enabled_for_class_data_table() {
		boolean previousPageEnabled =classPage.verifyPreviousPageBtnEnabled();
		Assert.assertTrue(previousPageEnabled);
	}
	
	@When("Admin clicks Start page link of class data table")
	public void admin_clicks_start_page_link_of_class_data_table() {
	    classPage.clickOnFirstPage();
	}

	@Then("Admin should see the very first page record on the table with Previous page link are disabled for class data table")
	public void admin_should_see_the_very_first_page_record_on_the_table_with_previous_page_link_are_disabled_for_class_data_table() {
	    boolean previousPageDisabled= classPage.verifyPreviousPageBtnDisabled();
	    Assert.assertTrue(previousPageDisabled);
	}



}
