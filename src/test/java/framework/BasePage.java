package framework;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.gargoylesoftware.htmlunit.ElementNotFoundException;

import stepdefinition.SharedSD;

/**
 * Created by mohammadmuntakim on 6/9/17.
 */
public class BasePage {

	public void clickOn(By locator) {
		try {
			SharedSD.getDriver().findElement(locator).click();
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
	}

	public void sendText(By locator, String text) {
		try {
			SharedSD.getDriver().findElement(locator).sendKeys(text);
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}
	}

	public String getTextFromElement(By locator) {
		String text = null;
		try {
			text = SharedSD.getDriver().findElement(locator).getText();
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator: " + locator.toString());
			e.printStackTrace();
		}

		return text;
	}

	public void clickOnBrowserBackArrow() {
		SharedSD.getDriver().navigate().back();
	}

	public void clickOnBrowserForwardArrow() {
		SharedSD.getDriver().navigate().forward();
	}

	public void refreshBrowser() {
		SharedSD.getDriver().navigate().refresh();
	}
	// click on drop down
	public void clickOnDropdown(String text, By locator){
		try{
		Select dropdown = new Select(SharedSD.getDriver().findElement(locator));
		dropdown.selectByVisibleText(text);
	} catch (NoSuchElementException e) {
		Assert.fail("Element is  not found with this locator");
		e.printStackTrace();}
	}
		
	
	

	
	
	// Take Screenshot and save in file 
	public static void takeScreenshot(String fileName) {
		
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd_hh-mm-ss-ms");
		try  {
			File src = ((TakesScreenshot)SharedSD.getDriver()).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(src, new File(sdf.format(date) + fileName + ".jpg"));
	}    catch (IOException e) {
		e.printStackTrace(); }
		}
	// auto complete action in base page
	public void autoComplete(By clickLocator, By sendLocator, String searchText,
			 By suggestionLocator, String desiretext ) {
		try {
			SharedSD.getDriver().findElement(clickLocator).click();
			SharedSD.getDriver().findElement(sendLocator).sendKeys(searchText);

		List<WebElement> list = SharedSD.getDriver().findElements(suggestionLocator);
		for (WebElement ele : list)  {
			if(ele.getText().contains(desiretext)) {
				ele.click();
			     break;  }
		}
		}
	catch (NoSuchElementException e) {
		// Taking screenshot on BasePage action when exception is thrown
		takeScreenshot("screenshot1");
		e.printStackTrace();
		throw new NoSuchElementException("Specified element is not found");
	}}
	
	// method for accept alert
	public static void acceptAlert(String frameText, By locator) {
		try {
			SharedSD.getDriver().switchTo().frame(frameText);
			SharedSD.getDriver().findElement(locator);
			SharedSD.getDriver().switchTo().alert().accept();
	    
		}  catch (NoAlertPresentException e) {
			Assert.fail("Alert is  not found with this locator");
			e.printStackTrace();}
		}
	// method for dismiss alert
	public static void dismissAlert(String frameText, By locator) {
		try {
			SharedSD.getDriver().switchTo().frame(frameText);
			SharedSD.getDriver().findElement(locator);
			SharedSD.getDriver().switchTo().alert().dismiss();
	    
		}  catch (NoAlertPresentException e) {
			Assert.fail("Alert is not found with this locator");
			e.printStackTrace();}
		}
	// method for get Text from alert
	public static String getTextFromAlert(String frameText, By locator) {
	String text = null;
		try {

			SharedSD.getDriver().switchTo().frame(frameText);
			SharedSD.getDriver().findElement(locator);
	    text =  SharedSD.getDriver().switchTo().alert().getText();
	    SharedSD.getDriver().switchTo().alert().dismiss();
	   
		}  catch (NoAlertPresentException e) {
			Assert.fail("Alert is not found with this locator");
			e.printStackTrace();}
		return text;
		}
        
	  
	// method for send Text to alert
	public static void sendTextToAlert(String frameText, By locator, String sendText) {
		try {

		SharedSD.getDriver().switchTo().frame(frameText);
		SharedSD.getDriver().findElement(locator);
		SharedSD.getDriver().switchTo().alert().sendKeys(sendText);
		SharedSD.getDriver().switchTo().alert().dismiss();
	    
		}  catch (NoAlertPresentException e) {
			Assert.fail("Alert is not found with this locator");
			e.printStackTrace();}
		}
// Create mouse over method 	
	public static void selecfromMouseOver(By linkText1, By linkText2) {
		try {
			SharedSD.getDriver().manage().window().maximize();
		WebElement element = SharedSD.getDriver().findElement(linkText1);
		Actions action = new Actions(SharedSD.getDriver());
		action.moveToElement(element).build().perform();
		SharedSD.getDriver().findElement(linkText2).click();
		
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator");
			e.printStackTrace();}
	}
	// Switches to window based of index
	public static void switchToWindow(int index) {
	 try {  
		List<String> listOfWindows = new ArrayList<String>(SharedSD.getDriver().getWindowHandles());
		SharedSD.getDriver().switchTo().window(listOfWindows.get(index));
		}
	catch (Exception e) {
		Assert.fail("Window not found in this location");
		e.printStackTrace();}}
	
	// Closes current window and switches back to root window
	public static void switchToRootWondowAndCloseOtherWindows() {
		try {
		List<String> listOfWindows = new ArrayList<String>(SharedSD.getDriver().getWindowHandles());
		
		for(int i = 1; i < listOfWindows.size(); i++) {
			SharedSD.getDriver().switchTo().window(listOfWindows.get(i));
			SharedSD.getDriver().close();
		}
		SharedSD.getDriver().switchTo().window(listOfWindows.get(0));
	}
	catch (Exception e) {
		Assert.fail("Window not found in this location");
		e.printStackTrace();}}
	
	// Create a method for select any date from Calendar
	public void slectDateFromCalendar(String format, String date, By clickLocator,
			By calendarLocator )  {
		try {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
	    String desireDate = sdf.format(date);
	    SharedSD.getDriver().findElement(clickLocator).click();
		List<WebElement> days = SharedSD.getDriver().findElements(calendarLocator);
		ArrayList<String> selectDays = new ArrayList<>();
		
		for (WebElement day : days) {
			String expectedDay = day.getText();
			if(expectedDay.equals(desireDate)) {
				selectDays.add(expectedDay);
				day.click();
				break;
			}	
		}
		} catch (NoSuchElementException e) {
			Assert.fail("Element is not found with this locator");
			e.printStackTrace();}
	
}
	// Double click action on web element
public void doubleClickOn(By locator) {
	try {
	Actions actions = new Actions(SharedSD.getDriver());
	actions.doubleClick(SharedSD.getDriver().findElement(locator));
} catch (NoSuchElementException e) {
	Assert.fail("Element is not found with this double click locator");
	e.printStackTrace();} 
}
// Click and hold on web element
public void clickAndHold(By locator) {
	try {
	Actions actions = new Actions(SharedSD.getDriver());
	actions.clickAndHold(SharedSD.getDriver().findElement(locator));
} catch (NoSuchElementException e) {
	Assert.fail("Element is not found with click and hold locator");
	e.printStackTrace();} 
}
// Context on web element
	public void contextClick(By locator) {
		try {
		Actions actions = new Actions(SharedSD.getDriver());
		actions.contextClick(SharedSD.getDriver().findElement(locator));
	} catch (NoSuchElementException e) {
		Assert.fail("Element is not found with context click locator");
		e.printStackTrace();}
	}
	// Drag and drop  on web element
	public void dragAndDrop(By dragLocator, By dropLocator) {
		try {
	   Actions actions = new Actions(SharedSD.getDriver());
	  actions.dragAndDrop(SharedSD.getDriver().findElement(dragLocator),
			  SharedSD.getDriver().findElement(dropLocator));
			} catch (NoSuchElementException e) {
				Assert.fail("Element is not found with drag and drop locator");
				e.printStackTrace();}
	}
	// Usage of Tab key
	public void usingTabKey(By locator) {
		try {
	(SharedSD.getDriver().findElement(locator)).sendKeys(Keys.TAB);
}  catch (NoSuchElementException e) {
	Assert.fail("Element is not found with this locator");
	e.printStackTrace();}
	}
	// Generate a method to type text in CAPS
	public void typeTextInCaps(By locator, String text ){
		try {
		Actions actions = new Actions(SharedSD.getDriver());
		actions.keyDown(SharedSD.getDriver().findElement(locator), Keys.SHIFT)
				        .sendKeys(SharedSD.getDriver().findElement(locator), text)
				        .keyUp(SharedSD.getDriver().findElement(locator), Keys.SHIFT)
			            .build();
	} catch (NoSuchElementException e) {
		Assert.fail("Element is not found with this locator");
		e.printStackTrace();}
	}
	// Click on with java script
	public static void clickOnWithJavascript(By locator) {
		try {
		WebElement element = SharedSD.getDriver().findElement(locator);
		JavascriptExecutor js = (JavascriptExecutor)SharedSD.getDriver();
		js.executeScript("arguments[0].click();", element);
	} catch (NoSuchElementException e) {
		Assert.fail("Element is not found with this locator");
		e.printStackTrace();}
	}
	// Vertical scroll - down any web page
	public static void scrollOnThePage() {
		try {
		JavascriptExecutor js = (JavascriptExecutor)SharedSD.getDriver();
		js.executeScript("window.scrollBy(0,1000)");
	}  catch (Exception e) {
		Assert.fail("Exeption is found");
		e.printStackTrace();}
	}
	// Implicit wait 
	public void implicitWait() {
		try {
			SharedSD.getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}  catch (TimeoutException e) {
		Assert.fail("Timeout Exeption is found");
		e.printStackTrace();}
	}
	// Fluent wait
	public static WebElement fluentWait(final By locator) {
		
		Wait<WebDriver> wait = new FluentWait<WebDriver>(SharedSD.getDriver())
				.withTimeout(15, TimeUnit.SECONDS)
				.pollingEvery(1, TimeUnit.SECONDS)
				.ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class)
				.ignoring(ElementNotFoundException.class)
				.withMessage(
						"WebDriver waited for 15 seconds but still could not find the element therefore timeout exception has been thrown");
	WebElement element = wait.until(new Function<WebDriver, WebElement>() {
		   public WebElement apply(WebDriver driver) {
			   return driver.findElement(locator);
		   }
	});  return element;
		}
 // wait until WebElement be click able
	public static void waitUntilClickable(By locator) {
		try {
		WebDriverWait wait = new WebDriverWait(SharedSD.getDriver(), 10);
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
	}  catch (TimeoutException e) {
		Assert.fail("Timeout Exeption is found");
		e.printStackTrace();}
	}
	// Page load wait method
	public static void pageLoadWait() {
		try {
			SharedSD.getDriver().manage().timeouts().pageLoadTimeout(30, TimeUnit.SECONDS);
		} catch (TimeoutException e) {
			Assert.fail("Timeout Exeption is found");
			e.printStackTrace();}
		}
	// Script timeout
	public static void asynchronousScript() {
		try {
			SharedSD.getDriver().manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
	} catch (TimeoutException e) {
		Assert.fail("Timeout Exeption is found");
		e.printStackTrace();}
	}
	
	
}
