package base;

import org.testng.annotations.*;
import helpers.ConfigReader;
import helpers.DriverFactory;

public class BaseTest {

	@BeforeMethod(alwaysRun = true)
	@Parameters({ "browser" })
	public void setUp(@Optional("") String browser) {
		DriverFactory.initDriver(browser);
		String url = ConfigReader.get("url");
		if (url != null && !url.isBlank()) {
			DriverFactory.getDriver().get(url);
		}
	}

	@AfterMethod(alwaysRun = true)
	public void tearDown() {
		DriverFactory.quitDriver();
	}
}
