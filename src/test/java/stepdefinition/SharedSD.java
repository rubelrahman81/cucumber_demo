package stepdefinition;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class SharedSD {
	
	 public static final String USERNAME = "rubelrahman81";
	  public static final String ACCESS_KEY = "3785db61-4053-498a-bc46-35f9a31c7985";
	  public static final String URL = "https://" + USERNAME + ":" + ACCESS_KEY + "@ondemand.saucelabs.com:443/wd/hub";

	private static final String FACEBOOKURL = "https://facebook.com/";
	private static WebDriver driver = null;

	@Given("^I open the default browser$")
	public static void before() throws Exception{

//		System.setProperty("webdriver.chrome.driver",
//				"/Users/rubel/Desktop/java lib/chromedriver.exe");
//		driver = new ChromeDriver();
		 DesiredCapabilities caps = DesiredCapabilities.chrome();
	 	    caps.setCapability("platform", "Windows XP");
	 	    caps.setCapability("version", "43.0");
	 	 
	 	     driver = new RemoteWebDriver(new URL(URL), caps);
	        
	 	   driver.get(FACEBOOKURL);

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
	}

	@Then("^I close the default browser$")
	public static void after() {
		if (driver != null) {
			driver.manage().deleteAllCookies();
			driver.quit();
		}
	}

	public static WebDriver getDriver() {
		return driver;
	}
}
