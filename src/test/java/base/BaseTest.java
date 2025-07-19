package base;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import helpers.ConfigReader;
import helpers.DriverFactory;


public class BaseTest {
	  private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
	    public static ExtentReports extent;
	    public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	    public WebDriver getDriver() {
	        return driver.get();
	    }

	    @BeforeSuite
	    public void setupExtent() {
	    	 ExtentSparkReporter spark = new ExtentSparkReporter("test-output/ExtentReport.html");
	         extent = new ExtentReports();
	         extent.attachReporter(spark);
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
