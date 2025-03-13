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
import utilities.Log;
import utilities.ReadConfig;

public class CommonStepDef {
	
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;
	ProgramPage programPage;
	BatchPage batchPage;
	
	public CommonStepDef(TestContext context) {
		this.context = context;
	      this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
		
	}
	
	@Given("Admin is logged in to LMS Portal")
	public void admin_is_logged_in_to_lms_portal() {
		loginPage = new LoginPage(driver);
		homePage =  (HomePage) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), "Admin");
		
	//	Log.logInfo("Home Page Title >>>>"+this.homePage.getPageTitle());
	}

	@Given("Admin is on home page after Login")
	public void admin_is_on_home_page_after_login() {
		String homePageTitle = this.homePage.getPageTitle();
		Assert.assertEquals(homePageTitle, "LMS");
		Log.logInfo("Page Title :" +homePageTitle);


	}
	
	@When("Admin clicks {string} on the navigation bar")
	public void admin_clicks_on_the_navigation_bar(String menuOption) throws Exception {
		  if (homePage == null) {
	            throw new IllegalStateException("HomePage is not initialized. Ensure that the Admin is logged in.");
	        }
		switch (menuOption.trim().toLowerCase()) {
		case "program":
			programPage = (ProgramPage) this.homePage.selectOptionNavigationMenuBar(menuOption);
			break;
			
		case "batch":
			batchPage = (BatchPage) this.homePage.selectOptionNavigationMenuBar(menuOption);
			break;
		
		case "logout":
			loginPage = (LoginPage)homePage.selectOptionNavigationMenuBar(menuOption);
			break;

		default:
			 throw new IllegalArgumentException("Invalid menu option: " + menuOption);
		}
		
		
	}
	
	/*********************** LOGOUT STEPS  ***********************/

	//@TTLPH2-112 Verify logout function
	@Then("Admin should be redirected to login page")
	public void admin_should_be_redirected_to_login_page() {
		
		Assert.assertTrue(loginPage.getPageURL().contains("login"));
	}
	
	//@TTLPH2-114 Verify back button function
	@When("Admin clicks browser back button")
	public void admin_clicks_browser_back_button() {

		loginPage.navigateBack();
		
	}
	
	@Then("Admin should receive error message")
	public void admin_should_receive_error_message() {

		Assert.assertFalse(new LoginPage(driver).getPageURL().contains("login"));
	}


}
