package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.*;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class ProgramStepDef {
	
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;
	
	public ProgramStepDef(TestContext context) {
		this.context = context;
	      this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
	}
	
	

@Given("Admin is logged in to LMS Portal")
public void admin_is_logged_in_to_lms_portal() {
	loginPage = new LoginPage(driver);
	 loginPage.doLoginWithValidCredentials(readConfig.getUSername(), readConfig.getpassword(), "Admin");
}

@Given("Admin is on home page after Login")
public void admin_is_on_home_page_after_login() {
	homePage = new HomePage(driver);
	Assert.assertEquals(false, null);
}



@Given("Admin clicks {string} on the navigation bar")
public void admin_clicks_on_the_navigation_bar(String string) {
}

@Then("Admin should be navigated to Program page")
public void admin_should_be_navigated_to_program_page() {
}

}
