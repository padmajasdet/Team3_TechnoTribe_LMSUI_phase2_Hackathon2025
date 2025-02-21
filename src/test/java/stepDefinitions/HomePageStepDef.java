package stepDefinitions;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.testng.Assert.assertEquals;

import java.util.List;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.LoginPage;
import pageObjects.HomePage;
import utilities.ReadConfig;
import org.openqa.selenium.Point;
import utilities.Log;

public class HomePageStepDef {
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;

	public HomePageStepDef(TestContext Context) {
		this.context = Context;
		driver = context.getDriver();
		this.readConfig = new ReadConfig();
		homePage = new HomePage(driver);
		loginPage = new LoginPage(driver);
	}

	@Then("Admin should see LMS -Learning management system as title")
	public void admin_should_see_lms_learning_management_system_as_title() {
		String homePage_title = loginPage.getPageTitle();
		Log.logInfo("The LMS page title is " + homePage_title);
		Assert.assertTrue(homePage_title.contains("LMS"));

	}

	@Then("LMS title should be on the top left corner of page")
	public void lms_title_should_be_on_the_top_left_corner_of_page() {

		Point locationCordinates = homePage.lmsTitleLocation();
		Log.logInfo("The coordinates of LMS title are " + locationCordinates);
		int titleX = locationCordinates.getX();
		int titleY = locationCordinates.getY();
		int actualX = 0;
		int actualY = 0;
		assertEquals(titleX, actualX, "Title is not in the left corner x coordinate is not zero)");
		assertEquals(titleY, actualY, "Title is not at the top y coordinate is not zero)");

	}

	@Then("Admin should see correct spelling in navigation bar text")
	public void admin_should_see_correct_spelling_in_navigation_bar_text() {
		//// homePage = new HomePage(driver);
		String dashBoardTitle = homePage.getDashboardText();
		Log.logInfo("The Dasboard page title is " + dashBoardTitle);
		Assert.assertTrue(dashBoardTitle.contains("Dashboard"));
	}

	@Then("Admin should see correct spelling and space in LMS title")
	public void admin_should_see_correct_spelling_and_space_in_lms_title() {
		// homePage = new HomePage(driver);
		String expectedlmsPageTitle = "LMS - Learning Management System";
		String actualLmsPageTitle = homePage.getLMSPageTitleText();
		Log.logInfo("The actaul lms title is  " + actualLmsPageTitle);
		Assert.assertEquals(actualLmsPageTitle, expectedlmsPageTitle);
	}

	@Then("Admin should see the navigation bar text on the top right side")
	public void admin_should_see_the_navigation_bar_text_on_the_top_right_side() {
		int rightLocation = homePage.lmsTitleLocationRightNavigation();
		Log.logInfo("The right most Location of LMS title are " + rightLocation);
		int expectedRightMostLocation = 1470;
		assertEquals(rightLocation, expectedRightMostLocation, "Title is not in the right corner )");

	}

	@Then("Admin should see home in the 1st place")
	public void admin_should_see_home_in_the_1st_place() {
		List<WebElement> elements = homePage.getElements();
		String actualElement = "Home";
		String expectedElement = elements.get(0).getText();
		Assert.assertEquals(actualElement, expectedElement);

	}

	@Then("Admin should see program in the 2nd place")
	public void admin_should_see_program_in_the_2nd_place() {
		List<WebElement> elements = homePage.getElements();
		String actualElement = "Program";
		String expectedElement = elements.get(1).getText();
		Assert.assertEquals(actualElement, expectedElement);
	}

	@Then("Admin should see batch in the  3rd place")
	public void admin_should_see_batch_in_the_3rd_place() {
		List<WebElement> elements = homePage.getElements();
		String actualElement = "Batch";
		String expectedElement = elements.get(2).getText();
		Assert.assertEquals(actualElement, expectedElement);
	}

	@Then("Admin should see class in the 4th place")
	public void admin_should_see_class_in_the_4th_place() {
		List<WebElement> elements = homePage.getElements();
		String actualElement = "Class";
		String expectedElement = elements.get(3).getText();
		Assert.assertEquals(actualElement, expectedElement);
	}

	@Then("Admin should see class in the 5th place")
	public void admin_should_see_class_in_the_5th_place() {
		List<WebElement> elements = homePage.getElements();
		String actualElement = "Logout";
		String expectedElement = elements.get(4).getText();
		Assert.assertEquals(actualElement, expectedElement);
	}

	
}
