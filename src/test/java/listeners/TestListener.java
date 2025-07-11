package listeners;

import org.testng.*;
import com.aventstack.extentreports.*;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import base.BaseTest;
import org.openqa.selenium.WebDriver;

public class TestListener extends BaseTest implements ITestListener {
    ExtentReports extent = ExtentManager.getReporter();
    ExtentTest test;

  
    @Override
    public void onTestStart(ITestResult result) {
        test = extent.createTest(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.pass("✅ Test Passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = ((BaseTest) result.getInstance()).driver;
        String screenshotPath = ScreenshotUtil.captureScreenshot(driver, result.getName());
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);  // use absolute path
        }
        test.fail("❌ Test Failed: " + result.getThrowable());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }
}
