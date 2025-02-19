package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.Given;
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
	
	public CommonStepDef(TestContext context) {
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
		String homePageTitle = driver.getTitle();
		Assert.assertEquals(homePageTitle, "LMS");
		System.out.println("Page Title :" +homePageTitle);


	}

}
