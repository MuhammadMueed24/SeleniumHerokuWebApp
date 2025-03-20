package test_cases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import page_objects.loginPage;
import test_base.baseClass;
import util.snapFunction;

public class loginTest extends baseClass {
    loginPage lp;
    snapFunction snap;

    @BeforeClass
    public void setupTest() {
        logger.info("Starting login test setup");
        try {
            lp = new loginPage(driver);
            snap = new snapFunction(); 
            logger.info("Login page object and snap function initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize login page object or snap function", e);
            throw e;
        }
    }

    @Test
    public void login_with_valid_cred() {
        logger.info("Starting test: login with valid credentials");
        try {
            logger.debug("Navigating to login page");
            driver.get(prop.getProperty("loginUrl"));
            logger.info("Successfully navigated to login page");
            
            String username = "tomsmith";
            String password = "SuperSecretPassword!";
            
            logger.debug("Attempting login with username: {}", username);
            lp.login(username, password);
            logger.info("Login credentials submitted");
            
            logger.debug("Verifying successful login");
            lp.verifyLogin();
            logger.info("Valid login test completed successfully");
        } catch (Exception e) {
            logger.error("Error occurred during valid login test", e);
            throw e;
        }
    }

    @Test
    public void login_with_invalid_cred() throws Exception {
        logger.info("Starting test: login with invalid credentials");
        try {
            logger.debug("Navigating to login page");
            driver.get(prop.getProperty("loginUrl"));
            logger.info("Successfully navigated to login page");
            
            logger.debug("Attempting login with invalid credentials");
            lp.login("wrongname", "wrongpassword");
            logger.info("Invalid login credentials submitted");
            
            logger.debug("Verifying error message for invalid login");
            lp.verifyInvalidLogin();
            logger.info("Invalid login test completed successfully");
            
            // Take screenshot for passed test case
            String passedScreenshotPath = snap.getPassed_Screenshot("login_with_invalid_cred");
            logger.info("Screenshot captured for passed test case: " + passedScreenshotPath);
            
        } catch (Exception e) {
            logger.error("Error occurred during invalid login test", e);
            try {
                String screenshotPath = snap.get_Screenshot("FAILED_login_with_invalid_cred");
                logger.info("Screenshot captured for failed test case: " + screenshotPath);
            } catch (Exception ex) {
                logger.error("Failed to capture screenshot", ex);
            }
            throw e;
        }
    }


}
