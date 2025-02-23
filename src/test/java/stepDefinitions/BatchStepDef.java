package stepDefinitions;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.BatchPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.ReadConfig;

public class BatchStepDef  {

	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	LoginPage loginPage;
	BatchPage batchPage;
	HomePage homePage;
	 
	
	
	public BatchStepDef(TestContext context) {
		this.context = context;
	      this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
	}
	
	@Given("Admin successfully Logged on to the LMS Portal")
	public void admin_successfully_logged_on_to_the_lms_portal() {
		loginPage = new LoginPage(driver);
		String username = readConfig.getUsername();
		String password = readConfig.getPassword();
		homePage = (HomePage) loginPage.doLoginWithValidCredentials(username, password, "Admin");
		batchPage = new BatchPage(driver); // Initialize batchPage here
	}
	
	@Given("Admin is on the home Page")
	public void admin_is_on_the_home_page() {
		batchPage.homeMenuClick();	
	}
	
	@When("Admin Clicks on the Batch menu from the header")
	public void admin_clicks_on_the_batch_menu_from_the_header() {
		batchPage.batchMenuClick();
	}
	
	@Then("Admin should be in the Manage Batch Page")
	public void admin_should_be_in_the_manage_batch_page() {
	   Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
	}
	
	@Then("Admin should see the {string} Title")
	public void admin_should_see_the_title(String batchTitle) {
		 Assert.assertEquals(batchPage.getBatchPageTitle(), batchTitle);
	}
	
	@Then("Admin should see the {string} Heading")
	public void admin_should_see_the_heading(String batchPageTitle) {
		 Assert.assertEquals(batchPage.getManageBatchText(), batchPageTitle);
	}
	
	@Then("Admin should see the disabled {string} under the header")
	public void admin_should_see_the_disabled_under_the_header(String string) {
	    Assert.assertEquals(batchPage.deleteBatchHeaderButton(), false);
	}
	
	@Then("Admin should see the enabled pagination controls under the data table")
	public void admin_should_see_the_enabled_pagination_controls_under_the_data_table() {
	    Assert.assertEquals(batchPage.isPaginationAvailable(), true);
	}
	
	@Then("Admin should see the edit icon in each row")
	public void admin_should_see_the_edit_icon_in_each_row() {
	    Assert.assertEquals(batchPage.isElementPresent("edit-icon", "row"), true);
		
	}
	
	@Then("Admin should see the sort icon next to all Datatable headers")
	public void admin_should_see_the_sort_icon_next_to_all_datatable_headers() {
		  Assert.assertEquals(batchPage.isElementPresent("sort-icon", "header"), true);
	}
	
	@Then("Admin should see the delete icon in each row")
	public void admin_should_see_the_delete_icon_in_each_row() {
		Assert.assertEquals(batchPage.isElementPresent("delete-icon", "row"), true);
	}
	
	@Then("Admin should see the checkbox in each row")
	public void admin_should_see_the_checkbox_in_each_row() {
		Assert.assertEquals(batchPage.isElementPresent("checkbox", "row"), true);
	}
	
	@Then("Admin should see the checkbox  in the datatable header row")
	public void admin_should_see_the_checkbox_in_the_datatable_header_row() {
		Assert.assertEquals(batchPage.isElementPresent("checkbox", "header"), true);
	}
	
	@Then("Admin should see the datatable headers Batch name, Batch Description,Batch Status, No Of classes, Program Name, Edit\\/Delete")
	public void admin_should_see_the_datatable_headers_batch_name_batch_description_batch_status_no_of_classes_program_name_edit_delete() { 
	    	String[] actualHeaders = batchPage.verifyTableHeaders();
			String[] expectedHeaders = { "Batch Name", "Batch Description", "Batch Status", "No Of Classes", "Program Name",
					"Edit / Delete" };
			Assert.assertEqualsNoOrder(actualHeaders, expectedHeaders);
	}

	@Given("Admin is on the Batch Details Pop Up WIndow")
	public void admin_is_on_the_batch_details_pop_up_w_indow() {
		batchPage.batchMenuClick();
		batchPage.addBatchClick();	
	}
	@When("Admin selects program name present in the dropdown")
	public void admin_selects_program_name_present_in_the_dropdown() {
		batchPage.selectProgramNameDD();
		batchPage.selectProgramNameListBox("onlyMandatory");       
	}
	@Then("Admin should see selected program name in the batch name prefix box")
	public void admin_should_see_selected_program_name_in_the_batch_name_prefix_box() {
        Assert.assertEquals(batchPage.selectDataFromExcel("onlyMandatory","ProgramName"), batchPage.getBatchNamePrefix());	
	}
	
	@When("Admin enters the valid data to all the mandatory fields and click cancel button")
	public void admin_enters_the_valid_data_to_all_the_mandatory_fields_and_click_cancel_button() {
		batchPage.enterAllDetails("Cancel", "onlyMandatory");
		
	}
	@Then("Admin can see the batch details popup closes without creating any batch")
	public void admin_can_see_the_batch_details_popup_closes_without_creating_any_batch() {
		   Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
		
	}
	
