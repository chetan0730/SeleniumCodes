package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Parameters;


public class UtilityMethods {

	public WebDriver driver = null;
	//public String browser;
	/*
	 * initiate and Launch browser based on OS. Returns Driver instance
	 */
	 
	public WebDriver launchBrowser(String browser) throws Exception {
	Log.debug("start of Launch Browser method");
	
	
	if(browser.equalsIgnoreCase("firefox")){
		//create firefox instance
		System.setProperty("webdriver.gecko.driver", "resources/geckodriver.exe");
		driver= new FirefoxDriver();
		driver.manage().window().maximize();
		}
		//Check if parameter passed as 'chrome'
		else if(browser.equalsIgnoreCase("chrome")){
		//set path to chromedriver.exe
			System.setProperty("webdriver.chrome.driver", "resources/chromedriver.exe");
			driver = new ChromeDriver();
			driver.manage().window().maximize();
		}
		Log.debug("End of Launch Browser method");
	
		return driver;

	}

}
