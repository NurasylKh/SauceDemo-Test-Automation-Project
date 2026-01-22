# SauceDemo Test Automation Project

## Project Overview
This project contains automated test cases for the SauceDemo e-commerce website (https://www.saucedemo.com) using Selenium WebDriver, TestNG, Log4j, and Extent Reports.

## Technologies Used
- **Java 11**: Programming language
- **Selenium WebDriver 4.16.1**: Web automation framework
- **TestNG 7.8.0**: Testing framework for test lifecycle management
- **Log4j 2.21.1**: Logging framework
- **Extent Reports 5.1.1**: HTML reporting framework
- **Maven**: Build and dependency management tool

## Project Structure
```
saucedemo-automation/
├── src/
│   └── test/
│       ├── java/
│       │   └── com/
│       │       └── saucedemo/
│       │           ├── base/
│       │           │   └── BaseTest.java          # Base test class with setup/teardown
│       │           ├── pages/
│       │           │   ├── LoginPage.java         # Login page object
│       │           │   ├── ProductsPage.java      # Products page object
│       │           │   └── CartPage.java          # Cart page object
│       │           └── tests/
│       │               ├── LoginTests.java        # Login test cases
│       │               └── ShoppingCartTests.java # Shopping cart test cases
│       └── resources/
│           └── log4j2.xml                         # Log4j configuration
├── test-output/
│   ├── logs/                                      # Log files
│   ├── screenshots/                               # Screenshots on failure
│   └── ExtentReport.html                          # HTML test report
├── pom.xml                                        # Maven configuration
├── testng.xml                                     # TestNG suite configuration
└── README.md                                      # This file
```

## Prerequisites
Before running the tests, ensure you have the following installed:
1. **Java Development Kit (JDK) 11 or higher**
   - Download from: https://www.oracle.com/java/technologies/downloads/
   - Verify installation: `java -version`

2. **Apache Maven 3.6 or higher**
   - Download from: https://maven.apache.org/download.cgi
   - Verify installation: `mvn -version`

3. **Google Chrome Browser**
   - Download from: https://www.google.com/chrome/

4. **ChromeDriver** (automatically managed by Selenium Manager)

## Setup Instructions

### 1. Clone or Download the Project
```bash
# If using Git
git clone <repository-url>
cd saucedemo-automation

# Or extract the ZIP file and navigate to the project directory
```

### 2. Install Dependencies
```bash
mvn clean install
```

This command will:
- Download all required dependencies
- Compile the source code
- Run the tests
- Generate reports

## Running the Tests

### Option 1: Run all tests using Maven
```bash
mvn clean test
```

### Option 2: Run specific test class
```bash
mvn test -Dtest=LoginTests
mvn test -Dtest=ShoppingCartTests
```

### Option 3: Run using TestNG XML file
```bash
mvn test -DsuiteXmlFile=testng.xml
```

### Option 4: Run from IDE
1. Import the project as a Maven project in your IDE (IntelliJ IDEA, Eclipse, etc.)
2. Right-click on `testng.xml` and select "Run"
3. Or right-click on individual test classes and run them

## Test Cases

### Login Tests (LoginTests.java)
1. **testValidLogin**: Verify successful login with valid credentials
   - Username: `standard_user`
   - Password: `secret_sauce`
   - Expected: Redirected to Products page

2. **testInvalidLogin**: Verify login fails with invalid credentials
   - Username: `invalid_user`
   - Password: `wrong_password`
   - Expected: Error message displayed

3. **testLockedOutUser**: Verify login fails with locked out user
   - Username: `locked_out_user`
   - Password: `secret_sauce`
   - Expected: Locked out error message displayed

### Shopping Cart Tests (ShoppingCartTests.java)
1. **testAddProductsToCart**: Verify adding products to shopping cart
   - Add Sauce Labs Backpack
   - Add Sauce Labs Bike Light
   - Expected: Cart badge shows "2"

2. **testViewCartItems**: Verify cart page displays added products
   - Add 2 products to cart
   - Navigate to cart
   - Expected: Cart displays 2 items

3. **testProductSorting**: Verify product sorting functionality
   - Verify 6 products displayed
   - Sort by Price (low to high)
   - Expected: Same number of products after sorting

## Test Reports

### 1. Extent HTML Report
- **Location**: `test-output/ExtentReport.html`
- **Features**:
  - Test execution summary (Pass/Fail/Skip)
  - Individual test case results
  - Expected vs Actual results
  - Logs for each test step
  - Screenshots on test failure
  - System information

### 2. Log Files
- **Location**: `test-output/logs/automation.log`
- **Contains**:
  - Test start and end timestamps
  - Main test steps
  - Errors and failures
  - Debug information

### 3. Screenshots
- **Location**: `test-output/screenshots/`
- **Naming**: `TestName_YYYYMMDD_HHMMSS.png`
- **Captured**: Automatically on test failure

### 4. TestNG Report
- **Location**: `test-output/index.html`
- **Features**: Default TestNG HTML report

## Viewing Test Results

### After test execution:
1. **Open Extent Report**:
   - Navigate to `test-output/ExtentReport.html`
   - Open in any web browser
   - View detailed test results with logs and screenshots

2. **Check Logs**:
   - Navigate to `test-output/logs/automation.log`
   - Open in any text editor
   - Review detailed execution logs

3. **View Screenshots** (if tests failed):
   - Navigate to `test-output/screenshots/`
   - View PNG images of failed test states

## Framework Features

### 1. Test Lifecycle Management (30 points)
- ✅ **@BeforeSuite**: Initialize Extent Reports and create directories
- ✅ **@BeforeMethod**: Setup WebDriver, navigate to application
- ✅ **@AfterMethod**: Capture screenshots on failure, close browser
- ✅ **@AfterSuite**: Generate final reports
- ✅ **Clear separation**: Setup, test logic, and cleanup are well-separated

### 2. Logging Framework (30 points)
- ✅ **Log4j 2**: Configured with XML configuration file
- ✅ **Log test start and end**: Each test logs its beginning and completion
- ✅ **Log main test steps**: Every important step is logged
- ✅ **Log errors and failures**: Errors are logged with stack traces
- ✅ **File logging**: All logs written to `test-output/logs/automation.log`

### 3. Test Reports and Screenshots (40 points)
- ✅ **HTML Report**: Extent Reports with comprehensive test details
- ✅ **Test Summary**: Pass/Fail/Skip counts clearly displayed
- ✅ **Individual Results**: Each test case shows detailed results
- ✅ **Expected vs Actual**: Results are clearly defined
- ✅ **Logs in Report**: All logs integrated into HTML report
- ✅ **Screenshots on Failure**: Automatic screenshot capture
- ✅ **Clear Descriptions**: Failure descriptions are detailed

## Page Object Model (POM)
The project follows the Page Object Model design pattern:
- **LoginPage.java**: Handles login page interactions
- **ProductsPage.java**: Handles products page interactions
- **CartPage.java**: Handles shopping cart interactions

## Common Issues and Solutions

### Issue 1: ChromeDriver not found
**Solution**: Selenium 4+ includes Selenium Manager which automatically downloads the correct ChromeDriver version. Ensure you have internet connectivity during first run.

### Issue 2: Tests failing due to slow network
**Solution**: Increase implicit wait timeout in `BaseTest.java`:
```java
driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
```

### Issue 3: Maven build fails
**Solution**: 
```bash
mvn clean install -U
```
The `-U` flag forces Maven to update dependencies.

### Issue 4: Headless mode issues
**Solution**: Remove headless option in `BaseTest.java` to run with visible browser:
```java
// Comment out this line:
// options.addArguments("--headless");
```

## Configuration

### Change Browser
Currently configured for Chrome. To use Firefox:
1. Update `BaseTest.java`:
```java
// Replace ChromeDriver with FirefoxDriver
WebDriver driver = new FirefoxDriver();
```
2. Add Firefox dependency to `pom.xml`

### Parallel Execution
To run tests in parallel, update `testng.xml`:
```xml
<suite name="SauceDemo Test Suite" parallel="tests" thread-count="2">
```

## Best Practices Implemented
1. ✅ Page Object Model for maintainability
2. ✅ Explicit waits for stable tests
3. ✅ Comprehensive logging
4. ✅ Screenshot on failure
5. ✅ Clear test assertions
6. ✅ Proper exception handling
7. ✅ Clean code structure
8. ✅ Detailed documentation

## Contact & Support
For issues or questions:
- Create an issue in the repository
- Contact the automation team

## License
This project is created for educational purposes as part of Assignment 5.

---
**Note**: This automation framework meets all the requirements specified in Assignment 5:
- ✅ Test lifecycle management with TestNG
- ✅ Setup and teardown implementation
- ✅ Minimum 3 test cases (6 implemented)
- ✅ Log4j logging framework
- ✅ Logs written to file
- ✅ HTML test report (Extent Reports)
- ✅ Screenshots on failure
- ✅ Complete documentation
