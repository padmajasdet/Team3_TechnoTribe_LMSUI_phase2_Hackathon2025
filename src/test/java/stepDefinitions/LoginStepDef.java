package stepDefinitions;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.asserts.SoftAssert;
import org.testng.Assert;
import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.ExcelReader;
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

	// @TTLPH2-16 Verify Admin is able to land on login page
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

	// @TTLPH2-12 Validate login with valid data in all field
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

	// @TTLPH2-2 Verify login button action through keyboard
	@When("Admin enter valid credentials  and clicks login button through keyboard")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_keyboard() {

		homePage = (HomePage) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(),
				"Admin", "KEYBOARD");
	}

	// @TTLPH2-9 Verify login button action through mouse

	@When("Admin enter valid credentials and clicks login button through mouse")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_mouse() {
		homePage = (HomePage) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(),
				"Admin", "Mouse");

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

	// @TTLPH2-13 Validate login with invalid data - "<ScenarioName>" --> DEFECT
	@When("Admin enter invalid {string} and\\/or {string}, and clicks login button")
	public void admin_enter_invalid_and_or_and_clicks_login_button(String username, String password) {

		actualErrMsg = (String) loginPage.loginWithInvalidCredentials(username.trim(), password.trim(), "Admin");

	}

	/********************** ADDITIONAL SCENARIOS *******************/

	// @TTLPH2-103 Validate login with Empty credentials and No Role selected
	@When("Admin enters no credentials, selects no role and clicks on login button")
	public void admin_enters_no_credentials_selects_no_role_and_clicks_on_login_button() {
		actualErrMsgList = loginPage.doLoginWithEmptyCredentials();
	}

	@Then("Admin sees {int} error messages {string}, {string} and  {string}")
	public void admin_sees_error_messages_and(Integer expectedErrMsgCount, String expErr1, String expErr2,
			String expErr3) {

		// Error Count Validation
		softAssert.assertEquals(expectedErrMsgCount, actualErrMsgList.size());

		// Actual Error Msg Text Validation
		List<String> expectedErrMsgList = Arrays.asList(expErr1.trim(), expErr2.trim(), expErr3.trim());
		softAssert.assertTrue(expectedErrMsgList.equals(actualErrMsgList));

		softAssert.assertAll();
	}

	// @TTLPH2-104 Validate login with Empty Credentials only
	@When("Admin only selects role as Admin and clicks on login button")
	public void admin_only_selects_role_as_admin_and_clicks_on_login_button() {

		actualErrMsgList = loginPage.doLoginWithEmptyCredentials("Admin");
	}

	@Then("Admin sees {int} error messages {string} and {string}")
	public void admin_sees_error_messages_and(Integer expectedErrMsgCount, String expErr1, String expErr2) {

		// Error Count Validation
		softAssert.assertEquals(expectedErrMsgCount, actualErrMsgList.size());

		// Actual Error Msg Text Validation
		List<String> expectedErrMsgList = Arrays.asList(expErr1.trim(), expErr2.trim());
		softAssert.assertTrue(expectedErrMsgList.equals(actualErrMsgList));

		softAssert.assertAll();
	}

	// @TTLPH2-105 Validate login with valid Credentials but No role selected
	@When("Admin enters valid credentials but selects no role")
	public void admin_enters_valid_credentials_but_selects_no_role() {

		actualErrMsg = (String) loginPage.doLoginWithValidCredentials(readConfig.getUsername(),
				readConfig.getPassword(), null);

	}

	/********************* NON-FUNCTIONAL SCENARIOS *******************/

	// @TTLPH2-30 Verify dropdown option to select role
	@Then("Admin should see {string},{string} and {string} options in dropdown")
	public void admin_should_see_and_options_in_dropdown(String option1, String option2, String option3) {

		List<String> expectedOptionList = Arrays.asList(option1.trim(), option2.trim(), option3.trim());
		List<String> actualOptionList = loginPage.getRoleOptions();

		Assert.assertTrue(expectedOptionList.equals(actualOptionList));
	}

	// @TTLPH2-22 Validate sign in content
	@Then("Admin should see {string}")
	public void admin_should_see(String expectedSignInContent) {

		Assert.assertEquals(expectedSignInContent, loginPage.getSignInContent());
	}

	// @TTLPH2-18 Verify for broken link
	@Then("If HTTP response >= {int}, then the link is broken")
	public void if_http_response_then_the_link_is_broken(Integer int1) {

		HttpURLConnection huc;
		int responseCode = 0;
		try {
			URL url = new URI(readConfig.getApplicationURL()).toURL();
			huc = (HttpURLConnection) url.openConnection();
			huc.setRequestMethod("HEAD");
			huc.connect();
			responseCode = huc.getResponseCode();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}

		Assert.assertFalse(responseCode >= 400);
	}

	// @TTLPH2-17 Verify Admin is not able to land on home page with invalid URL
	@When("Admin gives the invalid LMS portal URL for test case {string}")
	public void admin_gives_the_invalid_lms_portal_url_for_test_case(String testCase) {

		String filePath = readConfig.getExcelPath();
		String sheetName = "Login";

		Map<String, String> testCaseData = ExcelReader.getTestData(filePath, sheetName, testCase.trim());

		String invalidLMSURL = testCaseData.get("URL");
		System.out.println(invalidLMSURL);

		try {
			context.getDriver().get(invalidLMSURL);

		} catch (Exception e) {
			System.out.println("ERROR MSG IN CONSOLE");
			System.out.println(e.getMessage());
			actualErrMsg = e.getMessage();
		}

	}

	@Then("Admin should receive application error")
	public void admin_should_receive_application_error() {

		if (actualErrMsg != null) {
			// if no proper page launches and error exists
			Assert.assertTrue(actualErrMsg.contains("unknown error: net::ERR_NAME_NOT_RESOLVED"));
		} else
			// but if some page gets loaded, make sure it is not login page
			Assert.assertFalse(new LoginPage(driver).isPasswordFieldPresent());

	}

}
