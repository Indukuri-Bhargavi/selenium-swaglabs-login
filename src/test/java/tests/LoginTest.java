package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import pages.LoginPage;
import utils.BaseTest;

public class LoginTest extends BaseTest {
    @Test
    public void validLoginTest()  {
        LoginPage login = new LoginPage(driver);
        login.login("standard_user", "secret_sauce");
        Assert.assertEquals(driver.getTitle(), "Swag Labs");
    }
}
