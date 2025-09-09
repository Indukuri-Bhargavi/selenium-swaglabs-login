package base;

import org.testng.annotations.*;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import helpers.ConfigReader;
import helpers.DriverFactory;

public class BaseTest {
	public static ExtentReports extent;
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@BeforeSuite
	public void setupExtent() {
		ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
		extent = new ExtentReports();
		extent.attachReporter(spark);
	}

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

	public static ExtentTest getTest() {
		return test.get(); // 👈 This method enables BaseTest.getTest()
	}

	public static void createTest(String testName) {
		if (extent == null) {
			ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
			extent = new ExtentReports();
			extent.attachReporter(spark);
		}
		ExtentTest extentTest = extent.createTest(testName);
		test.set(extentTest);
	}

	@AfterSuite
	public void tearDownExtent() {
		extent.flush();
	}

}
