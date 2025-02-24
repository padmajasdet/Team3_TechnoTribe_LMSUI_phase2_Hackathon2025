package pageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import utilities.ReadConfig;

public class testing2 {
	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		WebDriver driver = new ChromeDriver();
		LoginPage loginPage = new LoginPage(driver);
		ReadConfig readConfig = new ReadConfig();
		JavascriptExecutor js = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		driver.get("https://feb-ui-hackathon-bbfd38d67ea9.herokuapp.com/");
		WebElement username = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement password = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement dropDownArraow = driver.findElement(By.xpath(
				"//span[text()='Select the role']/..//following-sibling::div[contains(@class,'arrow-wrapper')]"));
		WebElement loginbutton = driver.findElement(By.xpath("//button[@id='login']"));
		loginPage.doLoginWithValidCredentials(readConfig.getUsername(), readConfig.getPassword(), "Admin");

		WebElement classBtn = driver.findElement(By.xpath("//span[text()='Class']"));

		js.executeScript("arguments[0].click();", classBtn);

		WebElement addNewClassBtn = driver.findElement(By.xpath("//button[text()='Add New Class']"));
		Thread.sleep(1000);
		js.executeScript("arguments[0].click();", classBtn);
		js.executeScript("arguments[0].click();", addNewClassBtn);
		Thread.sleep(3000);
		WebElement batchNameDrpdw = driver.findElement(By.xpath("//input[@placeholder='Select a Batch Name']"));

		//batchNameDrpdw.click();
		batchNameDrpdw.sendKeys("SMPO33");
		WebElement classTopicTextbox = driver.findElement(By.xpath("//input[@id='classTopic']"));
		classTopicTextbox.click();
		classTopicTextbox.sendKeys("Java");
		WebElement ClassDescriptionTextbox = driver.findElement(By.xpath("//input[@id='classDescription']"));

		ClassDescriptionTextbox.click();
		ClassDescriptionTextbox.sendKeys("Learning java");

		// Open the date picker
		WebElement datePicker = driver.findElement(By.xpath("//input[@id='icon']")); // Replace with actual locator
		datePicker.click();

		// Wait for the calendar to appear
		Thread.sleep(1000);

		// WebElement calendar =
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		wait.until(ExpectedConditions.visibilityOfElementLocated(
				By.xpath("//div[@class='p-datepicker-group ng-tns-c92-13 ng-star-inserted']")));

		// Navigate to the desired month
		WebElement nextMonthButton = driver
				.findElement(By.xpath("//span[@class='p-datepicker-next-icon pi pi-chevron-right ng-tns-c92-13']"));
		WebElement currentMonthText = driver
				.findElement(By.xpath("//span[@class='p-datepicker-month ng-tns-c92-13 ng-star-inserted']"));

		//System.out.println(currentMonthText.getText());
		String month = "February";
		while (!currentMonthText.getText().contains(month)) {
			wait.until(ExpectedConditions.visibilityOfElementLocated(
					By.xpath("//span[@class='p-datepicker-next-icon pi pi-chevron-right ng-tns-c92-13']")));
			js.executeScript("arguments[0].click();", nextMonthButton);

		}
		
		WebElement calenderTextbox = driver.findElement(By.xpath("//input[@id='icon']"));
		Actions actions = new Actions(driver);

		WebElement staffName = driver.findElement(By.xpath("//input[@placeholder='Select a Staff Name']"));
		actions.contextClick(calenderTextbox).perform();
		calenderTextbox.click();
		calenderTextbox.sendKeys("02/25/2025");

		js.executeScript("arguments[0].scrollIntoView(true);", staffName);
		staffName.click();
		staffName.sendKeys("Saranya");
		WebElement No_of_ClassesTextbox = driver.findElement(By.xpath("//input[@id='classNo']"));
		js.executeScript("arguments[0].scrollIntoView(true);", No_of_ClassesTextbox);
		WebElement statusActive = driver.findElement(By.xpath("//div[normalize-space()='Active']/p-radiobutton"));
		statusActive.click();

		WebElement saveBtn = driver.findElement(By.xpath("//button[@label='Save']"));
		saveBtn.click();
		WebElement classCreated=driver.findElement(By.xpath("//div[text()='Successful']"));
		
		
		

	}

}
