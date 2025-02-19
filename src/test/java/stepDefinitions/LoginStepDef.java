package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class LoginStepDef {
	
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	
	public LoginStepDef(TestContext Context) {
		this.context = Context;
		this.readConfig = new ReadConfig();
	}
	
	
	@Given("The browser is open")
	public void the_browser_is_open() {
		String browserName = readConfig.getbrowser();
		driver = context.getDriverFactory().initialiseBrowser(browserName);
	}
	
	@Given("Admin gives the correct LMS portal URL")
	public void admin_gives_the_correct_lms_portal_url() {
		context.setDriver(driver);
		context.getDriver().get(readConfig.getApplicationURL());
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Then("Admin lands on login page")
	public void admin_lands_on_login_page() {
		loginPage = new LoginPage(driver);
		Assert.assertTrue(loginPage.getPageURL().contains("login"));
		
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
