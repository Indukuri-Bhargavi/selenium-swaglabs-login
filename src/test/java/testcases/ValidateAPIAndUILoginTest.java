package testcases;

import base.BaseTest;
import helpers.ConfigReader;
import helpers.DriverFactory;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateAPIAndUILoginTest extends BaseTest {

    @Test
    public void validateApiResponseAndUILoad() {
        createTest("Combined API + UI Test");

        // 🔹 Step 1: API Call
        RestAssured.baseURI = "https://reqres.in/api";
        String apiKey = ConfigReader.get("api_key");

        Response response = RestAssured
            .given()
            .header("x-api-key", apiKey)
            .when()
            .get("/users/2")
            .then()
            .extract().response();

        String email = response.jsonPath().getString("data.email");
        test.get().info("✅ Email from API: " + email);

        // 🔹 Step 2: UI Verification
        DriverFactory.getDriver().get("https://www.saucedemo.com/");
        String title = DriverFactory.getDriver().getTitle();
        test.get().info("✅ Page Title: " + title);
        Assert.assertEquals(title, "Swag Labs");

        // Optionally check element visibility
        boolean isLoginBoxPresent = DriverFactory.getDriver().findElement(By.id("login-button")).isDisplayed();
        Assert.assertTrue(isLoginBoxPresent, "Login button not found on UI");

        test.get().pass("🎉 API + UI Test Passed");
    }
}
