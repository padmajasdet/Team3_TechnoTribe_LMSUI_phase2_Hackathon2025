package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class BatchStepDef  {

	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	BatchPage batchPage;
	
	public BatchStepDef(TestContext context) {
		this.context = context;
	      this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
	}
	
	@Given("Admin successfully Logged on to the LMS Portal")
	public void admin_successfully_logged_on_to_the_lms_portal() {
		loginPage = new LoginPage(driver);
		loginPage.doLoginWithValidCredentials(readConfig.getUSername(), readConfig.getpassword(), "Admin");
	}
	
	@Given("Admin is on the home Page")
	public void admin_is_on_the_home_page() {
		batchPage = new BatchPage(driver);
		batchPage.homeMenuClick();	
	}
	
	@When("Admin Clicks on the Batch menu from the header")
	public void admin_clicks_on_the_batch_menu_from_the_header() {
		batchPage.batchMenuClick();
	}
	
	@Then("Admin should be in the Manage Batch Page")
	public void admin_should_be_in_the_manage_batch_page() {
	   Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
	}
	
	@Then("Admin should see the {string} Title")
	public void admin_should_see_the_title(String batchTitle) {
		 Assert.assertEquals(batchPage.getBatchPageTitle(), batchTitle);
	}
	
}
