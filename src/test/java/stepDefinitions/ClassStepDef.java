package stepDefinitions;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import utilities.Log;

import hooks.TestContext;
import pageObjects.ClassPage;
import pageObjects.LoginPage;
import utilities.ReadConfig;
import io.cucumber.java.en.*;

public class ClassStepDef {
	private WebDriver driver;
	private TestContext context;
	private ClassPage cp;
	private ReadConfig readConfig;
	LoginPage loginPage;

	public ClassStepDef(TestContext context) {
		this.context = context;
		this.driver = context.getDriver();
		this.cp = new ClassPage(driver, context);
		this.readConfig = new ReadConfig();

	}
	/*@Given("Admin successfully Logged on to the LMS Portal")
	public void admin_successfully_logged_on_to_the_lms_portal() {
		loginPage = new LoginPage(driver);
		String url = readConfig.getApplicationURL();
		driver.get(url);
		
		loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), "Admin");
	}*/

	@Given("Admin is on the dashboard page after login")
	public void admin_is_on_the_dashboard_page_after_login() {

		loginPage = new LoginPage(driver);
		String url = readConfig.getApplicationURL();
		driver.get(url);
		
		loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), "Admin");
		System.out.println("DashBoard Url is " + driver.getCurrentUrl());

	}

	@When("Admin clicks the Class Navigation bar in the Header")
	public void admin_clicks_the_class_navigation_bar_in_the_header() {
		Log.logInfo("Navigated to home page ");

		cp.clickClassBtn();

	}

	@Then("Admin should land on the Manage class page with Title {string}")
	public void admin_should_land_on_the_manage_class_page_with_title(String Expected) {
		Log.logInfo("Navigated to Manage class page ");
		String current_Title = driver.getTitle();
		Assert.assertEquals(current_Title, Expected);
	}

	@Then("Admin should see the {string} Header")
	public void admin_should_see_the_header(String Expected) {
		Log.logInfo("Testing Manage class header");

		String ManageHeader = cp.getManageHeader();
		Assert.assertEquals(ManageHeader, Expected);

	}

	@Then("Admin should see the Search Bar in Manage class page")
	public void admin_should_see_the_search_bar_in_manage_class_page() {
		Log.logInfo("Testing search bar  in  manage class page");

		assertTrue(cp.validatesearchbox());

	}

	@Then("Admin should see the datatable heading like Batchname,class topic,class description,status,class Date,staff name,Edit\\/Delete")
	public void admin_should_see_the_datatable_heading_like_batchname_class_topic_class_description_status_class_date_staff_name_edit_delete() {
		
		List<String> itemTextList = cp.validateManageTableHeader();
		
		List<String> expectedItems = Arrays.asList("Batch Name", "Class Topic", "Class Description", "Status",
				"Class Date", "Staff Name", "Edit / Delete");

		Assert.assertTrue(itemTextList.containsAll(expectedItems), "List does not contain all expected items");
		Log.logInfo("Validated table headers  in  manage class page");
	}

}
