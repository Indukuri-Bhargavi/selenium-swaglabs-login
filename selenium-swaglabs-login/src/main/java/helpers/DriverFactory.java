package helpers;

import java.util.HashMap;
import java.util.Map;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

public class DriverFactory {
	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver initDriver(String browser) {
		String br = (browser == null || browser.isBlank()) ? System.getProperty("browser", ConfigReader.get("browser"))
				: browser;
		if (br == null || br.isBlank()) { // final fallback to config.properties if no param provided
			br = ConfigReader.get("browser");
		}

		switch (br.toLowerCase().trim()) {

		case "chrome": {
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
			driver.set(new ChromeDriver(options));
			break;
		}
		case "firefox": {
			FirefoxOptions options = new FirefoxOptions();
			driver.set(new FirefoxDriver(options));
			break;
		}
		case "edge": {
			EdgeOptions options = new EdgeOptions();
			driver.set(new EdgeDriver(options));
			break;
		}
		default:
			throw new IllegalArgumentException("Unsupported browser: " + br);
		}
		getDriver().manage().window().maximize();
		return getDriver();
	}

	public static WebDriver getDriver() {
		return driver.get();
	}

	public static void quitDriver() {
		WebDriver d = driver.get();
		if (d != null) {
			d.quit();
			driver.remove();
		}
	}
}
