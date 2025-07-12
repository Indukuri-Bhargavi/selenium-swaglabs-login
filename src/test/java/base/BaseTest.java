package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import helpers.ConfigReader;
import helpers.DriverFactory;


public class BaseTest {
	  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	    public WebDriver getDriver() {
	        return driver.get();
	    }

	    @BeforeMethod
	    public void setUp() {
	        String browser = ConfigReader.get("browser");
	        driver.set(DriverFactory.createDriver(browser)); // ✅ This must be set
	        getDriver().manage().window().maximize();
	        getDriver().get(ConfigReader.get("url"));
	    }

	    @AfterMethod
	    public void tearDown() {
	        getDriver().quit();
	        driver.remove();
	    }}
