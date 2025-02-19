package stepDefinitions;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import static org.testng.Assert.assertEquals;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.HomePage;
import utilities.ReadConfig;
import org.openqa.selenium.Point;

public class HomePageStepDef {
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;
	
	
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
	@Then("LMS title should be on the top left corner of page")
	public void lms_title_should_be_on_the_top_left_corner_of_page() {
		homePage = new HomePage(driver);
		Point locationCordinates = homePage.lmsTitleLocation();
		int titleX = locationCordinates.getX();
        int titleY = locationCordinates.getY();
        int actualX = 0;
        int actualY = 0;
		assertEquals(titleX,actualX,"Title is not in the left corner x coordinate is not zero)");
		assertEquals(titleY, actualY, "Title is not at the top y coordinate is not zero)");
    
		
		
	
	}
	

}

