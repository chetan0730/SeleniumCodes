package base;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import properties.ApplicationConstants;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import utils.ActionUtil;
import utils.Log;
import utils.UtilityMethods;


public class BaseTest {

	public ActionUtil action = null;
	public UtilityMethods utilityMethods;
	public WebDriver driver = null;
	public String suiteName = null;
	public String browser;

	@Parameters({ "suiteName" })
	@BeforeSuite(alwaysRun = true)
	public void suiteSetUp(@Optional("suiteName") String suiteName) throws Exception {
		this.suiteName = suiteName;

	}
	@Parameters("browser")
	@BeforeMethod(alwaysRun = true)
	public void setUp(String browser) throws Exception {
		System.out.println(browser);
		utilityMethods = new UtilityMethods();
		driver = utilityMethods.launchBrowser(browser);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		action = new ActionUtil(driver);
		
	}

	@AfterMethod
	public void afterMethod(ITestResult testResult) throws IOException {

		if (testResult.getStatus() == ITestResult.FAILURE) {
			String failedMethodName = new StringBuilder().append(testResult.getTestClass().getName()).append(".")
					.append(testResult.getName()).toString();
			Log.error("Test Failed [" + failedMethodName + "]");
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			String path = null;
			
				path = ApplicationConstants.screenshots + File.separator + File.separator + timeStamp
						+ "_ScreenShot.jpg";

			
			File f1 = new File(path);
			FileUtils.copyFile(scrFile, f1);
			String filePath = f1.getAbsolutePath();
			Reporter.setCurrentTestResult(testResult);
			Log.error("Screenshot on failed screen is stored here: " + filePath);
		}

		driver.quit();

	}

	

	public void takesSreenshot(WebDriver driver, String message) throws IOException {
		try {
			File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
			String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new java.util.Date());
			String path = null;
			
				path =  ApplicationConstants.screenshots + File.separator + suiteName + File.separator + timeStamp
						+ message.replaceAll("\\s+", "") + "_ScreenShot.jpg";

			File f1 = new File(path);
			FileUtils.copyFile(scrFile, f1);
			String filePath = f1.getAbsolutePath();
			Log.info("Took Screenshot for the action: " + message);
			Log.info("Screenshot is stored here " + filePath);
		} catch (IOException e) {
			Log.info("Taking screenshot failed");
			e.printStackTrace();
			throw new IOException(e);

		}
	}


	public static boolean deleteDirectory(File dir) {
		if (dir.isDirectory()) {
			File[] children = dir.listFiles();
			for (int i = 0; i < children.length; i++) {
				boolean success = deleteDirectory(children[i]);
				if (!success) {
					return false;
				}
			}
		}
		Log.debug("removing file or directory : " + dir.getName());
		return dir.delete();

	}	

}
