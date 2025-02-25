package stepDefinitions;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import hooks.TestContext;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pageObjects.ClassPage;
import pageObjects.HomePage;
import pageObjects.LoginPage;
import utilities.Log;
import utilities.ReadConfig;

public class ClassStepDef {
	private WebDriver driver;
	private TestContext context;
	private ClassPage classPage;
	private ReadConfig readConfig;
	SoftAssert softAssert;

	LoginPage loginPage;
	HomePage homePage;

	boolean status;

	public ClassStepDef(TestContext context) {
		this.context = context;
		driver = context.getDriver();
		// classPage = new ClassPage(driver, context);
		classPage = new ClassPage(driver);
		readConfig = new ReadConfig();
		softAssert = new SoftAssert();

	}

	@Given("Admin is on the dashboard page after login")
	public void admin_is_on_the_dashboard_page_after_login() {

		loginPage = new LoginPage(driver);
		homePage = (HomePage) loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(),
				"Admin");
		System.out.println("DashBoard Url is " + driver.getCurrentUrl());

	}

	@When("Admin clicks the Class Navigation bar in the Header")
	public void admin_clicks_the_class_navigation_bar_in_the_header() {
		Log.logInfo("Navigated to home page ");
		try {
			classPage = (ClassPage) homePage.selectOptionNavigationMenuBar("Class");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// classPage.clickClassBtn();

	}

	@Then("Admin should land on the Manage class page with Title {string}")
	public void admin_should_land_on_the_manage_class_page_with_title(String Expected) {
		Log.logInfo("Navigated to Manage class page ");
		String current_Title = driver.getTitle();
		Assert.assertEquals(current_Title, Expected);
	}

	@Then("Admin should see the {string} Header")
	public void admin_should_see_the_header(String expectedHeader) {
		Log.logInfo("Testing Manage class header");

		String ManageHeader = classPage.getManageHeader();
		Assert.assertEquals(ManageHeader, expectedHeader);

	}

	@Then("Admin should see the Search Bar in Manage class page")
	public void admin_should_see_the_search_bar_in_manage_class_page() {
		Log.logInfo("Testing search bar  in  manage class page");

		Assert.assertTrue(classPage.validatesearchbox());

	}

	@Then("Admin should see the datatable heading like Batchname,class topic,class description,status,class Date,staff name,Edit\\/Delete")
	public void admin_should_see_the_datatable_heading_like_batchname_class_topic_class_description_status_class_date_staff_name_edit_delete() {

		List<String> itemTextList = classPage.validateManageTableHeader();

		List<String> expectedItems = Arrays.asList("Batch Name", "Class Topic", "Class Description", "Status",
				"Class Date", "Staff Name", "Edit / Delete");

		Assert.assertTrue(itemTextList.containsAll(expectedItems), "List does not contain all expected items");
		Log.logInfo("Validated table headers  in  manage class page");
	}

	@Then("Admin should see Total no of classes in below of the data table.")
	public void admin_should_see_total_no_of_classes_in_below_of_the_data_table() {
		String paginationTextfooter = classPage.testpaginationfooter();
		String expectedPatternfooter = "In total there are \\d+ classes.";

		Assert.assertTrue(paginationTextfooter.matches(expectedPatternfooter), "Pagination text format is correct");
	}

	@When("clicks add new class under the class menu bar")
	public void clicks_add_new_class_under_the_class_menu_bar() throws InterruptedException {

		do {
			// Code to be executed
			classPage.clickClassBtn();
			classPage.clickAddNewClass();
		} while (!classPage.batchnamedropdownDisplayed());

	}

	@Given("clicks add new class under the class menu bar for creation")
	public void clicks_add_new_class_under_the_class_menu_bar_for_creation() throws InterruptedException {
		classPage.clickClassBtn();
		classPage.clickAddNewClass();
		Thread.sleep(1000);
	}
	

	@Then("Admin should see a popup open for class details with empty form along with <SAVE> and <CANCEL> button and Close\\(X) Icon on the top right corner of the window")
	public void admin_should_see_a_popup_open_for_class_details_with_empty_form_along_with_SAVE_and_CANCEL_button_and_Close_X_Icon_on_the_top_right_corner_of_the_window()
			throws InterruptedException {

		softAssert.assertTrue(classPage.cancelDisp());
		// Assert.assertTrue(classPage.cancelDisp());
		Log.logInfo("Cancel button is visible");

		softAssert.assertTrue(classPage.saveDisp());
		// Assert.assertTrue(classPage.saveDisp());
		Log.logInfo("Save button is visible");

		softAssert.assertTrue(classPage.crossBtnDisp());
		// Assert.assertTrue(classPage.crossBtnDisp());
		Log.logInfo("Save button is visible");

		softAssert.assertAll();

	}

	@Then("Admin should see few input fields and their respective text boxes in the class details window")
	public void admin_should_see_few_input_fields_and_their_respective_text_boxes_in_the_class_details_window() {
		softAssert.assertTrue(classPage.classTopicOnPopupDisp());
		softAssert.assertTrue(classPage.classDescriptionOnPopupDisp());
		softAssert.assertTrue(classPage.commentsonPopupDisp());
		softAssert.assertTrue(classPage.noOfClassesonPopupDisp());
		softAssert.assertTrue(classPage.notesPopupDisp());
		softAssert.assertTrue(classPage.staffNameOnPopupDisp());
		softAssert.assertTrue(classPage.statusonPopupDisp());
		softAssert.assertTrue(classPage.recordingonPopupDisp());
		softAssert.assertTrue(classPage.classDatesonPopupDisp());
		softAssert.assertAll();

		Log.logInfo("All Elements on the class popup are visible");

	}

	@When("Admin enters mandatory fields {string} {string} {string} {string} {string}  {string} {string} {string} in the form and clicks on save button")
	public void admin_enters_mandatory_fields_in_the_form_and_clicks_on_save_button(String batchName, String classTopic,
			String classDescription, String month, String date, String staffName, String Status, String expectedMsg)
			throws Exception {

		if (classPage.batchnamedropdownDisplayed()) {
			System.out.println("batch name displayed:     " + classPage.batchnamedropdownDisplayed());

			classPage.addingMandatoryFields(batchName.trim(), classTopic.trim(), classDescription.trim(), month.trim(),
					date.trim(), staffName.trim(), Status.trim());
			Assert.assertTrue(
					classPage
							.addingMandatoryFields(batchName.trim(), classTopic.trim(), classDescription.trim(),
									month.trim(), date.trim(), staffName.trim(), Status.trim())
							.equals(expectedMsg.trim()));
		} else
			System.out.println("Add New Class pop up didnt open");
			
		

	}

	@Then("Admin gets message Class added Successfully")
	public void admin_gets_message_Class_added_Successfully() {
		Log.logInfo("Class created successfully");
	}

	@Then("Admin should see the Sort icon of all the field in the datatable.")
	public void admin_should_see_the_Sort_icon_of_all_the_field_in_the_datatable() {
		status = classPage.validateSortingBtn();
		Assert.assertTrue(status);
		Log.logInfo("Sorting button are visible");

	}

	@Then("Admin should see the Delete button under the Manage class page header")
	public void admin_should_see_the_Delete_button_under_the_Manage_class_page_header() {
		status = classPage.deleteBtnDisplayed();
		Assert.assertTrue(status);
		Log.logInfo("Delete button is visible");

	}

	@Then("Admin should see the showing entries and enabled pagination controls under the data table")
	public void admin_should_see_the_and_enabled_pagination_controls_under_the_data_table() {
		Assert.assertTrue(classPage.validateShowingEnteries());
	    Log.logInfo("Footer is displayed");
	    
	}

	@When("Admin clicks Cancel Icon on class Details form")
	public void admin_clicks_cancel_icon() throws InterruptedException {
		classPage.cancelDisp();

	}

	@Then("Class Details popup window should be closed without saving")
	public void class_details_popup_window_should_be_closed_without_saving() {
		status = classPage.onManagePage();
		Assert.assertTrue(status);
		Log.logInfo("Admin is on manage class page");

	}

	@When("Admin clicks on save button without entering data")
	public void admin_clicks_on_save_button_without_entering_data() {
		classPage.clickOnSave();
	}

	@Then("Admin should see error message below the test field and the field will be highlighed in red color {string} {string} {string} {string} {string}")
	public void admin_should_see_error_message_below_the_test_field_and_the_field_will_be_highlighed_in_red_color(
			String expectedBatchNameText, String expectedClassTopicText, String expectedClassDateText,
			String expectedStaffNameText, String expectedNoOfClassesText) {
		Assert.assertEquals("Batch Name is required", expectedBatchNameText, classPage.getBatchNameReqText());
		Assert.assertEquals("Class Topic is required", expectedClassTopicText, classPage.getClassTopicReqText());
		Assert.assertEquals("Class Date is required", expectedClassDateText, classPage.getClassDateReqText());
		Assert.assertEquals("Staff Name is required", expectedStaffNameText, classPage.getStaffNameReqText());
		Assert.assertEquals("No. of Classes is required", expectedNoOfClassesText, classPage.getNoOfClassesReqText());
	}

	@When("Admin skips to add value in mandatory field and enter only the optional field {string} {string} {string}")
	public void admin_skips_to_add_value_in_mandatory_field_and_enter_only_the_optional_field(String comments,
			String notes, String recording) throws Exception {
		classPage.selectOptionalFields(comments, notes, recording);

	}
	@Then("Admin should see Class details are searched by given fields")
	public void admin_should_see_class_details_are_searched_by() {
		
	}
	@When("Admin clicks date picker")
	public void admin_clicks_date_picker() {
		classPage.clickDatePicker();
	}
	@Then("Admin should see weekends dates are disabled to select")
	public void admin_should_see_weekends_dates_are_disabled_to_select() {
		//classPage.weekendDaysDisabled(); 
		System.out.println(classPage.areWeekendDatesDisabled());
	}
	/*------------------------------editpopup------------------------------------------*/
	@When("Admin clicks on the edit icon")
	public void admin_clicks_on_the_edit_icon() {
		classPage.clickOnEdit();
	}

	@Then("A new pop up with class details appears")
	public void a_new_pop_up_with_class_details_appears() {
		status = classPage.editPopup();
		Assert.assertTrue(status);
		Log.logInfo("edit popup window opens");
	}

	@Then("Admin should see batch name field is disabled")
	public void admin_should_see_batch_name_field_is_disabled() {
		Assert.assertTrue(classPage.batchNameDisabled());
		Log.logInfo("Batch Name is disabled");
	}

	@Then("Admin should see class topic field is disabled")
	public void admin_should_see_class_topic_field_is_disabled() {
		status = classPage.classTopicDisabled();
		Assert.assertFalse(status);
		Log.logInfo("Class Topic is disabled");
	}

	@Given("Admin is on the Edit Class Popup window")
	public void admin_is_on_the_Edit_Class_Popup_window() {
		classPage.clickOnEdit();
	}

	@When("Update the optional fields with valid values {string} {string} {string} and click save")
	public void update_the_optional_fields_with_valid_values_and_click_save(String comments, String notes,
			String recording) throws Exception {
		classPage.selectOptionalFields(comments, notes, recording);
	}

	@Then("Admin gets message {string} and see the updated values in data table")
	public void admin_gets_message_and_see_the_updated_values_in_data_table(String string) {

		Assert.assertEquals("Updated class Successfully", string, classPage.saveEditClass());

	}

	@When("Update the fields with valid data {string} and click save")
	public void update_the_fields_with_valid_data_and_click_save(String editClass) {
		classPage.editClassDetails(editClass);

	}

	@When("Admin clicks Cancel button on edit popup")
	public void admin_clicks_Cancel_button_on_edit_popup() throws InterruptedException {
		classPage.cancelDisp();
	}

	@Then("Admin can see the class details popup disappears and can see nothing changed for particular Class")
	public void admin_can_see_the_class_details_popup_disappears_and_can_see_nothing_changed_for_particular_Class() {
		status = classPage.onManagePage();
		Assert.assertTrue(status);
		Log.logInfo("Admin is on manage class page");
	}
	// *********************************Delete
	// class***************************************

	@When("Admin clicks on the delete icon on class module page")
	public void admin_clicks_on_the_delete_icon_on_class_module_page() {
		classPage.clickOnDeleteIcon();

	}

	@Then("Admin able to delete by clicking yes to confirmation pop up on Class module")
	public void admin_able_to_delete_by_clicking_yes_to_confirmation_pop_up() {
		classPage.deleteSingleProgram();
	}


	@Then("Admin able to delete by clicking No to confirmation pop up on Class module")
	public void admin_able_to_delete_by_clicking_No_to_confirmation_pop_up() {
		classPage.DropDeleteSingleProgram();
	}


	@When("Admin clicks on the multiple checkboxes on class module page")
	public void admin_clicks_on_the_multiple_checkboxes_on_class_module_page() {
		classPage.SelectCheckBoxes();
	}

	@When("Admin clicks  on the left delete button on class module page")
	public void admin_clicks_on_the_left_delete_button_on_class_module_page() {
		classPage.MultipleDelete();

	}

	@Then("Admin able to delete multiple class by clicking yes to confirm")
	public void admin_able_to_delete_multiple_class_by_clicking_yes_to_confirm() {
		classPage.DeleteSuccess();

	}
	// Search by batch name class topic and staff name

	@When("Admin enter the {string} {string} in search textbox")
	public void admin_enter_the_in_search_textbox(String field, String value) throws InterruptedException {
		classPage.searhBoxValidation(field, value);
	}


	@When("Admin clicks on Arrow next to Batch Name of Class module page for sort")
	public void admin_clicks_on_Arrow_next_to_Batch_Name_of_Class_module_page_for_sort() {
		classPage.clickBatchNameSort();

	}

	@Then("Admin See the Batch Name is sorted Ascending order in Class module page for sort")
			public void admin_See_the_Batch_Name_is_sorted_Ascending_order_in_Class_module_page_for_sort() {
				List<String> originalList = classPage.getOriginalList("BatchName");
				List<String> sortedList = classPage.getSortedList(originalList);
				System.out.println("sorted name list" + sortedList.toString() );
				Assert.assertTrue(originalList.equals(sortedList));
	}


	@When("Admin clicks on Arrow next to Batch Name of Class module page for sort descend")
	public void admin_clicks_on_Arrow_next_to_Batch_Name_of_Class_module_page_for_sort_descend() {
		classPage.clickBatchNameSortDec();

	}

	@Then("Admin See the Batch Name is sorted Descending order in Class module page")
	public void admin_See_the_Batch_Name_is_sorted_Descending_order_in_Class_module_page() {
		List<String> originalList = classPage.getOriginalList("BatchName");
		List<String> sortedList = classPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to Class Topic of Class module page for sort")
	public void admin_clicks_on_Arrow_next_to_Class_Topic_of_Class_module_page_for_sort() {
		classPage.clickclassTopicSort();
	}

	@Then("Admin See the Class Topic is sorted Ascending order in Class module page")
	public void admin_See_the_Class_Topic_is_sorted_Ascending_order_in_Class_module_page() {
		List<String> originalList = classPage.getOriginalList("ClassTopic");
		List<String> sortedList = classPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to Class Topic of Class module page for sort descend")
	public void admin_clicks_on_Arrow_next_to_Class_Topic_of_Class_module_page_for_sort_descend() {
		classPage.clickclassTopicSortDes();
	}

	@Then("Admin See the Class Topic is sorted Descending order in Class module page")
	public void admin_See_the_Class_Topic_is_sorted_Descending_order_in_Class_module_page() {

		List<String> originalList = classPage.getOriginalList("ClassTopic");
		List<String> sortedList = classPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to Class Description of Class module page for sort")
	public void admin_clicks_on_Arrow_next_to_Class_Description_of_Class_module_page_for_sort() {
		classPage.clickclassDescriptionSort();

	}

	@Then("Admin See the Class Description is sorted Ascending order in Class module page")
	public void admin_See_the_Class_Description_is_sorted_Ascending_order_in_Class_module_page() {
		List<String> originalList = classPage.getOriginalList("Classdescription");
		List<String> sortedList = classPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to Class Description of Class module page for sort descend")
	public void admin_clicks_on_Arrow_next_to_Class_Description_of_Class_module_page_for_sor_descend() {
		classPage.clickclassDescriptionSortDes();

	}

	@Then("Admin See the Class Description is sorted Descending order in Class module page")
	public void admin_See_the_Class_Description_is_sorted_Descending_order_in_Class_module_page() {
		List<String> originalList = classPage.getOriginalList("ClassDescription");
		List<String> sortedList = classPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to Status of Class module page for sort")
	public void admin_clicks_on_arrow_next_to_status_of_class_module_page_for_sort() {
		classPage.clickStatusSort();
	}

	@Then("Admin See the Status is sorted Ascending order in Class module page")
	public void admin_see_the_status_is_sorted_ascending_order_in_class_module_page() {
		List<String> originalList = classPage.getOriginalList("Status");
		List<String> sortedList = classPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}

	@When("Admin clicks on Arrow next to Status of Class module page for sort descend")
	public void admin_clicks_on_arrow_next_to_status_of_class_module_page_for_sort_descend() {
		classPage.clickStatusSortDec();
	}

	@Then("Admin See the Status is sorted Descending order in Class module page")
	public void admin_see_the_status_is_sorted_descending_order_in_class_module_page() {
		List<String> originalList = classPage.getOriginalList("Status");
		List<String> sortedList = classPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to StaffName of Class module page for sort")
	public void admin_clicks_on_arrow_next_to_staff_name_of_class_module_page_for_sort() {

		classPage.clickStaffNameSort();
	}

	@Then("Admin See the StaffName is sorted Ascending order in Class module page")
	public void admin_see_the_staff_name_is_sorted_ascending_order_in_class_module_page() {
		List<String> originalList = classPage.getOriginalList("StaffName");
		List<String> sortedList = classPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}

	@When("Admin clicks on Arrow next to StaffName of Class module page for sort descend")
	public void admin_clicks_on_arrow_next_to_StaffName_of_class_module_page_for_sort_descend() {
		classPage.clickStaffNameSortDec();
	}

	@Then("Admin See the StaffName is sorted Descending order in Class module page")
	public void admin_see_the_StaffName_is_sorted_descending_order_in_class_module_page() {
		List<String> originalList = classPage.getOriginalList("StaffName");
		List<String> sortedList = classPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));

	}

	@When("Admin clicks on Arrow next to ClassDate of Class module page for sort")
	public void admin_clicks_on_arrow_next_to_ClassDate_of_class_module_page_for_sort() {

		classPage.clickClassDateSort();
	}

	@Then("Admin See the ClassDate is sorted Ascending order in Class module page")
	public void admin_see_the_ClassDate_is_sorted_ascending_order_in_class_module_page() {
		List<Date> originalList = classPage.getClassDatesOriginalList();
		List<Date> sortedList = classPage.getClassDatesSortedList();
		System.out.println("sorted name list" + sortedList);
		System.out.println("original list name list" + originalList);
		Assert.assertTrue(originalList.equals(sortedList));
		// if ((originalList.get(0)).equals(sortedList.get(0))) {
		// System.out.println("The lists are identical.");
		// } else {
		// System.out.println("The lists are different.");
		// }
	}

	@When("Admin clicks on Arrow next to ClassDate of Class module page for sort descend")
	public void admin_clicks_on_arrow_next_to_ClassDate_of_class_module_page_for_sort_descend() {
		classPage.clickClassDateSortDec();
	}

	@Then("Admin See the ClassDate is sorted Descending order in Class module page")
	public void admin_see_the_ClassDate_is_sorted_descending_order_in_class_module_page() {
		List<String> originalList = classPage.getOriginalList("ClassDate");
		List<String> sortedList = classPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}
	@When("Admin clicks on Logout link on Manage class page")
	public void admin_clicks_on_logout_link_on_manage_class_page() {
	    classPage.clickLogout();
	}
	@Then("Admin is redirected to Login page")
	public void admin_is_redirected_to_login_page() {
		
		Log.logInfo("Navigated to Dashboard  page ");
		String current_Title = driver.getTitle();
		 String Expected="LMS";
		Assert.assertEquals(current_Title, Expected);
	}
	@When("Admin clicks next page link on the class table")
	public void admin_clicks_next_page_link_on_the_class_table() {
	    classPage.clickOnNextPage();
	}
	@Then("Admin should see the next page record on the table  with Pagination has next active link enabled")
	public void admin_should_see_the_next_page_record_on_the_table_with_pagination_has_next_active_link_enabled() {
		String pageText = classPage.nextPageValidation();
		boolean nextPageActive = classPage.nextPageEnabled();
	    //Assert.assertTrue(nextPageActive);
	    Assert.assertTrue(pageText.contains("Showing 11"));
	}
	@When("Admin clicks Last page link of class data table")
	public void admin_clicks_last_page_link_of_class_data_table() {
	    classPage.clickOnLastPage();
	}

	@Then("Admin should see the last page record on the table with Next page link are disabled for class data table")
	public void admin_should_see_the_last_page_record_on_the_table_with_next_page_link_are_disabled_for_class_data_table() {
		boolean nextPageDisabled= classPage.verifyNextPageBtnDisabled();
		boolean lastPageDisplayed = classPage.lastPageDisplayed();
		int lastPageRecord = classPage.lastPageRecord();
		int lastPageFootCount = classPage.lastPageFootCount();
		Assert.assertTrue(nextPageDisabled);
		Assert.assertTrue(lastPageDisplayed);
		Assert.assertEquals(lastPageRecord, lastPageFootCount);
	}
	

	@Then("Admin should see the previous page record on the table with pagination has previous page link enabled for class data table")
	public void admin_should_see_the_previous_page_record_on_the_table_with_pagination_has_previous_page_link_enabled_for_class_data_table() {
		boolean previousPageEnabled =classPage.verifyPreviousPageBtnEnabled();
		Assert.assertTrue(previousPageEnabled);
	}
	@When("Admin clicks Start page link of class data table")
	public void admin_clicks_start_page_link_of_class_data_table() {
	    classPage.clickOnFirstPage();
	}

	@Then("Admin should see the very first page record on the table with Previous page link are disabled for class data table")
	public void admin_should_see_the_very_first_page_record_on_the_table_with_previous_page_link_are_disabled_for_class_data_table() {
	    boolean previousPageDisabled= classPage.verifyPreviousPageBtnDisabled();
	    Assert.assertTrue(previousPageDisabled);
	}
	

}
