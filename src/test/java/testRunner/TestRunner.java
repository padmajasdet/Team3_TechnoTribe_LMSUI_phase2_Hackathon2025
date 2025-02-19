package testRunner;

import org.testng.annotations.DataProvider;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = { "src/test/resources/features/Program.feature" }, 
				glue = { "hooks", "stepDefinitions" },
				monochrome = true, 
				tags="@TC1",
				dryRun = false, 
				plugin = { "pretty",

				"com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter:",
				"io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm", "html:target/Cucumber.html",
				"json:target/cucumber.json", "com.aventstack.chaintest.plugins.ChainTestCucumberListener:",
				"rerun:target/rerun.txt" // to record the failures
		// features={"@target/rerun.txt" } //to rerun only failed tests
		})

public class TestRunner extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false) // The default thread count of the dataprovider in parallel mode is 10.
	public Object[][] scenarios() {
		return super.scenarios();
	}
}
