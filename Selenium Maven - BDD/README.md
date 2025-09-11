# 🥒 Selenium BDD Framework (Cucumber + TestNG)

## 📖 Overview

This is a **BDD automation framework** using **Selenium WebDriver**, **Cucumber**, **TestNG**, and **Maven**.  
It supports writing test cases in **Gherkin syntax** for better collaboration.

---

## 📂 Project Structure

```
Selenium Maven - BDD/
├── src/main/java/pages           # Page Object Model (POM) classes
├── src/test/java/stepdefinitions # Step Definitions (glue code)
├── src/test/java/runners         # Cucumber Test Runners (TestNG)
├── src/test/resources/features   # Gherkin Feature Files
├── test-output                   # Reports (Extent, Cucumber)
├── config.properties             # Config (URL, browser)
├── pom.xml                       # Maven dependencies
└── README.md                     # Documentation
```

---

## ⚙️ Execution Flow

1. Gherkin Feature File (`.feature`)  
2. Step Definitions → Selenium actions  
3. Runner Class (Cucumber + TestNG)  
4. Reports generated in `test-output/`

---

## ✨ Features

✅ BDD with Cucumber & Gherkin syntax  
✅ Selenium WebDriver with Page Object Model  
✅ ExtentReports + Cucumber Reports  
✅ Parallel execution with TestNG  
✅ Configurable via `config.properties`  

---

## 📄 How to Run

```bash
mvn clean test
```

Reports:  
- `test-output/ExtentReport.html`  
- `cucumber-reports/`

---

## 📌 Author

Bhargavi Indukuri  
📧 bhargaviraju.indukuri@gmail.com
