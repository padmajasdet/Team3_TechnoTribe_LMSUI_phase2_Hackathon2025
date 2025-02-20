package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

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
	
	public LoginStepDef(TestContext Context) {
		this.context = Context;
		this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
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
		
		homePage = loginPage.doLoginWithValidCredentials(username, password, "Admin");

	}
	
	@Then("Admin should land on home page")
	public void admin_should_land_on_home_page() {

		Assert.assertTrue(homePage.isDashboardHeaderVisible());
	}
	
	//@TTLPH2-2 Verify login button action through keyboard
	@When("Admin enter valid credentials  and clicks login button through keyboard")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_keyboard() {

		homePage = loginPage.doLoginWithKeyboardOrMouseClick(readConfig.getUsername(), readConfig.getPassword(), "Admin", "KEYBOARD");
	}
	
	//@TTLPH2-9 Verify login button action through mouse
	@When("Admin enter valid credentials and clicks login button through mouse")
	public void admin_enter_valid_credentials_and_clicks_login_button_through_mouse() {
		homePage = loginPage.doLoginWithKeyboardOrMouseClick(readConfig.getUsername(), readConfig.getPassword(), "Admin", "Mouse");

	}


}
