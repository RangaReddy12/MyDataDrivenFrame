package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LogoutPage {
@FindBy(xpath="//a[@id='welcome']")
WebElement ObjWelcome;
@FindBy(xpath="//a[contains(text(),'Logout')]")
WebElement ObjLogout;
public void verifyLogout()throws Throwable
{
	ObjWelcome.click();
	Thread.sleep(2000);
	ObjLogout.click();
}

}
