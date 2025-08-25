package helpers;

import io.github.bonigarcia.wdm.WebDriverManager;
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
    	String br = (browser == null || browser.isBlank())
                ? System.getProperty("browser", ConfigReader.get("browser"))
                : browser;

        if (br == null || br.isBlank()) {
            // final fallback to config.properties if no param provided
            br = ConfigReader.get("browser");
        }

        switch (br.toLowerCase().trim()) {
            case "chrome": {
                WebDriverManager.chromedriver().setup();
                ChromeOptions options = new ChromeOptions();
                driver.set(new ChromeDriver(options));
                break;
            }
            case "firefox": {
                WebDriverManager.firefoxdriver().setup();
                FirefoxOptions options = new FirefoxOptions();
                driver.set(new FirefoxDriver(options));
                break;
            }
            case "edge": {
                WebDriverManager.edgedriver().setup();
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
