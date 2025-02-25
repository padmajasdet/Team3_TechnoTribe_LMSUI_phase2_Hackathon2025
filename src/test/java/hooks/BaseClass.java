package hooks;

import java.io.IOException;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import utilities.Log;
import utilities.ReadConfig;
import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import io.cucumber.java.After;
import io.cucumber.java.AfterAll;
import io.cucumber.java.Before;
import io.cucumber.java.BeforeAll;
import io.cucumber.java.Scenario;
import utilities.ExcelReader;


public class BaseClass {

	private TestContext context;
	private ReadConfig readConfig;

	// inject TestContext constructor
	public BaseClass(TestContext Context) {
		this.context = Context;
		readConfig = new ReadConfig();
	}

	@BeforeAll
    public static void externalFIleOrAppSetUp() throws Exception {
        
		String excelPath = new ReadConfig().getExcelPath();
        System.out.println("Excel file path = " + excelPath); 
        if(excelPath != null) {
        	
        	ExcelReader.openExcel(excelPath);
    	    System.out.println("Excel file opened successfully.");
        	}  
        else System.out.println("No excel path found");
        }
		

	@Before("not @login")
	public void setUp() {
		
		Log.logInfo("Initializing WebDriver");
		
		String browserName = null;
		if(readConfig.getBrowserFromTestNG() != null ) {
			browserName = readConfig.getBrowserFromTestNG();
		}
		else {
			browserName = readConfig.getbrowser();
		}
			
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

	@AfterAll
	public static void externalFIleOrAppTearDown() {
		try {
			// Close the Excel file
			ExcelReader.closeExcel();
			System.out.println("Excel file closed successfully.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// to attach screeshots in allure
	@Attachment(value = "Screenshot", type = "image/png")
	public byte[] attachScreenshot(WebDriver driver) {
		// Capture the screenshot and return it as bytes
		return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
	}

}
