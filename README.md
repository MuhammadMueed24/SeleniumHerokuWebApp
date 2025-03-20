# SeleniumHerokuWebApp

## Selenium Test Automation Framework

A robust test automation framework built with Selenium WebDriver, TestNG, and Java, implementing the Page Object Model design pattern for automated web testing.

## Features

- Page Object Model (POM) architecture for maintainable test code
- Cross-browser testing support (Chrome, Firefox) 
- Parallel test execution capability
- Comprehensive logging with Log4j2
- Screenshot capture on test failures
- Multiple file upload methods (SendKeys and Robot class)
- JavaScript alert/popup handling
- Web table data extraction and verification
- Login functionality testing with valid/invalid credentials

## Tech Stack

- Java
- Selenium WebDriver 4.29.0
- TestNG 7.11.0 
- WebDriverManager 5.9.3
- Log4j2 2.24.3
- Maven
- ExtentReports 5.1.2

## Project Structure


```SeleniumHerokuWebApp/ ├── src/ │ ├── test/ │ │ ├── java/ │ │ │ ├── page_objects/ # Page object classes │ │ │ ├── test_base/ # Base test configuration │ │ │ ├── test_cases/ # Test case implementations │ │ │ ├── util/ # Helper utilities │ │ ├── resources/ # Test resources and config files ├── screenshots/ # Test failure screenshots ├── pom.xml # Maven configuration ├── testng.xml # TestNG suite configuration ├── run_test.sh # Test execution script ├── config.properties # Test configuration properties ├── log4j2.xml # Logging configuration```


## Prerequisites

- Java JDK 8 or higher
- Maven 
- Chrome/Firefox browser

## Installation & Setup

1. Clone the repository:


bash
git clone <repository-url>
cd selenium_assignment


2. Install dependencies:

bash
mvn clean install

## Running Tests

### Using Maven:


bash
mvn test


### Using Shell Script:

bash
./run_test.sh


### Using TestNG XML:
Run the `testng.xml` file directly from your IDE.

## Test Cases

1. Login Tests (`loginTest.java`)
- Valid credential login verification
- Invalid credential error validation
- Screenshot capture on failure

2. File Upload Tests (`fileUpload.java`) 
- Upload using SendKeys method
- Upload using Robot class
- Upload verification

3. Alert Handling Tests (`alert_handling.java`)
- Simple JavaScript alerts
- Confirmation dialogs
- Prompt dialogs with input

4. Table Tests (`tableTest.java`)
- Extract company names
- Verify specific company existence
- Extract column data

## Configuration

- Browser selection and other test properties can be configured in `config.properties`
- Parallel execution settings in `testng.xml`
- Logging configuration in `log4j2.xml`

## Utilities

- `snapFunction.java`: Screenshot capture functionality
- `helpingClass.java`: Common WebDriver helper methods
- `baseClass.java`: Test initialization and cleanup



