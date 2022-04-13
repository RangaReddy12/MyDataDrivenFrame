package CommonFunctions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddEmpPage {
WebDriver driver;
public AddEmpPage(WebDriver driver)
{
	this.driver=driver;
}
@FindBy(xpath="//b[contains(text(),'PIM')]")
WebElement ObjPim;
@FindBy(xpath="//input[@id='btnAdd']")
WebElement Objaddbtn;
@FindBy(xpath="//input[@id='firstName']")
WebElement Objfname;
@FindBy(xpath="//input[@id='middleName']")
WebElement Objmame;
@FindBy(xpath="//input[@id='lastName']")
WebElement Objlname;
@FindBy(xpath="//input[@id='btnSave']")
WebElement Objsavebtn;
public String verifyAddEmp(String fname,String mname,String lname)throws Throwable
{
	String res="";
	Actions ac = new Actions(driver);
	ac.moveToElement(this.ObjPim).click().perform();
	Thread.sleep(2000);
	ac.moveToElement(this.Objaddbtn).click().perform();
	Thread.sleep(2000);
	this.Objfname.sendKeys(fname);
	this.Objmame.sendKeys(mname);
	this.Objlname.sendKeys(lname);
	this.Objsavebtn.click();
	Thread.sleep(2000);
	String expected ="empNumber";
	String actual = driver.getCurrentUrl();
	if(actual.contains(expected))
	{
		res ="Pass";
	}
	else
	{
		res ="Fail";
	}
	return res;
}

}
