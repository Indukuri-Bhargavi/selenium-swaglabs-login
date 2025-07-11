package utils;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class ScreenshotUtil {

    public static String captureScreenshot(WebDriver driver, String testName) {
    	
    	 String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
    	    String screenshotDir = System.getProperty("user.dir") + "/test-output/screenshots/";
    	    String filePath = screenshotDir + testName + "_" + timestamp + ".png";

    	    try {
    	        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    	        File dest = new File(filePath);
    	        dest.getParentFile().mkdirs(); // ensure directory exists
    	        FileHandler.copy(src, dest);
    	        return filePath; // return absolute path for Extent
    	    } catch (IOException e) {
    	        System.out.println("❌ Screenshot failed: " + e.getMessage());
    	        return null;
    	    }
    }
}
