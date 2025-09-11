package base;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import helpers.ConfigReader;
import helpers.DriverFactory;

public class BaseTest {
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

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
