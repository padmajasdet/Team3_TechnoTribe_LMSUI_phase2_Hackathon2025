package stepDefinitions;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class LoginStepDef {
	
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	
	LoginPage loginPage;
	HomePage homePage;
	
	String actualErrMsg;
	List<String> actualErrMsgList;
	SoftAssert softAssert;
	
	public LoginStepDef(TestContext Context) {
		this.context = Context;
		this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
		softAssert = new SoftAssert();
	}
	
	//@TTLPH2-16 Verify Admin is able to land on login page
	@Given("The browser is open")
	public void the_browser_is_open() {
		String browserName = readConfig.getbrowser();
		driver = context.getDriverFactory().initialiseBrowser(browserName);
	}
	
	@Given("Admin gives the correct LMS portal URL")
	public void admin_gives_the_correct_lms_portal_url() {
	
		context.setDriver(driver);
		context.getDriver().get(readConfig.getApplicationURL());

	}
	
	@Then("Admin lands on login page")
	public void admin_lands_on_login_page() {
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.getPageURL().contains("login"));
	}
	
	//@TTLPH2-12 Validate login with valid data in all field
	@When("Admin enter valid data in all field and clicks login button")
	public void admin_enter_valid_data_in_all_field_and_clicks_login_button() {
		
		String username = readConfig.getUsername();
		String password = readConfig.getPassword();
		
		homePage = (HomePage) loginPage.doLoginWithValidCredentials(username, password, "Admin");

	}
	
	@Then("Admin should land on home page")
	public void admin_should_land_on_home_page() {

		Assert.assertTrue(homePage.isDashboardHeaderVisible());
	}
	
	//@TTLPH2-2 Verify login button action through keyboard
	@When("Admin enter valid credentials  and clicks login button through keyboard")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_keyboard() {

		homePage = (HomePage) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), "Admin", "KEYBOARD");
	}
	
	//@TTLPH2-9 Verify login button action through mouse
	@When("Admin enter valid credentials and clicks login button through mouse")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_mouse() {
		homePage = (HomePage) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), "Admin", "Mouse");

	}

	// @TTLPH2-14 Validate login credentials with null user name
	@When("Admin enter value only in password and clicks login button")
	public void admin_enter_value_only_in_password_and_clicks_login_button() {

		actualErrMsg = (String) loginPage.doLoginWithValidCredentials("", readConfig.getPassword(), "Admin");
	
	}
	
	// @TTLPH2-15 Validate login credentials with null password
	@When("Admin enter value only in user name and clicks login button")
	public void admin_enter_value_only_in_user_name_and_clicks_login_button() {

		actualErrMsg = (String) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), "", "Admin");
	}
	
	@Then("Error message {string}")
	public void error_message(String expectedErrMsg) {

		Assert.assertEquals(expectedErrMsg.trim(), actualErrMsg.trim());
	}
	
	//@TTLPH2-13 Validate login with invalid data - "<ScenarioName>" --> DEFECT
	@When("Admin enter invalid {string} and\\/or {string}, and clicks login button")
	public void admin_enter_invalid_and_or_and_clicks_login_button(String username, String password) {

		actualErrMsg = (String) loginPage.doLoginWithValidCredentials(username.trim(),password.trim(), "Admin");

	}
	
	/********************** ADDITIONAL SCENARIOS *******************/
	
	//@TTLPH2-103 Validate login with Empty credentials and No Role selected
	@When("Admin enters no credentials, selects no role and clicks on login button")
	public void admin_enters_no_credentials_selects_no_role_and_clicks_on_login_button() {
		actualErrMsgList = loginPage.doLoginWithEmptyCredentials();
	}
	
	@Then("Admin sees {int} error messages {string}, {string} and  {string}")
	public void admin_sees_error_messages_and(Integer expectedErrMsgCount, String expErr1, String expErr2, String expErr3) {

		//Error Count Validation
		softAssert.assertEquals(expectedErrMsgCount, actualErrMsgList.size());
		
		//Actual Error Msg Text Validation
		List<String> expectedErrMsgList = Arrays.asList(expErr1.trim(),expErr2.trim(),expErr3.trim());
		softAssert.assertTrue(expectedErrMsgList.equals(actualErrMsgList));
		
		softAssert.assertAll();
	}
	
	//@TTLPH2-104 Validate login with Empty Credentials only
	@When("Admin only selects role as Admin and clicks on login button")
	public void admin_only_selects_role_as_admin_and_clicks_on_login_button() {
		
		actualErrMsgList = loginPage.doLoginWithEmptyCredentials("Admin");
	}
	
	@Then("Admin sees {int} error messages {string} and {string}")
	public void admin_sees_error_messages_and(Integer expectedErrMsgCount, String expErr1, String expErr2) {

		//Error Count Validation
		softAssert.assertEquals(expectedErrMsgCount, actualErrMsgList.size());
		
		//Actual Error Msg Text Validation
		List<String> expectedErrMsgList = Arrays.asList(expErr1.trim(),expErr2.trim());
		softAssert.assertTrue(expectedErrMsgList.equals(actualErrMsgList));
		
		softAssert.assertAll();
	}
	
	//@TTLPH2-105 Validate login with valid Credentials but No role selected
	@When("Admin enters valid credentials but selects no role")
	public void admin_enters_valid_credentials_but_selects_no_role() {

		actualErrMsg = (String) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), null);

	}

}
