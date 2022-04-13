package DriverFactory;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Reporter;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import CommonFunctions.FunctionLibrary;
import Constant.AppUtil;
import Utilities.ExcelFileUtil;

public class DriverScript extends AppUtil {
	String inputpath ="D:\\Automation_Selenium\\DDT_Framework\\TestInput\\LoginData.xlsx";
	String outputpath="D:\\Automation_Selenium\\DDT_Framework\\TestOutPut\\DDTResults.xlsx";
	ExtentReports report;
	ExtentTest test;
	@Test
	public void validateLogin() throws Throwable
	{
		//define path to generate html reports
		report= new ExtentReports("./ExtentReports/DDT.html");
		//create object for accessing xl methods
		ExcelFileUtil xl = new ExcelFileUtil(inputpath);
		//count no of rows in login sheet
		int rc = xl.rowCount("Login");
		int cc = xl.cellCount("Login");
		Reporter.log("No of rows are::"+rc+"     "+"No of cells are::"+cc,true);
		for(int i=1;i<=rc;i++)
		{
			test=report.startTest("Login Test");
			driver.get(config.getProperty("Url"));
			driver.manage().window().maximize();
		String username = xl.getCellData("Login", i, 0);
		String password = xl.getCellData("Login", i, 1);
		//call login method
		FunctionLibrary.verifyLogin(username, password);
		String expected ="dashboard";
		String actual = driver.getCurrentUrl();
		if(actual.contains(expected))
		{
			//write as login success into results cell
			xl.setCellData("Login", i, 2, "Login success", outputpath);
			xl.setCellData("Login", i, 3, "Pass", outputpath);
			test.log(LogStatus.PASS, "Login success:::"+expected+"             "+actual);
			Reporter.log("Login success::"+expected+"             "+actual,true);
			
		}
		else
		{
			//take screen shot
			File screen =((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(screen, new File("./Screensshot/iteration/"+i+"Loginpage.png"));
			//capture error message
			String errormessage = driver.findElement(By.cssSelector(config.getProperty("Objerrormessage"))).getText();
			xl.setCellData("Login", i, 2, errormessage, outputpath);
			xl.setCellData("Login", i, 3, "Fail", outputpath);
			test.log(LogStatus.FAIL, errormessage+"   "+expected+"             "+actual);
			Reporter.log("Login Fail::"+expected+"             "+actual,true);
		}
			report.endTest(test);
			report.flush();
		}
	}

}
