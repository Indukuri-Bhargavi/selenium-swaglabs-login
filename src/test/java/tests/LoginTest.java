package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.LoginPage;
import utils.ExcelUtil;

@Listeners(listeners.TestListener.class)
public class LoginTest extends BaseTest {
	
	@DataProvider(name = "loginData")
	public Object[][] getData() {
	    return ExcelUtil.readLoginData("test-data/LoginData.xlsx", "Login");
	}

    @Test(dataProvider = "loginData")
    public void loginWithMultipleUsers(String username, String password) {
        LoginPage login = new LoginPage(driver);
        login.login(username, password);
        Assert.assertTrue(driver.getCurrentUrl().contains("inventory")); // basic check
    }
}
