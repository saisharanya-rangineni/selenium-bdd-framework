# 🧪 Selenium BDD Automation Framework

[![Selenium BDD Tests](https://github.com/saisharanya-rangineni/selenium-bdd-framework/actions/workflows/selenium-tests.yml/badge.svg)](https://github.com/saisharanya-rangineni/selenium-bdd-framework/actions/workflows/selenium-tests.yml)
![Java](https://img.shields.io/badge/Java_17-ED8B00?logo=openjdk&logoColor=white)
![Selenium](https://img.shields.io/badge/Selenium-43B02A?logo=selenium&logoColor=white)
![Cucumber](https://img.shields.io/badge/Cucumber_BDD-23D96C?logo=cucumber&logoColor=white)
![Maven](https://img.shields.io/badge/Maven-C71A36?logo=apachemaven&logoColor=white)

A scalable BDD automation framework built with **Selenium WebDriver**, **Cucumber**, and **Java**, following Page Object Model patterns with Allure reporting and CI/CD integration via GitHub Actions.

> **Note:** This project is built for portfolio and demonstration purposes only. No proprietary or company-related information, code, or projects have been used. All tests run against the publicly available [SauceDemo](https://www.saucedemo.com/) application.

---

## 🏗️ Architecture

```
selenium-bdd-framework/
├── src/test/
│   ├── java/com/portfolio/
│   │   ├── pages/              # Page Object Models
│   │   │   ├── LoginPage.java
│   │   │   └── InventoryPage.java
│   │   ├── steps/              # Cucumber step definitions
│   │   │   ├── LoginSteps.java
│   │   │   └── Hooks.java      # Setup/teardown with screenshot-on-failure
│   │   ├── runners/            # Test runners
│   │   │   └── TestRunner.java
│   │   └── utils/              # Utilities
│   │       └── DriverFactory.java  # Thread-safe WebDriver management
│   └── resources/
│       └── features/           # Gherkin feature files
│           ├── login.feature   # Login scenarios (positive, negative, data-driven)
│           └── cart.feature    # Shopping cart & checkout scenarios
├── .github/workflows/          # CI/CD pipeline
│   └── selenium-tests.yml
├── pom.xml                     # Maven configuration & dependencies
└── README.md
```

## ✨ Key Features

| Feature | Description |
|---|---|
| **Cucumber BDD** | Human-readable Gherkin scenarios that bridge dev and business |
| **Page Object Model** | Clean separation of locators, actions, and test logic |
| **Data-Driven Testing** | Scenario Outline with Examples for parameterised tests |
| **Cross-Browser Support** | Chrome and Firefox via WebDriverManager |
| **Thread-Safe Drivers** | ThreadLocal WebDriver for parallel execution |
| **Screenshot on Failure** | Automatic screenshot capture attached to Cucumber reports |
| **Allure Reporting** | Rich, interactive test reports with history and trends |
| **CI/CD Pipeline** | GitHub Actions with artifact uploads for reports |
| **Tag-Based Execution** | Run by `@smoke`, `@regression`, `@positive`, `@negative` |

## 🚀 Getting Started

### Prerequisites
- Java 17+
- Maven 3.8+
- Chrome or Firefox browser

### Installation

```bash
# Clone the repository
git clone https://github.com/saisharanya-rangineni/selenium-bdd-framework.git
cd selenium-bdd-framework

# Run all tests
mvn clean test

# Run only smoke tests
mvn clean test -Dcucumber.filter.tags="@smoke"

# Run regression suite
mvn clean test -Dcucumber.filter.tags="@regression"

# Run with Firefox
mvn clean test -Dbrowser=firefox

# Run in headed mode (visible browser)
mvn clean test -Dheadless=false
```

### Viewing Reports

```bash
# Cucumber HTML Report
open target/cucumber-reports/cucumber.html

# Allure Report (requires Allure CLI)
allure serve target/allure-results
```

## 🧪 Test Scenarios

### Login Feature (`login.feature`)
| Scenario | Type | Tags |
|---|---|---|
| Successful login with valid credentials | Positive | `@smoke` |
| Login fails with invalid password | Negative | `@negative` |
| Login fails with locked out user | Negative | `@negative` |
| Login fails with empty credentials | Negative | `@negative` |
| Login fails with empty password | Negative | `@negative` |
| Login with multiple user types | Data-driven | `@data-driven` |

### Shopping Cart Feature (`cart.feature`)
| Scenario | Type | Tags |
|---|---|---|
| Add a single item to cart | Positive | `@smoke` |
| Add multiple items to cart | Positive | `@cart` |
| Remove an item from cart | Positive | `@cart` |
| Continue shopping from cart | Navigation | `@cart` |
| Complete checkout process | E2E | `@smoke` |

## ⚙️ CI/CD Pipeline

The GitHub Actions workflow automatically:

1. Sets up Java 17 and Chrome browser
2. Runs the full test suite in headless mode
3. Uploads Cucumber HTML report as an artifact
4. Uploads Allure results for trend analysis
5. Captures and uploads failure screenshots

## 🛠️ Tech Stack

| Technology | Purpose |
|---|---|
| Selenium WebDriver 4 | Browser automation |
| Cucumber 7 | BDD framework with Gherkin syntax |
| Java 17 | Programming language |
| Maven | Build and dependency management |
| Allure | Interactive test reporting |
| WebDriverManager | Automatic browser driver management |
| JUnit 5 | Test execution platform |
| GitHub Actions | CI/CD pipeline |

## 📝 License

This project is licensed under the MIT License.

---

**Author:** [Sai Sharanya Rangineni](https://saisharanya-rangineni.github.io/) | [LinkedIn](https://www.linkedin.com/in/sai-sharanya-r-27b003257/)
