package DriverFactory;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import CommonFunctions.AddEmpPage;
import CommonFunctions.AddUserPage;
import CommonFunctions.LoginPage;
import CommonFunctions.LogoutPage;

public class TestScript {
WebDriver driver;
@BeforeMethod
public void adminLogin()throws Throwable
{
	System.setProperty("webdriver.chrome.driver", "./CommonDrivers/chromedriver.exe");
	driver = new ChromeDriver();
	driver.manage().window().maximize();
	driver.get("http://orangehrm.qedgetech.com/");
	LoginPage login =PageFactory.initElements(driver, LoginPage.class);
	login.verifyLogin("Admin", "Qedge123!@#");
	
}
@Test(priority=1)
public void validateuser()throws Throwable
{
	AddUserPage user =PageFactory.initElements(driver, AddUserPage.class);
	String results=user.verifyAddUser("Admin", "ANIKET JAIN", "Akhilesh98", "Testing@123#$%", "Testing@123#$%");
	Reporter.log(results,true);
}
@Test(priority=0)
public void validateAddEmp()throws Throwable
{
	AddEmpPage emp =PageFactory.initElements(driver, AddEmpPage.class);
	String results = emp.verifyAddEmp("Akhi", "Ranga", "Testing");
	Reporter.log(results,true);
}
@AfterMethod
public void tearDown()throws Throwable
{
	LogoutPage logout =PageFactory.initElements(driver, LogoutPage.class);
	logout.verifyLogout();
	driver.close();
}
}





