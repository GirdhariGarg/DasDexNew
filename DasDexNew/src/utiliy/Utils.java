package utiliy;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Utils {
	public static WebDriver driver;
	
	public static String getTestCaseName(String sTestCaseName){
		try{
			String value  = sTestCaseName;
			int posi = sTestCaseName.indexOf("@");
			value = value.substring(0, posi);
			posi =  value.lastIndexOf(".");
			value = value.substring(posi + 1);
			Log.info("TestCase Extracted");
			return value;
		}catch(Exception e){
			Log.error("Class Utils | Method getTestCaseName|Exception desc "+e.getMessage());
			throw (e);
		}
	}
	
	//This method will send sheetname
	public static String getSheetName(String sTestCase){
		String sheet = null;
		try{
			String value = sTestCase;
			int posi = value.indexOf("_");
			value  = value.substring(0, posi);
			switch(value){
			case "LG":
			sheet = "Login";
			}
			}catch(Exception e){
				Log.error("Class Utils| Method getSheetName| Exception desc"+e.getMessage());
				throw(e);
			}
		return sheet;
	}

	public static WebDriver openBrowser(){
		try{
		String Browser = Constant.Browser;
		switch(Browser){
		case "firefox":
			driver = new FirefoxDriver();
			Log.info("Firefox Browser initialized");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Log.info("20 Second implecit wait applied");
			driver.get(Constant.url);
			Log.info("Website Launched successfully");
			break;
			
		case "chrome":
			//DesiredCapabilities capability = DesiredCapabilities.chrome();
			//capability.setCapability(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, true);
			System.setProperty("webdriver.Chrome.driver", "D:\\Only for Selenium use\\Chrome Driver\\chromedriver.exe");
			driver = new ChromeDriver();
			Log.info("Chrome Browser Intilatized");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Log.info("Impecit wait is applied for 20 seconds");
			driver.get(Constant.url);
			Log.info("Website launched successfully");
			break;
			
		case "IE":
			//DesiredCapabilities capability = DesiredCapabilities.internetExplorer();
			//capability.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			System.setProperty("webdriver.ie.driver", "D:\\Only for Selenium use\\IE webdriver server\\IEDriverServer.exe");
			driver = new InternetExplorerDriver();
			Log.info("IE Browser Intilatized");
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			Log.info("Impecit wait is applied for 20 seconds");
			driver.get(Constant.url);
			Log.info("Website launched successfully");
			break;
		default: Log.info("Does not find Browser"); 
		}
		}catch(Exception e){
			Log.error("Class Utils | Method openBrowser|Exception desc"+e.getMessage());
		}
		return driver;
	}

	public static void takeScreenShot(WebDriver driver, String sTestCaseName){
		try{
		File scrfile=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(scrfile, new File(Constant.Screenshot_path+sTestCaseName+".jpg"));
		Log.info("Screenshot taken successfully");
		}catch(Exception e){
			Log.error("Class Utility| method takeScreenshot | Exception desc"+e.getMessage());
		}
		
	}
}
