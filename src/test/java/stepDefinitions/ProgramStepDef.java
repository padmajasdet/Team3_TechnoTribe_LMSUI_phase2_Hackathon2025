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

public class ProgramStepDef {

	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	HomePage homePage;
	ProgramPage programPage;
	CommonPage commonPage;

	public static Map<String, String> programData;

	public ProgramStepDef(TestContext context) {
		this.context = context;
		this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
		programPage = new ProgramPage(driver);
		homePage = new HomePage(driver);
	}

	@Then("Admin should be navigated to Program page")
	public void admin_should_be_navigated_to_program_page() {
		Assert.assertEquals(programPage.getProgramPageTitle(), "Manage Program");
	}

	@Then("Admin should see Logout in menu bar")
	public void admin_should_be_see_Logout_on_MenuBar() {
		programPage.isLogoutDisplayedMenuBar();
	}

	@Then("Admin should see the heading {string}")
	public void admin_should_be_see_heading_LMS(String LMSHeader) {
		Assert.assertEquals(programPage.getLMSHeaderMenuBar(), LMSHeader);
	}

	@Then("Admin should see the Manage Program {string} Heading")
	public void admin_should_see_the_title(String manageProgramTitle) {
		Assert.assertEquals(programPage.getManageProgramText(), manageProgramTitle);
	}

	@Given("Admin is on program details form")
	public void admin_is_on_program_details_form() {

		programPage = new ProgramPage(driver);
		programPage.clickAddNewProgramBtn();
	}

	/*
	 * @When("Admin enters details for {string} for mandatory fields and Click on save button"
	 * ) public void
	 * admin_enters_details_for_for_mandatory_fields_and_click_on_save_button(String
	 * testCase) {
	 * 
	 * String filePath = readConfig.getExcelPath(); // Excel file location String
	 * sheetName = "Program"; // Sheet name
	 * 
	 * programData = ExcelReader.getTestData(filePath, sheetName,testCase);
	 * 
	 * // Debugging output to verify data System.out.println("Program Data: " +
	 * programData);
	 * 
	 * if (programData != null) { programPage.fillProgramForm(programData,testCase);
	 * 
	 * }
	 * 
	 * else { System.out.println("Test case not found in Excel: " + testCase);
	 * 
	 * }
	 * 
	 * 
	 * 
	 * 
	 * }
	 */

	@When("Admin enters details for {string} for mandatory fields and Click on save button")
	public void admin_enters_details_for_for_mandatory_fields_and_click_on_save_button(String testCase) {

		programPage.fillProgramForm(testCase);

	}

	@Then("Admin gets message {string}")
	public void admin_gets_message(String expSuccessMsg) {
		programPage.verifySuccessMessage(expSuccessMsg);

	}

	@Then("Admin should see the page names as in order on menu bar")
	public void admin_sees_menuBar() {
		programPage = new ProgramPage(driver);
		programPage.menuBarDisplay();
	}

	@Then("Admin should see the title on Program page {string}")
	public void admin_sees_lms_titleOnProgPage() {
		programPage.menuBarDisplay();
	}

	@Then("Admin should able to see Program name, description, and status for each program")
	public void admin_should_be_able_to_see_table_with_program_name_description_and_status_headings() {
		Assert.assertEquals(programPage.verifyColumnHeader(), true);
	}

	@Then("Admin should see a Delete button in left top is disabled")
	public void delete_Btn_should_be_disabled() {
		Assert.assertEquals(programPage.verifyDeleteBtnDisabled(), true);
	}

	@Given("Admin is on Program page")
	public void admin_is_on_program_page() throws Exception {

		programPage = (ProgramPage) homePage.selectOptionNavigationMenuBar("Program");
	}

	@When("Admin searches with newly created Program {string}")
	public void admin_searches_with_newly_updated(String newProgName) {

		programPage.search(newProgName);
	}

	@Then("Records of the newly created  {string} is displayed and match the data entered")
	public void admin_verifies_that_the_details_are_correctly_updated(String newProgram) {
		programPage.verifySearchResultProgramName(newProgram);

	}

	@When("Admin edits the program {string} and click on save button for {string}")
	public void admin_edits_the_program_name_and_click_on_save_button(String newProgram, String testCase) {

		programPage.editTheProgramAndClickSave(newProgram, testCase);

	}

	@Then("Updated program name is seen by the Admin")
	public void updated_program_name_is_seen_by_the_admin() {

	}

}
