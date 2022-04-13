package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import CommonFunctions.AddUserPage;
import CommonFunctions.LoginPage;
import CommonFunctions.LogoutPage;
import Utilities.ExcelFileUtil;

public class POMDriverScript {
WebDriver driver;
String inputpath ="D:\\Automation_Selenium\\DDT_Framework\\TestInput\\UserData.xlsx";
String outputpath ="D:\\Automation_Selenium\\DDT_Framework\\TestOutPut\\PomResults.xlsx";
@BeforeTest
public void adminLogin()throws Throwable
{
	System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	LoginPage login = PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
}
@Test
public void validateuser()throws Throwable
{
	boolean res=false;
	//create object for excelfile util class
	ExcelFileUtil xl = new ExcelFileUtil(inputpath);
	int rc = xl.rowCount("AddUser");
	int cc = xl.cellCount("AddUser");
	Reporter.log("No of rows are::"+rc+"   "+"No of cells in first row::"+cc,true);
	for(int i=1;i<=rc;i++)
	{
		String userrole =xl.getCellData("AddUser", i, 0);
		String Ename =xl.getCellData("AddUser", i, 1);
		String username =xl.getCellData("AddUser", i, 2);
		String password =xl.getCellData("AddUser", i, 3);
		String cpassword =xl.getCellData("AddUser", i, 4);
		AddUserPage user = PageFactory.initElements(driver, AddUserPage.class);
		String Results =user.verifyAddUser(userrole, Ename, username, password, cpassword);
		xl.setCellData("AddUser", i, 5, Results, outputpath);
		
		
	}
}
@AfterTest
public void tearDown()throws Throwable
{
	LogoutPage logout = PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}










