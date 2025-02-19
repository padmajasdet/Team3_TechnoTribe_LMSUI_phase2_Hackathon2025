package hooks;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ReadConfig;

public class TestContext {

	private WebDriver driver;
	private DriverFactory driverFactory;
	
	private ReadConfig readConfig;
	

	// initializing the DriverFactory
	public TestContext() {
		this.driverFactory = new DriverFactory();
		this.readConfig = new ReadConfig(); // config reader initilise

	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
		
		
		

	}

	public DriverFactory getDriverFactory() {
		return driverFactory;
	}

	// WebDriver instance
	public WebDriver getDriver() {
		return driver;
	}

// driver close
	public void closeDriver() {
		driverFactory.closeDriver();
	}

	

	public String getApplicationURL() {
		return readConfig.getApplicationURL(); // Method to get URL
	}

	
	}

	

