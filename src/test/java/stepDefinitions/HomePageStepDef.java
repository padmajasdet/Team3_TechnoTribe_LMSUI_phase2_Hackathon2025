package stepDefinitions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class HomePageStepDef {
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	
	
	public HomePageStepDef(TestContext Context) {
		this.context = Context;
		this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
	}
	@When("Admin enter valid data in all field and  clicks login button")
	public void admin_enter_valid_data_in_all_field_and_clicks_login_button() throws InterruptedException {
		loginPage = new LoginPage(driver);
		String username = readConfig.getUsername();
		String password = readConfig.getPassword();
		loginPage.doLoginWithValidCredentials(username, password, "Admin");
		
	}

	@Then("Admin should see LMS -Learning management system as title")
	public void admin_should_see_lms_learning_management_system_as_title() {
		String homePage_title = loginPage.getPageTitle();
		Assert.assertTrue(homePage_title.contains("LMS"));
		
	}	
	

}

