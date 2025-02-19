package stepDefinitions;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.*;
import pageObjects.CommonPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProgramPage;
import utilities.ReadConfig;

public class ProgramStepDef {
	
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;
	ProgramPage programPage;
	CommonPage commonPage;
	
	public ProgramStepDef(TestContext context) {
		this.context = context;
	      this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
	}


@Given("Admin clicks {string} on the navigation bar")
public void admin_clicks_on_the_navigation_bar(String menuOption) {
	
	commonPage = new CommonPage(driver);
	commonPage.selectOptionNavigationMenuBar(menuOption);
	
}

@Then("Admin should be navigated to Program page")
public void admin_should_be_navigated_to_program_page() {
	programPage = new ProgramPage(driver);
	Assert.assertEquals(programPage.getProgramPageTitle(), "Manage Program");
}

@Then("Admin should see Logout in menu bar")
public void admin_should_be_see_Logout_on_MenuBar() {
	programPage = new ProgramPage(driver);
	programPage.isLogoutDisplayedMenuBar();
}

}
