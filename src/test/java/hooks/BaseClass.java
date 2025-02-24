package hooks;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import utilities.Log;
import utilities.ReadConfig;
import utilities.Screenshot;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;


public class BaseClass {

	private TestContext context;
	private ReadConfig readConfig;

	// inject TestContext constructor
	public BaseClass(TestContext Context) {
		this.context = Context;
		this.readConfig = new ReadConfig();
	}

	@Before("not @login")
	public void setUp() {
		Log.logInfo("Initializing WebDriver");
		String browserName = readConfig.getbrowser();
		WebDriver driver = context.getDriverFactory().initialiseBrowser(browserName);
		context.setDriver(driver);
		Log.logInfo("Navigating to: " + readConfig.getApplicationURL());
		context.getDriver().get(readConfig.getApplicationURL());

	}

	@After
	public void tearDown(Scenario scenario) {
		Log.logInfo("Screenshots for failed");
		if (scenario.isFailed()) {
			// Use context.getDriver() to take a screenshot
			//Screenshot.takeScreenshot(context.getDriver(), scenario.getName());
			final byte[] screenshot = ((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES);
			scenario.attach(screenshot, "image/png", "Myscreenshot");
			Allure.addAttachment("Myscreenshot",
					new ByteArrayInputStream(((TakesScreenshot) context.getDriver()).getScreenshotAs(OutputType.BYTES)));
		}
		Log.logInfo("Closing WebDriver");
		context.closeDriver();

	}

}
