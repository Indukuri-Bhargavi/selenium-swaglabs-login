package base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;
import utils.ConfigReader;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;

	@BeforeMethod
	public void setUp() {
	    WebDriverManager.chromedriver().setup();

	    driver = new ChromeDriver();
	    driver.get(ConfigReader.get("url"));
	    driver.manage().window().maximize();
	}

}
