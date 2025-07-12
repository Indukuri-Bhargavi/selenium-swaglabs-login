package testcases;

import base.BaseTest;
import helpers.ExcelUtil;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;

public class InvalidLoginTest extends BaseTest {

    @DataProvider(name = "negativeLoginData")
    public Object[][] getNegativeLoginData() {
        return ExcelUtil.readNegativeLoginData("test-data/LoginData.xlsx", "NegativeLogin");
    }

    @Test(dataProvider = "negativeLoginData")
    public void loginWithInvalidCredentials(String username, String password, String expectedError) {
        LoginPage login = new LoginPage(getDriver());
        login.login(username, password);

        String actualError = login.getErrorMessage();
        System.out.println("❌ Actual Error: " + actualError);

        Assert.assertEquals(actualError.trim(), expectedError.trim(), "Error message mismatch");
    }
}
