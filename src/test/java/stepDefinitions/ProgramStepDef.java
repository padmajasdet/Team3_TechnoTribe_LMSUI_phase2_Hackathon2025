package stepDefinitions;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
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
	public void admin_edits_the_program_name_and_click_on_save_button(String newProgram, String testCase) throws InterruptedException {

		programPage.editTheProgramAndClickSave(newProgram, testCase);

	}

	@Then("Updated program {string} and {string} and {string} is seen by the Admin")
	public void updated_program_name_is_seen_by_the_admin(String updatedProgram, String updatedProgramDesc, String updatedStatus) {
		programPage.searchUpdatedProgram(updatedProgram);
		programPage.verifyUpdatedProgramDetails(updatedProgram,updatedProgramDesc,updatedStatus );

	}
	
	@Then("Admin should see Search bar with text as {string}")
	public void admin_should_see_search_bar_with_text_as(String searchBarText) {
		programPage.verifySearchBarManageProgram(searchBarText);	
		
	}
	@Then("Admin should see data table with column header on the Manage Program Page as  Program Name, Program Description, Program Status, Edit\\/Delete")
	public void admin_should_see_data_table_with_column_header_on_the_manage_program_page_as_program_name_program_description_program_status_edit_delete() {
		List<String> expectedHeaders = Arrays.asList("Program Name","Program Description","Program Status","Edit/Delete");
		        Assert.assertEquals(programPage.verifyTableHeaders(expectedHeaders), true);
	}
	
	
	@Then("Admin should see checkbox default state as unchecked beside Program Name column header")
	public void admin_should_see_checkbox_default_state_as_unchecked_beside_program_name_column_header() {
		
		Assert.assertEquals(programPage.isElementPresent("checkbox", "header"), true);
		Assert.assertEquals(programPage.verifyCheckBoxUnchecked(), true);
	}

	@Then("Admin should see check box default state as unchecked on the left side in all rows against program name")
	public void admin_should_see_check_box_default_state_as_unchecked_on_the_left_side_in_all_rows_against_program_name() {
		
		Assert.assertEquals(programPage.isElementPresent("checkbox", "row"), true);
	}

	@Then("Admin should see the sort arrow icon beside to each column header except Edit and Delete")
	public void admin_should_see_the_sort_arrow_icon_beside_to_each_column_header_except_edit_and_delete() {
		
		Assert.assertEquals(programPage.isElementPresent("sort-icon", "header"), true);
	}

	@Then("Admin should see the Edit and Delete buttons on each row of the data table")
	public void admin_should_see_the_edit_and_delete_buttons_on_each_row_of_the_data_table() {

		Assert.assertEquals(programPage.isElementPresent("edit-icon", "row"), true);
		Assert.assertEquals(programPage.isElementPresent("delete-icon", "row"), true);
	}

	@Then("Admin should see the text along with Pagination icon below the table")
	public void admin_should_see_entries_text_with_pagination() {
		Assert.assertEquals(programPage.isPaginationAvailable(), true);
	}

	@Then("Admin should see the footer with total programs")
	public void admin_should_see_the_footer_as() {

		programPage.verifyFooterOfManageProgram();
	}
	
	
	
	
	
	
	
	
	
	
	

}