	@Then("The pop up should include the fields Batch Name,Number of classes and Description as text box,Program Name as drop down Status as radio button")
	public void the_pop_up_should_include_the_fields_batch_name_number_of_classes_and_description_as_text_box_program_name_as_drop_down_status_as_radio_button() {
		  Assert.assertEquals(batchPage.isAddBatchDescEnabled(), true);   
	        Assert.assertEquals(batchPage.isAddBatchNameEnabled(), true);
	        Assert.assertEquals(batchPage.isAddBatchNoOfClassesEnabled(), true);
	        Assert.assertEquals(batchPage.isAddBatchProgramNameEnabled(), true);
	        Assert.assertEquals(batchPage.isStatusRadioButtonsPresentAndEnabled(),true);        
	}
	

	@When("Admin enters the data only to the mandatory fields and clicks save button")
	public void admin_enters_the_data_only_to_the_mandatory_fields_and_clicks_save_button() {
	           batchPage.enterAllDetails("Save", "onlyMandatory");
	}
	
	@Then("Admin should get a successful message")
	public void admin_should_get_a_successful_message() {
		Assert.assertEquals(batchPage.getToast(), "Successful");
	}
	
	@When("Admin clicks on {string} under the {string} menu bar")
	public void admin_clicks_on_under_the_menu_bar(String string, String string2) {
		batchPage.batchMenuClick();
		batchPage.addBatchClick();
	}
	
	@Then("Admin should see the Batch Details pop up window")
	public void admin_should_see_the_batch_details_pop_up_window() {
		   Assert.assertEquals(batchPage.getAddNewPageTitle(), "Batch Details");	
	}
	
	
	@When("Admin enters alphabets in batch name prefix box")
	public void admin_enters_alphabets_in_batch_name_prefix_box() {
	    batchPage.enterBatchNamePrefix();
		
	}
	@Then("Admin should see empty text box")
	public void admin_should_see_empty_text_box() {
	   Assert.assertEquals(batchPage.isBatchNamePrefixEditable(), false);
	}

	@When("Admin leaves blank one of the mandatory fields")
	public void admin_leaves_blank_one_of_the_mandatory_fields() {
		batchPage.enterAllDetails("Save", "missingOneMandatory");	
	}
	
	@Then("Admin should get a error message on the respective mandatory field")
	public void admin_should_get_a_error_message_on_the_respective_mandatory_field() {
		Assert.assertEquals(batchPage.getErrorMessage(), "Batch Name is required."); 	
	}
	
	@When("Admin clicks on the close icon")
	public void admin_clicks_on_the_close_icon() {
	   batchPage.closeButtonClick();
	}
	
	@Then("batch details pop up closes")
	public void batch_details_pop_up_closes() {
		Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
	}

	@When("Admin enters alphabets in batch name suffix box")
	public void admin_enters_alphabets_in_batch_name_suffix_box() {
	   batchPage.enterBatchNameSuffix();	
	}
	
	@Then("Admin should get error message below the text box of respective field")
	public void admin_should_get_error_message_below_the_text_box_of_respective_field() {
	   Assert.assertEquals(batchPage.getErrorMessage(), "This field accept only numbers and max 5 count.");   
	}

	@When("Admin enters the valid data to all the fields and click save button")
	public void admin_enters_the_valid_data_to_all_the_fields_and_click_save_button() {
		 batchPage.enterAllDetails("Save", "validAll");
	}

