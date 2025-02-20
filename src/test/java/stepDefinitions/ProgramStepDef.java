package stepDefinitions;

import java.time.Duration;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import hooks.TestContext;
import io.cucumber.java.en.*;
import pageObjects.CommonPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.ProgramPage;
import utilities.ExcelReader;
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

	@Then("Admin should see the heading {string}")
	public void admin_should_be_see_heading_LMS(String LMSHeader) {
		programPage = new ProgramPage(driver);
		Assert.assertEquals(programPage.getLMSHeaderMenuBar(), LMSHeader);
	}

	/*
	 * @When("Admin clicks on {string} under the {string} menu bar") public void
	 * admin_clicks_on_under_the_menu_bar(String menu, String option) {
	 * programpage.clickNewProgram(menu, option); }
	 * 
	 * @Then("Admin should see pop up window for program details") public void
	 * admin_should_see_pop_up_window_for_program_details() {
	 * programpage.isProgramDetailsPopupVisible(); }
	 */

	@Given("Admin is on program details form")
	public void admin_is_on_program_details_form() {

		programPage = new ProgramPage(driver);
		programPage.clickAddNewProgramBtn();
	}

	@When("Admin enters details for {string} for mandatory fields and Click on save button")
   public void admin_enters_details_for_for_mandatory_fields_and_click_on_save_button(String testCase) {
    
	   String filePath = readConfig.getExcelPath(); // Excel file location
       String sheetName = "Program"; // Sheet name
       
       Map<String, String> programData = ExcelReader.getTestData(filePath, sheetName,testCase);

       // Debugging output to verify data
       System.out.println("Program Data: " + programData);

       if (programData != null) {
       programPage.fillProgramForm(programData);  
       
       }
		
       else { System.out.println("Test case not found in Excel: " + testCase); }
		 
		 
   }
	

	@Then("Admin gets message {string}")
	public void admin_gets_message(String string) {
		programPage.validateAddNewProgramSuccessMsg();
		
	}

}
