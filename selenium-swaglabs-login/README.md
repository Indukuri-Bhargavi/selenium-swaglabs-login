# 🔐 SwagLabs Login Automation

# Selenium Automation Framework 🚀

This is a hybrid automation framework built using **Selenium WebDriver**, **TestNG**, **Maven**, and **ExtentReports**, designed to support robust web UI testing.

---

## 📁 Project Structure

selenium-automation-framework/
├── src
│   ├── main
│   │   └── java
│   │       ├── helpers        # Utility classes (ConfigReader, ExcelUtil, ScreenshotUtil, ExtentManager, DriverFactory, etc.)
│   │       └── pages          # Page Object Model (POM) classes (LoginPage, etc.)
│   └── test
│       └── java
│           ├── base           # BaseTest with ThreadLocal WebDriver management
│           ├── listeners      # TestNG listeners (screenshot capture, reporting)
│           └── testcases      # TestNG test classes for positive & negative login flows
├── test-data                  # Test input files (Excel, etc.)
├── test-output                # Generated reports and logs
├── config.properties          # Project configuration (URL, browser)
├── pom.xml                    # Maven dependencies & build configuration
└── README.md                  # Project documentation

---

## ⚙️ Execution Flow

      TestNG Suite (testng.xml)
              │
              v
       BaseTest (setup/teardown)
              │
              v
     DriverFactory.java → WebDriver instance
              │
              v
       Page Classes (LoginPage)
              │
              v
     Test Cases (LoginTest, etc.)
              │
              v
       TestListener.java (failure handling, screenshots)
              │
              v
     ExtentManager.java → ExtentReport.html

---

## ✨ Features

✅ Selenium WebDriver with Java

✅ TestNG for test orchestration and assertions

✅ Parallel Execution using ThreadLocal<WebDriver>

✅ Data-Driven Testing with Apache POI (Excel)

✅ Cross-Browser Support with WebDriverManager

✅ ExtentReports with screenshots on failure

✅ Page Object Model (POM) for cleaner, reusable code

✅ Configurable via config.properties

---

## 🔧 Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports
- Apache POI
- WebDriverManager

---

## 📄 How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/Indukuri-Bhargavi/selenium-automation-framework.git
2. Configure config.properties:
	url=https://www.saucedemo.com/
	browser=chrome
3. Run the test suite via TestNG XML:
	mvn clean test
4. View reports:
	Open test-output/ExtentReport.html in browser for visual test report.



📊 Sample Test Scenarios
✅ Valid Login (UI)

❌ Invalid Login (error validation)

🔄 Cross-browser execution (Chrome, Firefox, Edge)

📸 Automatic screenshot capture on test failure

🛒 **E2E Flow:** Login → Add to Cart → Checkout → Order Confirmation 


📌 Author
Bhargavi Indukuri
Selenium Automation Engineer
GitHub: Indukuri-Bhargavi

📬 Contributions & Feedback
Feel free to fork, raise issues, or suggest improvements via GitHub.
For job-specific inquiries or demo requests, contact: bhargaviraju.indukuri@gmail.com