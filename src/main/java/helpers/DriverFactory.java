package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;

import java.util.HashMap;
import java.util.Map;

public class DriverFactory {

	private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

	public static WebDriver initDriver(String browser) {
		String br = (browser == null || browser.isBlank()) ? System.getProperty("browser", ConfigReader.get("browser"))
				: browser;

		String os = System.getProperty("os.name").toLowerCase();

		switch (br.toLowerCase().trim()) {
		case "chrome": {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			Map<String, Object> prefs = new HashMap<>();
			prefs.put("credentials_enable_service", false);
			prefs.put("profile.password_manager_enabled", false);
			options.setExperimentalOption("prefs", prefs); // Optional: disable other popups
			options.addArguments("--disable-popup-blocking"); // Set consistent window size (helps in headless or CI)
			options.addArguments("--window-size=1920,1080");
			if (os.contains("linux")) {
				options.addArguments("--headless=new"); // run headless
				options.addArguments("--disable-dev-shm-usage"); // avoid crashes in Docker/CI
				options.addArguments("--no-sandbox"); // required for some CI runners }
				driver.set(new ChromeDriver(options));
			}
			break;
		}
		case "firefox": {
			WebDriverManager.firefoxdriver().setup();
			FirefoxOptions options = new FirefoxOptions();
			if (os.contains("linux")) {
				options.addArguments("--headless");
			}
			driver.set(new FirefoxDriver(options));
			break;
		}
		case "edge": {
			WebDriverManager.edgedriver().setup();
			EdgeOptions options = new EdgeOptions();
			if (os.contains("linux")) {
				options.addArguments("--headless");
			}
			driver.set(new EdgeDriver(options));
			break;
		}
		default:
			throw new IllegalArgumentException(
					"Unsupported browser: " + br + ". Valid options are: chrome, firefox, edge.");
		}

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
