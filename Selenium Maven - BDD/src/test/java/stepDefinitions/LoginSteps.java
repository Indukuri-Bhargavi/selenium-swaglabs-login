package stepDefinitions;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utils.DriverFactory;

public class LoginSteps {

    WebDriver driver = DriverFactory.getDriver();

    @Given("User is on the login page")
    public void user_is_on_login_page() {
        driver.get("https://www.saucedemo.com/");
    }

    @When("User enters username {string} and password {string}")
    public void user_enters_credentials(String username, String password) {
        driver.findElement(By.id("user-name")).sendKeys(username);
        driver.findElement(By.id("password")).sendKeys(password);
    }

    @When("clicks on the login button")
    public void clicks_login_button() {
        driver.findElement(By.id("login-button")).click();
    }

    @Then("User should be redirected to the home page")
    public void redirected_to_homepage() {
        boolean displayed = driver.findElement(By.className("title")).isDisplayed();
        assert displayed;
        driver.quit();
    }
}
