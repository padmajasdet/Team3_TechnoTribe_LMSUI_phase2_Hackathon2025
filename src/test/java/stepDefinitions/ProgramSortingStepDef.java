package stepDefinitions;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import io.cucumber.java.en.*;

import hooks.TestContext;
import pageObjects.HomePage;
import pageObjects.ProgramPage;
import utilities.ReadConfig;

public class ProgramSortingStepDef {
	WebDriver driver;
	TestContext context;
	ReadConfig readConfig;
	HomePage homePage;
	ProgramPage programPage;
	public ProgramSortingStepDef(TestContext context) {
		this.context = context;
		this.driver = context.getDriver();
		this.readConfig = new ReadConfig();
		programPage = new ProgramPage(driver);
		homePage = new HomePage(driver);
	}
	@When("Admin clicks on Arrow next to Program Name of Program module page for sort ascending")
	public void admin_clicks_on_arrow_next_to_program_name_of_program_module_page_for_sort_ascending() {
		programPage.clickProgramNameSort();
	}

	@Then("Admin See the Program Name is sorted in ascending order")
	public void admin_see_the_program_name_is_sorted_in_ascending_order() {
		List<String> originalList = programPage.getOriginalList("ProgramName");
		List<String> sortedList = programPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString() );
		Assert.assertTrue(originalList.equals(sortedList));
}
	@When("Admin clicks on Arrow next to Program Name of Program module page for sort descend")
	public void admin_clicks_on_arrow_next_to_program_name_of_program_module_page_for_sort_descend() {
		programPage.clickProgramNameSortDescend();
	}
	
	@Then("Admin See the Program Name is sorted in descending order")
	public void admin_see_the_program_name_is_sorted_in_descending_order() {
		List<String> originalList = programPage.getOriginalList("ProgramName");
		List<String> sortedList = programPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}
	
	@When("Admin clicks on Arrow next to program description of Program module page for sort ascending")
	public void admin_clicks_on_arrow_next_to_program_description_of_program_module_page_for_sort_ascending() {
	    programPage.clickProgramDescriptionSort();
	}

	@Then("Admin See the program description is sorted Ascending order in Program module page")
	public void admin_see_the_program_description_is_sorted_ascending_order_in_program_module_page() {
		List<String> originalList = programPage.getOriginalList("ProgramDescription");
		List<String> sortedList = programPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}
	
	@When("Admin clicks on Arrow next to program description of Program module page for sort descending")
	public void admin_clicks_on_arrow_next_to_program_description_of_program_module_page_for_sort_descending() {
		programPage.clickProgramDescriptionSortDes();
	}

	@Then("Admin See the program description is sorted Descending order in Program module page")
	public void admin_see_the_program_description_is_sorted_descending_order_in_program_module_page() {
		List<String> originalList = programPage.getOriginalList("ProgramDescription");
		List<String> sortedList = programPage.getSortedListDescending(originalList);
		System.out.println("Descending sorted name list " + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}
	
	@When("Admin clicks on Arrow next to program Status of Program module page for sort ascending")
	public void admin_clicks_on_arrow_next_to_program_status_of_program_module_page_for_sort_ascending() {
		 programPage.clickProgramStatusSort();
	}

	@Then("Admin See the program Status is sorted Ascending order in Program module page")
	public void admin_see_the_program_status_is_sorted_ascending_order_in_program_module_page() {
		List<String> originalList = programPage.getOriginalList("ProgramStatus");
		List<String> sortedList = programPage.getSortedList(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}
	
	@When("Admin clicks on Arrow next to program Status of Program module page for sort descending")
	public void admin_clicks_on_arrow_next_to_program_status_of_program_module_page_for_sort_descending() {
		 programPage.clickProgramStatusSortDes();
	}

	@Then("Admin See the program Status is sorted descending order in Program module page")
	public void admin_see_the_program_status_is_sorted_descending_order_in_program_module_page() {
		List<String> originalList = programPage.getOriginalList("ProgramStatus");
		List<String> sortedList = programPage.getSortedListDescending(originalList);
		System.out.println("sorted name list" + sortedList.toString());
		Assert.assertTrue(originalList.equals(sortedList));
	}
	

}
