package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;
import pageObjects.CommonPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProgramPage;
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
	}

	@Given("Admin is on home page after Login")
	public void admin_is_on_home_page_after_login() {
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "LMS");
		System.out.println("Page Title :" +homePageTitle);


	}
	
	@Given("Admin clicks {string} on the navigation bar")
	public void admin_clicks_on_the_navigation_bar(String menuOption) throws Exception {
		
		switch (menuOption.trim().toLowerCase()) {
		case "program":
			programPage = (ProgramPage) homePage.selectOptionNavigationMenuBar(menuOption);
			break;
			
		case "batch":
			batchPage = (BatchPage) homePage.selectOptionNavigationMenuBar(menuOption);
			break;
		
		case "logout":
			loginPage = (LoginPage)homePage.selectOptionNavigationMenuBar(menuOption);
			break;

		default:
			break;
		}
		
		
	}
	
	/*********************** LOGOUT STEPS  ***********************/

	//@TTLPH2-112 Verify logout function
	@Then("Admin should be redirected to login page")
	public void admin_should_be_redirected_to_login_page() {
		
		Assert.assertTrue(loginPage.getPageURL().contains("login"));
	}

}
