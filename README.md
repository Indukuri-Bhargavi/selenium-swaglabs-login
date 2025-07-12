# 🔐 SwagLabs Login Automation

# Selenium Automation Framework 🚀

This is a hybrid automation framework built using **Selenium WebDriver**, **TestNG**, **Maven**, and **ExtentReports**, designed to support robust web UI testing and REST API validation.

---

## 📁 Project Structure

selenium-automation-framework/
├── src
│ ├── main
│ │ └── java
│ │ ├── helpers # Utility classes (ConfigReader, ExcelUtil, ScreenshotUtil, etc.)
│ │ └── pages # Page Object Model (POM) classes
│ └── test
│ └── java
│ ├── api # REST Assured-based API tests
│ ├── base # BaseTest with ThreadLocal WebDriver management
│ └── testcases # TestNG test classes for positive and negative flows
---


---

## ✨ Features

- ✅ **Selenium WebDriver with Java**
- ✅ **TestNG** for assertions and test orchestration
- ✅ **Parallel Execution** using `ThreadLocal<WebDriver>`
- ✅ **Data-Driven Testing** using Apache POI (Excel)
- ✅ **Cross-Browser Support** via WebDriverManager
- ✅ **ExtentReports** with screenshots on failure
- ✅ **Page Object Model (POM)** for clean separation of test logic
- ✅ **API Testing** using **Rest Assured**
- ✅ Configurable via `config.properties`

---

## 🔧 Technologies Used

- Java
- Selenium WebDriver
- TestNG
- Maven
- ExtentReports
- Apache POI
- Rest Assured
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

📡 REST API validation using ReqRes (GET / POST)

📸 Automatic screenshot capture on test failure


📌 Author
Bhargavi Indukuri
Selenium Automation Engineer
GitHub: Indukuri-Bhargavi

📬 Contributions & Feedback
Feel free to fork, raise issues, or suggest improvements via GitHub.
For job-specific inquiries or demo requests, contact: bhargaviraju.indukuri@gmail.com