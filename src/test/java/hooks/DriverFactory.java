package hooks;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;



import java.time.Duration;

public class DriverFactory {
	 private WebDriver driver;
	 
	 	 
	 
	 
	 public WebDriver initialiseBrowser(String browserName) {
	        if (driver == null) {
	            switch (browserName.toLowerCase()) {
	                case "chrome":
	                   
	                    driver = new ChromeDriver();
	                    break;
	                case "firefox":
	                    
	                    driver = new FirefoxDriver();
	                    break;
	                case "edge":
	                   
	                    driver = new EdgeDriver();
	                    break;
	                case "safari":
	                   
	                    driver = new SafariDriver();
	                    break;
	                default:
	                    throw new IllegalArgumentException("Browser not supported: " + browserName);
	            }
	            driver.manage().window().maximize();
	            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	        }
	        return driver;
	    }
	 
	   public WebDriver getDriver() {
	        return driver;
	    }

	
	   public void closeDriver() {
	        if (driver != null) {
	            driver.quit();
	        }
	    }
}