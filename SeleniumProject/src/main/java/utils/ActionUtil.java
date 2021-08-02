package utils;

import static org.testng.Assert.assertTrue;

import java.util.Iterator;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ActionUtil {

	private WebDriver driver;

	public ActionUtil(WebDriver driver) {
		this.driver = driver;
	}

	 
	

	public void click(By byEle, Integer waitFor) {
		Log.info("Clicking with wait on the element identified by the locator = [" +byEle.toString()+"]");
		try {
			if (waitFor <= 0)
				driver.findElement(byEle).click();
			else if (waitFor > 0)
				waitForElementToBeClickable(byEle, waitFor).click();
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		Log.info("Complteed clicking with wait on the element identified by the locator = [" +byEle.toString()+"]");
	}
	
	public void click(By byEle) {
		Log.info("Clicking on the element identified by the locator = [" +byEle.toString()+"]");
		try {
			
				waitForElementToBeClickable(byEle, 10).click();
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		Log.info("Completed clicking on the element identified by the locator = [" +byEle.toString()+"]");
	}
	

	public void typeIn(By byEle, String characters) {
		Log.info("Typing the string ["+characters+"] on the element identified by the locator= [" +byEle.toString()+"]");
		try {
			waitForElementVisibility(byEle, 10).sendKeys(characters);
		} catch (Exception e) {
			Log.info(e.getMessage());
		}
		Log.info("Completed typing the string ["+characters+"] on the element identified by the locator= [" +byEle.toString()+"]");
	}
	
	public void typeIn(WebElement ele,By byEle, String characters) {
		Log.info("Typing the string ["+characters+"] on the element identified by the locator = [" +byEle.toString()+"]");
		try {
			Thread.sleep(2000);
			ele.findElement(byEle).sendKeys(characters);
			
		} catch (Exception e) {
			Log.info(e.getMessage());

		}
		Log.info("Completed typing the string ["+characters+"] on the element identified by the locator = [" +byEle.toString()+"]");
	}

	public WebElement waitForElementVisibility(By byEle, Integer timeOut) {
		WebElement element = null;
		Log.info("Waiting for the Element to appear on screen identified by the locator= [" +byEle.toString()+"]");
		try {
			Thread.sleep(2000);

			element = new WebDriverWait(driver, timeOut.longValue())
					.until(ExpectedConditions.visibilityOfElementLocated(byEle));
			String original_style = element.getAttribute("style");
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("arguments[0].style.border='3px solid red'", element);
			Thread.sleep(500);
			js.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style", original_style);

		} catch (Exception e) {
			Log.info(e.getMessage());
			assertTrue(false,byEle+"Element not found"); 
		}
		Log.info("Element found on screen identified by the locator= [" +byEle.toString()+"]");
		return element;
		
	}

	public WebElement waitForElementToBeClickable(By byEle, Integer timeOut) {
		Log.info("Waiting for the Element to be clickable identified by the locator= [" +byEle.toString()+"]");
		WebElement element = null;
		try {

			element = new WebDriverWait(driver, timeOut.longValue())
					.until(ExpectedConditions.elementToBeClickable(byEle));

		} catch (Exception e) {
			Log.info(e.getMessage());
			assertTrue(false,byEle+"Element not found"); 
		}
		return element;
	}
	
	
	
	
}


