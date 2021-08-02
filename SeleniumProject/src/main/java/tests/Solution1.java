package tests;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import finders.Selectors;

public class Solution1 extends BaseTest {
  @Test()
  public void flipkartAction() throws InterruptedException, IOException {
	  driver.get("https://www.flipkart.com/");
	  action.waitForElementVisibility(Selectors.CLOSE_REGISTRATION, 30);
	  action.click(Selectors.CLOSE_REGISTRATION);
	  action.waitForElementVisibility(Selectors.FLIPKART_SEARCH_BOX, 20);
	  action.typeIn(Selectors.FLIPKART_SEARCH_BOX, "MacBook Air");
	  action.click(Selectors.FLIPKART_SEARCH_BUTTON);
	 
	  action.waitForElementVisibility(Selectors.MACBOOKAIR2, 20);
	  action.click(Selectors.MACBOOKAIR2);
	  String parentWindow = driver.getWindowHandle();
	  Set<String> windows= driver.getWindowHandles();
	 Iterator<String > it = windows.iterator();
	 while(it.hasNext()) {
		 String childWindow = it.next();
		 if(!(parentWindow.equals(childWindow))) {
			 driver.switchTo().window(childWindow);
		 }
	 }
	 action.waitForElementVisibility(Selectors.MACBOOKAIR2_TITLE, 20);
	  String macBookTitle = driver.findElement(Selectors.MACBOOKAIR2_TITLE).getText();
	  Assert.assertEquals(macBookTitle, "APPLE MacBook Air Core i5 5th Gen - (8 GB/128 GB SSD/Mac OS Sierra) MQD32HN/A  (13.3 inch, Silver, 1.35 kg)");
	 String macBookPrice = driver.findElement(Selectors.MACBOOKAIR2_PRICE).getText();
	 Assert.assertTrue(macBookPrice.contains("74,990"), "Incorrect Price");
	 action.typeIn(Selectors.ZIPCODE_BOX, "411001");
	 action.click(Selectors.ZIPCODE_CHECK);
	 action.click(Selectors.ADDTOCART);
	 action.waitForElementVisibility(Selectors.GOTOCART, 30);
	 Thread.sleep(3000);
	  
	  
	  
	  
  }
}
