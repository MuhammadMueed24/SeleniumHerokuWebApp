package test_base;

import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import io.github.bonigarcia.wdm.WebDriverManager;

public class baseClass {
	protected static WebDriver driver;
	public Logger logger;
	public Properties prop;
	String browser;

	@BeforeClass
	public void setup() throws IOException {

		logger = LogManager.getLogger(this.getClass());
		logger.info("Initializing test setup");

		try {

			String propPath = "./src/test/resources/config.properties";
			FileReader file = new FileReader(propPath);
			prop = new Properties();
			prop.load(file);
			logger.info("Properties file loaded successfully");

			browser = prop.getProperty("browser", "chrome");
			logger.info("Using browser: {}", browser);

			initializeDriver();

			if (driver != null) {
				driver.manage().window().maximize();

				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
				driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
				logger.info("WebDriver initialized successfully");
			} else {
				logger.error("Failed to initialize WebDriver");
				throw new RuntimeException("WebDriver initialization failed");
			}
		} catch (Exception e) {
			logger.error("Error in setup: ", e);
			throw e;
		}
	}

	private void initializeDriver() {
		try {
			if (browser.equalsIgnoreCase("chrome")) {
				WebDriverManager.chromedriver().setup();
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless"); // Enable headless mode for GitHub Actions
				options.addArguments("--disable-notifications");
				options.addArguments("--no-sandbox");
				options.addArguments("--disable-dev-shm-usage");
				options.addArguments("--remote-allow-origins=*"); // Fixes some remote session issues
				options.addArguments("--window-size=1920,1080"); // Ensures proper viewport for UI tests
				options.addArguments("--disable-gpu"); // Required for some environments
				options.addArguments("--ignore-certificate-errors"); // Handles SSL issues
				options.addArguments("--disable-popup-blocking"); // Prevents pop-ups from interrupting tests
				driver = new ChromeDriver(options);
				logger.info("Chrome WebDriver initialized");
			} else if (browser.equalsIgnoreCase("firefox")) {
				WebDriverManager.firefoxdriver().setup();
				FirefoxOptions options = new FirefoxOptions();
				options.addArguments("--disable-notifications");
				driver = new FirefoxDriver(options);
				logger.info("Firefox WebDriver initialized");
			} else {
				logger.error("Unsupported browser type: {}", browser);
				throw new RuntimeException("Unsupported browser type: " + browser);
			}
		} catch (Exception e) {
			logger.error("Error initializing WebDriver: ", e);
			throw e;
		}
	}

	@AfterClass
	public void tearDown() {
		logger.info("Tearing down test");
		if (driver != null) {
			driver.quit();
			logger.info("WebDriver closed successfully");
		}
	}
}