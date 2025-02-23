package stepDefinitions;
import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.*;
import pageObjects.BatchPage;
import pageObjects.CommonPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProgramPage;
import utilities.ExcelReader;
import utilities.ReadConfig;

public class ProgramPaginationDef {
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;
	ProgramPage programPage;
	CommonPage commonPage;
	public static Map<String, String> programData;
	public ProgramPaginationDef(TestContext context) {
		this.context = context;
		this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
		programPage = new ProgramPage(driver);
		homePage = new HomePage(driver);
	}
	@When("Admin clicks Next page link on the program table")
	public void admin_clicks_next_page_link_on_the_program_table() {
	    programPage.clickOnNextPage();
	}
	@Then("Admin should see the Pagination has {string} active link")
	public void admin_should_see_the_pagination_has_active_link(String string) {
	    boolean nextPageActive = programPage.nextPageEnabled();
	    Assert.assertTrue(nextPageActive);
	    
	}
	
	@When("Admin clicks Last page link")
	public void admin_clicks_last_page_link() {
	    programPage.clickOnLastPage();
	}

	@Then("Admin should see the last page record on the table with Next page link are disabled")
	public void admin_should_see_the_last_page_record_on_the_table_with_next_page_link_are_disabled() {
		boolean nextPageActive = programPage.verifyNextPageBtnDisabled();
	    Assert.assertTrue(nextPageActive);
	}



}
