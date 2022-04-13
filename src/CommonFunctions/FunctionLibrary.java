package CommonFunctions;

import org.openqa.selenium.By;

import Constant.AppUtil;

public class FunctionLibrary extends AppUtil {
	public static void verifyLogin(String username,String password)
	{
		driver.findElement(By.cssSelector(config.getProperty("Objuser"))).sendKeys(username);
		driver.findElement(By.cssSelector(config.getProperty("Objpass"))).sendKeys(password);
		driver.findElement(By.cssSelector(config.getProperty("Objloginbtn"))).click();
	}

}
