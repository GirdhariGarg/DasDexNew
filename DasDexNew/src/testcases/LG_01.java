package testcases;

import org.apache.log4j.xml.DOMConfigurator;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import appModule.Login_Action;
import pageObject.BaseClass;
import utiliy.Constant;
import utiliy.ExcelUtils;
import utiliy.Log;
import utiliy.Utils;

public class LG_01 {
	public WebDriver driver;
	String sTestCaseName;
	int iTestCaseRow;
	 
	@BeforeMethod
	public void beforeMethod(){
		try{
		// configure 
		DOMConfigurator.configure("log4j.xml");
		
		// get test case name by class name
		sTestCaseName = this.toString();
		sTestCaseName  = Utils.getTestCaseName(sTestCaseName);
		System.out.println(sTestCaseName);
		Log.startTestCase(sTestCaseName);
		//getting sheet name 
		String sheetname = Utils.getSheetName(sTestCaseName);
		System.out.println(sheetname);
		//set test data file path 
		ExcelUtils.setFilePath(Constant.TestData_path, sheetname);
		
		//getting row which contains test case in excel test data
		iTestCaseRow =ExcelUtils.getRowContains(sTestCaseName, Constant.Col_Test_Case_ID);
		
		driver= Utils.openBrowser();
		
		new BaseClass(driver);
		}catch(Exception e){
			Log.error(sTestCaseName +" | Method beforeMethod| Exception desc"+e.getMessage());
		}
		
	}
	
	@Test
	public void main(){
		try{
		//Login_Action.Execute();
			String valuee = driver.switchTo().parentFrame().getWindowHandle();
			System.out.println(valuee+"this is parent window handle");
			String defaultframe = driver.switchTo().defaultContent().getWindowHandle();
			System.out.println(defaultframe+"this is default window handle");
			
			String integervalue = driver.switchTo().frame(0).getWindowHandle();
			System.out.println(integervalue+"this is frame window handle in in int value");
			String stringvalue = driver.switchTo().frame("second").getWindowHandle();
			System.out.println(stringvalue+"this is frame window handle in string value");
			
			WebElement we = driver.findElement(By.partialLinkText("Warning"));
			String webelementvalue = driver.switchTo().frame(we).getWindowHandle();
			System.out.println(webelementvalue+"this is frame window handle by webelement");
			
		if (BaseClass.bResult == true){
			Utils.takeScreenShot(driver, sTestCaseName);
			ExcelUtils.setCellData("Pass", iTestCaseRow, Constant.Col_Result);
			Log.info(sTestCaseName+" Passed successfully");
			Reporter.log(sTestCaseName+" passed in verication");
		}else{
			Utils.takeScreenShot(driver, sTestCaseName);
			ExcelUtils.setCellData("Fail", iTestCaseRow, Constant.Col_Result);
			Log.error(sTestCaseName+" Failed");
			Reporter.log(sTestCaseName+" failed in verification");
		}
		}catch(Exception e){
			Log.info(sTestCaseName + "| Method main| Exception desc"+e.getMessage());
		}
	}
	
	@AfterTest
	public void afterTest(){
		driver.quit();
		Log.info("Browser closed successfully");
		Log.endTestCase();
	}

}
