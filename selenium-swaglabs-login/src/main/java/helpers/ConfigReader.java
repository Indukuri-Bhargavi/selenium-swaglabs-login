package helpers;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	static Properties prop;

	public static String get(String key) {
		if (prop == null) {
			prop = new Properties();
			try {
				FileInputStream fis = new FileInputStream("config.properties");
				prop.load(fis);
			} catch (IOException e) {
				System.out.println("❌ Unable to load config: " + e.getMessage());
			}
		}
		return prop.getProperty(key);
	}
}
