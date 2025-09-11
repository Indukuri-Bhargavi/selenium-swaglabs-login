package utils;

import io.github.bonigarcia.wdm.WebDriverManager;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class DriverFactory {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();


    public static WebDriver getDriver() {
        if (driver.get() == null) {
        	WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			 Map<String, Object> prefs = new HashMap<>();
			    prefs.put("credentials_enable_service", false);
			    prefs.put("profile.password_manager_enabled", false);
			    prefs.put("autofill.profile_enabled", false);
			    prefs.put("autofill.credit_card_enabled", false);
			    prefs.put("profile.default_content_setting_values.notifications", 2);

			    options.setExperimentalOption("prefs", prefs);

			    // Disable common Chrome popups / first run nags
			    options.addArguments("--disable-popup-blocking");
			    options.addArguments("--incognito");
			    options.addArguments("--no-first-run");
			    options.addArguments("--no-default-browser-check");

			    // Ensure a fresh Chrome profile each run (avoids saving state)
			    options.addArguments("--user-data-dir=/tmp/chrome-data-" + System.currentTimeMillis());

			    // Consistent screen size
			    options.addArguments("--window-size=1920,1080");

			    // Extra stability in Linux/CI environments
			    String os = System.getProperty("os.name").toLowerCase();
			    if (os.contains("linux")) {
			        options.addArguments("--headless=new");
			        options.addArguments("--disable-dev-shm-usage");
			        options.addArguments("--no-sandbox");
			    }

			driver.set(new ChromeDriver(options));
        }
     //   getDriver().manage().window().maximize();
		return driver.get();
	}
    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
