package CommonFunctions;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
//define repository
	@FindBy(name ="txtUsername")
	WebElement user;
	@FindBy(name ="txtPassword")
	WebElement pass;
	@FindBy(name ="Submit")
	WebElement Loginbtn;
	//method for login
	public void verifyLogin(String username,String password)throws Throwable
	{
		user.sendKeys(username);
		pass.sendKeys(password);
		Loginbtn.click();
		Thread.sleep(3000);
	}
	
}
