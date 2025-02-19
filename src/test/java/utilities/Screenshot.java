package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;
import java.io.IOException;

public class Screenshot {
    
    // screeshot take method
    public static void takeScreenshot(WebDriver driver, String scenarioName) {
        
        File screenshotDir = new File("screenshots");// directory to take screenshot
        if (!screenshotDir.exists()) {
            screenshotDir.mkdir(); //  directory created
        }
        
        //screenshot capturing
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            // Save the screenshot in the specified directory
            FileHandler.copy(screenshot, new File(screenshotDir, scenarioName + ".png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

