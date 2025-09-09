package testcases;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import helpers.DriverFactory;
import helpers.ExcelUtil;
import pages.LoginPage;

public class ValidLoginTest extends BaseTest {
	
	@DataProvider(name = "loginData",parallel = true)
	public Object[][] getData() {
	    return ExcelUtil.readUserData("test-data/LoginData.xlsx", "Login");
	}

    @Test(dataProvider = "loginData")
    public void loginWithMultipleUsers(String username, String password) {
        LoginPage login = new LoginPage(DriverFactory.getDriver());

        login.login(username, password);
        try {
			Thread.sleep(4000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("🧪 Running test for: " + username + " on Thread: " + Thread.currentThread().getId());
        Assert.assertTrue(DriverFactory.getDriver().getCurrentUrl().contains("inventory")); // basic check
    }
}
