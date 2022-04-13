package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class AddUserPage {
WebDriver driver;
public AddUserPage(WebDriver driver)
{
	this.driver=driver;
}
//define repository 
@FindBy(xpath="//b[contains(text(),'Admin')]")
WebElement ObjAdmin;
@FindBy(xpath="//a[@id='menu_admin_UserManagement']")
WebElement Objusermgnt;
@FindBy(xpath="//a[@id='menu_admin_viewSystemUsers']")
WebElement Objusers;
@FindBy(xpath="//input[@id='btnAdd']")
WebElement Objaddbtn;
@FindBy(xpath="//select[@id='systemUser_userType']")
WebElement ObjuserRole;
@FindBy(xpath="//input[@id='systemUser_employeeName_empName']")
WebElement ObjEname;
@FindBy(xpath="//input[@id='systemUser_userName']")
WebElement Objusername;
@FindBy(xpath="//input[@id='systemUser_password']")
WebElement Objpassword;
@FindBy(xpath="//input[@id='systemUser_confirmPassword']")
WebElement Objcpassword;
@FindBy(xpath="//input[@id='btnSave']")
WebElement Objusavebtn;
public String verifyAddUser(String userRole,String ename,String username,String password,String cpassword)throws Throwable
{
	String res="";
	Actions ac = new Actions(driver);
	ac.moveToElement(this.ObjAdmin).perform();
	Thread.sleep(3000);
	ac.moveToElement(this.Objusermgnt).perform();
	Thread.sleep(3000);
	ac.moveToElement(this.Objusers).click().perform();
	Thread.sleep(3000);
	ac.moveToElement(this.Objaddbtn).click().perform();
	Thread.sleep(3000);
	new Select(this.ObjuserRole).selectByVisibleText(userRole);
	this.ObjEname.sendKeys(ename);
	this.Objusername.sendKeys(username);
	this.Objpassword.sendKeys(password);
	Thread.sleep(3000);
	this.Objcpassword.sendKeys(cpassword);
	Thread.sleep(3000);
	this.Objusavebtn.click();
	Thread.sleep(3000);
	String expected ="viewSystemUsers";
	String actual =driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		res="Pass";
	}
	else
	{
		res="Fail";
	}
	return res;
}

}