	@Given("Admin is on the Batch page")
	public void admin_is_on_the_batch_page() {
		batchPage.batchMenuClick();	
	}
	@When("Admin clicks the edit icon")
	public void admin_clicks_the_edit_icon() {
	 batchPage.clickAction("edit");	
	}
	@Then("Admin should see Program name value field is disabled for editing")
	public void admin_should_see_program_name_value_field_is_disabled_for_editing() {
		   Assert.assertEquals(batchPage.isEditBatchProgramNameEnabled(), false);	
	}
	@Given("Admin is on the Batch Details Page")
	public void admin_is_on_the_batch_details_page() {
		batchPage.batchMenuClick();	
		 batchPage.clickAction("edit");	
	}
	@When("Admin edit the valid data to all the mandatory fields and click cancel button")
	public void admin_edit_the_valid_data_to_all_the_mandatory_fields_and_click_cancel_button() {
		 batchPage.editAllDetails("Cancel", "editAll");
	}
	@Then("Admin can see the batch details popup closes without editing the batch")
	public void admin_can_see_the_batch_details_popup_closes_without_editing_the_batch() {
		  Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
	}
	@When("Admin Updates any fields with invalid data and click save button")
	public void admin_updates_any_fields_with_invalid_data_and_click_save_button() {
		 batchPage.editAllDetails("Save", "editInvalid");
	}
	@Then("Admin should get a error message under the respective field")
	public void admin_should_get_a_error_message_under_the_respective_field() {
		Assert.assertEquals(batchPage.getErrorMessage(), "This field should start with an alphabet and min 2 character."); 	
	}
	@When("Admin edit the valid data to all the mandatory fields and click save button")
	public void admin_edit_the_valid_data_to_all_the_mandatory_fields_and_click_save_button() {
	    batchPage.editAllDetails("Save", "editAll");
		
	}
	@Then("Admin should get a successful message for editing the batch")
	public void admin_should_get_a_successful_message_for_editing_the_batch() {
		Assert.assertEquals(batchPage.getToast(), "Successful");
	}
	@Then("Admin should see batch name value field is disabled for editing")
	public void admin_should_see_batch_name_value_field_is_disabled_for_editing() {
		  Assert.assertEquals(batchPage.isEditBatchNameEnabled(), false);	
	}
	@Then("Admin should see the Batch details pop up window in edit")
	public void admin_should_see_the_batch_details_pop_up_window_in_edit() {
		Assert.assertEquals(batchPage.getAddNewPageTitle(), "Batch Details");	
	}
	@When("Admin clicks the delete Icon on any row")
	public void admin_clicks_the_delete_icon_on_any_row() {
	   batchPage.clickDelete();
	}
	@Then("Admin should see the confirm alert box with yes and no button")
	public void admin_should_see_the_confirm_alert_box_with_yes_and_no_button() {
		Assert.assertEquals(batchPage.getDeleteTitle(), "Confirm");
		Assert.assertEquals(batchPage.getDeleteNoButton(), "No");
		Assert.assertEquals(batchPage.getDeleteYesButton(), "Yes");
	}
	@Given("Admin is on the batch confirm popup page")
	public void admin_is_on_the_batch_confirm_popup_page() {
		batchPage.batchMenuClick();	
		 batchPage.clickDelete();
	}
	@When("Admin clicks on the delete icon and click yes button")
	public void admin_clicks_on_the_delete_icon_and_click_yes_button() {
	   batchPage.clickDeleteButtons("yes");
	}
	@Then("Admin should see the successful message and the batch should be deleted")
	public void admin_should_see_the_successful_message_and_the_batch_should_be_deleted() {
		Assert.assertEquals(batchPage.getToast(), "Successful");
	}
	@When("Admin clicks on the delete icon and click no button")
	public void admin_clicks_on_the_delete_icon_and_click_no_button() {
		 batchPage.clickDeleteButtons("no");
	}
	@Then("Admin should see the alert box closed and the batch is not deleted")
	public void admin_should_see_the_alert_box_closed_and_the_batch_is_not_deleted() {
		Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
	}
	@When("Admin clicks on the close icon in batch confirm popup")
	public void admin_clicks_on_the_close_icon_in_batch_confirm_popup() {
		batchPage.clickDeleteButtons("close");
	}
	@Then("Admin should see the alert box closed")
	public void admin_should_see_the_alert_box_closed() {
		Assert.assertEquals(batchPage.getManageBatchText(), "Manage Batch");
	}
	
	@When("Admin clicks on the delete icon under the Manage batch header")
	public void admin_clicks_on_the_delete_icon_under_the_manage_batch_header() {
		batchPage.storeBeforeCount();
		batchPage.selectCheckboxes(1);
	}
	@Then("The respective row in the table should be deleted")
	public void the_respective_row_in_the_table_should_be_deleted() {
		Assert.assertTrue(batchPage.validateCount());
	}
	@When("Admin clicks on the delete icon for multiple row under the Manage batch header")
	public void admin_clicks_on_the_delete_icon_for_multiple_row_under_the_manage_batch_header() {
		batchPage.storeBeforeCount();
		batchPage.selectCheckboxes(3);
	}

	@When("Admin enters the batch name in the search text box and edit the valid data and click save button")
	public void admin_enters_the_batch_name_in_the_search_text_box_and_edit_the_valid_data_and_click_save_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("Admin enters the batch name in the search text box")
	public void admin_enters_the_batch_name_in_the_search_text_box() {
	    batchPage.enterSearch();
	}
	@Then("Admin should see the filtered batches in the data table")
	public void admin_should_see_the_filtered_batches_in_the_data_table() {
		Assert.assertFalse(batchPage.validateSearch());
	}
	@When("Admin clicks on the logout button")
	public void admin_clicks_on_the_logout_button() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@Then("Admin should see the Login screen Page")
	public void admin_should_see_the_login_screen_page() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}
	@When("Admin enters the batch name in the search text box and click on delete icon")
	public void admin_enters_the_batch_name_in_the_search_text_box_and_click_on_delete_icon() {
	    // Write code here that turns the phrase above into concrete actions
	    throw new io.cucumber.java.PendingException();
	}

	
	
	
	


	
	
}
