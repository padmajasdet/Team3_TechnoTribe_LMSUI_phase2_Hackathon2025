package utilities;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ElementUtil {
	

	private WebDriver driver;
	Alert alert;

	public ElementUtil(WebDriver driver) {
		this.driver = driver;
	}

	public void doClick(WebElement ele) {

		try {

			WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(ele));

			if (ele.isDisplayed()) {
				if (ele.isEnabled()) {

					try {

						ele.click();

					} catch (Exception e) {
						// JavascriptExecutor(driver) =
					}

				} else {
					throw new Exception("Element is not enabled");
				}
			} else {
				throw new Exception("Element is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void doClick(By locator) {

		try {

			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(20))
					.until(ExpectedConditions.visibilityOf(getElement(locator)));
			if(ele == null)
			{
				//LoggerLoad.info("ele is null");
			}
			else
			{
				//LoggerLoad.info("ele is not null"+ele.toString());
			}


			if (ele.isDisplayed()) {
				if (ele.isEnabled()) {

					try {

						ele.click();

					} catch (Exception e) {
						// JavascriptExecutor(driver) =
					}

				} else {
					//LoggerLoad.error("Element is not enabled");
					throw new Exception("Element is not enabled");
				}
			} else {
				//LoggerLoad.error("Element is not displayed");
				throw new Exception("Element is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public String getElementText(By locator) {
		elementWithFluentWaitLocated(locator, 20, 100);
		return getElement(locator).getText();
	}

	public String getElementText(WebElement ele) {
		return ele.getText();
	}

	public void setSelectOption(By locator, String text) {
		Select select = new Select(getElement(locator));
		select.selectByVisibleText(text);
	}

	public WebElement elementWithFluentWaitLocated(By locator, int timeOutInSeconds, int pollingIntervalInMillis) {

		WebElement ele = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingIntervalInMillis)).ignoring(NoSuchElementException.class)
				.until(ExpectedConditions.visibilityOfElementLocated(locator));

		return ele;

	}

	public WebElement elementWithFluentWaitClickable(By locator, int timeOutInSeconds, int pollingIntervalInMillis) {

		WebElement ele = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeOutInSeconds))
				.pollingEvery(Duration.ofMillis(pollingIntervalInMillis))
				.ignoring(ElementClickInterceptedException.class)
				.until(ExpectedConditions.elementToBeClickable(locator));

		return ele;

	}

	public WebElement getElement(By locator) {
		return driver.findElement(locator);
	}

	public List<WebElement> getElements(By locator) {
		elementWithFluentWaitLocated(locator, 10, 100);
		return driver.findElements(locator);
	}
		

	public void doSendKeys(By locator, Keys keys) {
		WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
				.until(ExpectedConditions.visibilityOf(getElement(locator)));
		ele.sendKeys(keys);

	}

	public void doSendKeys(By locator, String text) {
		try {

			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(10))
					.until(ExpectedConditions.visibilityOf(getElement(locator)));

			if (ele.isDisplayed()) {
				if (ele.isEnabled()) {

					try {
						ele.click();
						ele.clear();
						//clearExistingText(ele);
						ele.sendKeys(text);
					} catch (Exception e) {
						System.out.println(e.getMessage()); 
					}

				} else {
					throw new Exception("Element is not enabled");
				}
			} else {
				throw new Exception("Element is not displayed");
			}

		} catch (Exception e) {
			// LoggerLoad.error("Exception Happened: "+e.getMessage());
		}

	}

	public int getElementSize(By locator) {

		int size = 0;
		try {

			List<WebElement> ele = new WebDriverWait(driver, Duration.ofSeconds(40))
					.until(ExpectedConditions.visibilityOfAllElements(getElements(locator)));
			size = ele.size();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return size;
	}

	public boolean isElementDisplayed(By locator) {
		boolean flag = false;
		try {

			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.visibilityOf(getElement(locator)));

			if (ele.isDisplayed()) {

				flag = true;
			}

			else {
				throw new Exception("Element is not displayed");
			}

		} catch (Exception e) {
			e.printStackTrace();

		}
		return flag;
	}
	
	public boolean isElementAvailable(By locator) {
		boolean flag = false;
		try {

			WebElement ele = new WebDriverWait(driver, Duration.ofSeconds(30))
					.until(ExpectedConditions.visibilityOf(getElement(locator)));

			if (ele!=null && ele.isDisplayed()) {

				flag = true;
			}

			else {
				return flag;
			}

		} catch (Exception e) {
			return flag;
			//e.printStackTrace();

		}
		return flag;
	}


	/*
	 * public boolean isElementsDisplayed(By locator) { boolean flag = false; try {
	 * 
	 * List<WebElement> ele = new WebDriverWait(driver, Duration.ofSeconds(30))
	 * .until(ExpectedConditions.visibilityOfAllElements(getElements(locator)));
	 * 
	 * if (ele.) {
	 * 
	 * flag = true; }
	 * 
	 * else { throw new Exception("Element is not displayed"); }
	 * 
	 * } catch (Exception e) { e.printStackTrace();
	 * 
	 * } return flag; }
	 */

	public void selectDropDOwnOption(String option) {
		By locator = By.xpath("//ul[@role='listbox']//*[text()='" + option + "']");
		elementWithFluentWaitClickable(locator, 30, 100).click();
	}

	public void clearExistingText(WebElement ele) {
		ele.sendKeys(Keys.CONTROL);
		ele.sendKeys("A");
		ele.sendKeys(Keys.BACK_SPACE);

	}
	
    public void clearField(By locator) {
        WebElement element = new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
    }

	public String getAttributeVal(By locator, String attributeName) {
		return elementWithFluentWaitLocated(locator, 10, 100).getAttribute(attributeName);
	}

	public void attachFileUsingSendKeys(By locator, String filePath) {
		try {
			doSendKeys(locator, filePath);
		} catch (Exception e) {

		}
	}
	// Method to check if a field is editable
    public boolean isFieldEditable(By locator) {
        WebElement element = driver.findElement(locator);
        try {
            // Try to send keys and catch exception if it is not editable
            element.sendKeys("test");  // Attempt to type something
            element.clear();           // Clear after testing
            return true;
        } catch (Exception e) {
            return false;
        }
    }

	public void scrollToBottomOfPage() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_DOWN).build().perform();
	}

	public void scrollToTopOfPage() {
		Actions action = new Actions(driver);
		action.sendKeys(Keys.PAGE_UP).build().perform();
	}
	
	public void scrollIntoView(By locator) {
		
	   ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView(true);", getElement(locator));     
	}

	public String getPageURL() {

		return driver.getCurrentUrl();
	}

	public String getPageTitle() {

		return driver.getTitle();
	}

	public void doSendkeysUsingJS(By locator, String codeString) {
		JavascriptExecutor jse = (JavascriptExecutor) driver;
		jse.executeScript("arguments[0].value='" + codeString + "';", getElement(locator));

	}
	
	public boolean isEditablefield(By locator) {
        WebElement editableElement = getElement(locator);
		JavascriptExecutor js = (JavascriptExecutor) driver;
        Boolean isContentEditable = (Boolean) js.executeScript("return arguments[0].isContentEditable;", editableElement);
		return isContentEditable;
	}
	public boolean ifFieldIsRequired(By locator) {
		WebElement ele = elementWithFluentWaitLocated(locator, 5, 100);
		JavascriptExecutor js = (JavascriptExecutor) driver;
		boolean isRequired = (Boolean) js.executeScript("return arguments[0].required;", ele);
		return isRequired;
	}

	public void clickAndDeleteTextUsingAction(By locator) {
		Actions action = new Actions(driver);
		action.moveToElement(getElement(locator)).doubleClick().click().sendKeys(Keys.BACK_SPACE).perform();
	}

	public void mouseclickUsingAction(By locator) {
		Actions action = new Actions(driver);
		action.click(getElement(locator)).perform();
	}

	public void pressdownButtonUsingAction(By locator) {
		Actions action = new Actions(driver);
		action.keyDown(getElement(locator), Keys.ENTER).perform();
	}
	
	public void pressdownArrowButtonUsingAction() {
		Actions action = new Actions(driver);
		action
		.keyDown(Keys.DOWN)
		.keyUp(Keys.DOWN)
        .perform();
	}
	public void pressEnterButtonUsingAction() {
		Actions action = new Actions(driver);
		action
		.keyDown(Keys.ENTER)
		.keyUp(Keys.ENTER)
        .perform();
	}

	
	public String getAlertMsg() {

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
		if (!(wait.until(ExpectedConditions.alertIsPresent()) == null)) {
			alert = driver.switchTo().alert();
			return alert.getText();
		} else
			return "Alert is not present";
	}

	public void clickElementByJS(By locator, WebDriver driver) {
		JavascriptExecutor js = ((JavascriptExecutor) driver);
		js.executeScript("arguments[0].click();", getElement(locator));
	}

	public void goToProgramMenu() {
		driver.findElement(By.id("batch")).click();
		//clickElementByJS(By.id("batch"),driver);
		Actions action = new Actions(driver);
		action.keyDown(Keys.SHIFT).keyDown(Keys.TAB) // the focus has come to program menu
				.keyUp(Keys.TAB).keyUp(Keys.SHIFT).keyDown(Keys.ENTER).build().perform();
	}

	public boolean isElementEnabled(By locator) {
		return getElement(locator).isEnabled();
	}
	
	public static boolean validator(String actual, String expected) {

		if(actual.equalsIgnoreCase(expected)) {
			return true;
		}
		else {
			return false;
		}
	}

	public boolean isElementPresent(By locator) {
		try {
			driver.findElement(locator);
            return true;
		} catch (Exception e) {
			// TODO: handle exception
            return false;

		}
        
    }
	
	public List<String> printWebElements(List<WebElement> options) {

		List<String> texts = new ArrayList<String>();
		int i=0;
		for(WebElement option: options) {
			texts.add(i,option.getText());
			i++;
		}
		//LoggerLoad.info("The number of items in the list are: "+ texts.size());
		return texts;
	}
	
	public ArrayList<Integer> printWebElementsNC(ArrayList<WebElement> options) {

		ArrayList<Integer> integ = new ArrayList<Integer>();
		int i=0;
		for(WebElement option: options) {
			String variable = option.getText();
			int Num = Integer.parseInt(variable);
			integ.add(i, Num);
			i++;
		}
		//LoggerLoad.info("The number of items in the list are: "+ integ.size());
		return integ;
	}
	
	public ArrayList<WebElement> getElementsint(By locator) {
		return (ArrayList<WebElement>) driver.findElements(locator);
	}
	
	public List<String> getSortedList(List<String> originalList){
		
		//LoggerLoad.info("Original List Before sorting is"+ originalList);
        List<String> sortedList = new ArrayList<>(originalList);
        Collections.sort(sortedList, String.CASE_INSENSITIVE_ORDER);
		//LoggerLoad.info("Sorted List After sorting is"+ sortedList);
        return sortedList;
	}
	
	public ArrayList<Integer> getSortedNCList(ArrayList<Integer> originalNCList){
		
		//LoggerLoad.info("Original List Before sorting is"+ originalNCList);
        ArrayList<Integer> sortedList = new ArrayList<>(originalNCList);
        Collections.sort(sortedList);
		//LoggerLoad.info("Sorted List After sorting is"+ originalNCList);
        return sortedList;
	}
	
	public List<String> getSortedListDescending(List<String> originalList){
		
		//LoggerLoad.info("Original List Before sorting is"+ originalList);
        List<String> sortedList = new ArrayList<>(originalList);
        Collections.sort(originalList, (s1, s2) -> s2.compareToIgnoreCase(s1));
		//LoggerLoad.info("Sorted List After sorting is"+ sortedList);
        return sortedList;
	}
	
	public ArrayList<Integer> getSortedListNCDescending(ArrayList<Integer> originalNCList){
		
		//LoggerLoad.info("Original List Before sorting is"+ originalNCList);
        ArrayList<Integer> sortedList = new ArrayList<>(originalNCList);
        Collections.sort(originalNCList, (s1, s2) -> s2.compareTo(s1));
		//LoggerLoad.info("Sorted List After sorting is"+ originalNCList);
        return sortedList;
	}

	public  String getRandomNumbers(int length) {
		 StringBuilder result = new StringBuilder();
	        Random random = new Random();

	        for (int i = 0; i < length; i++) {
	            int digit = random.nextInt(10); // Generates a random number between 0 and 9
	            result.append(digit);
	        }
	        return result.toString();
	    
    }

}
