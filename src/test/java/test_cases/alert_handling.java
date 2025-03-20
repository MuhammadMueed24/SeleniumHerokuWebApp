package test_cases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import page_objects.alertPage;
import test_base.baseClass;

public class alert_handling extends baseClass {
    
    alertPage ap;
    
    @BeforeClass
    public void setupTest() {
        logger.info("Starting alert handling test setup");
        try {
            ap = new alertPage(driver);
            logger.info("Alert page object initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize alert page object", e);
            throw e;
        }
    }
    
    @Test
    public void testJSAlert() {
        logger.info("Starting test: JavaScript Alert handling");
        try {
            logger.debug("Navigating to alerts page");
            driver.get(prop.getProperty("alertUrl"));
            logger.info("Successfully navigated to JavaScript alerts page");
            
            logger.debug("Attempting to handle JS Alert");
            ap.handleAlert("You successfully clicked an alert");
            logger.info("JavaScript Alert handled successfully");
        } catch (Exception e) {
            logger.error("Error occurred during JavaScript Alert test", e);
            throw e;
        }
    }
    
    @Test
    public void testJSConfirmDismiss() {
        logger.info("Starting test: JavaScript Confirm handling with dismiss");
        try {
            logger.debug("Navigating to alerts page");
            driver.get(prop.getProperty("alertUrl"));
            logger.info("Successfully navigated to JavaScript alerts page");
            
            logger.debug("Attempting to handle JS Confirm with Cancel option");
            ap.handleConfirm(false, "You clicked: Cancel");
            logger.info("JavaScript Confirm dismissed successfully");
        } catch (Exception e) {
            logger.error("Error occurred during JavaScript Confirm dismiss test", e);
            throw e;
        }
    }
    
    @Test
    public void testJSPrompt() {
        logger.info("Starting test: JavaScript Prompt handling");
        try {
            logger.debug("Navigating to alerts page");
            driver.get(prop.getProperty("alertUrl"));
            logger.info("Successfully navigated to JavaScript alerts page");
            
            String inputText = "Assignment text";
            logger.debug("Attempting to handle JS Prompt with input: '{}'", inputText);
            ap.handlePrompt(inputText);
            logger.info("JavaScript Prompt handled successfully with text input");
        } catch (Exception e) {
            logger.error("Error occurred during JavaScript Prompt test", e);
            throw e;
        }
    }
    
    @Test
    public void testGenericJSAlertHandler() {
        logger.info("Starting test: Generic JavaScript Popup Handler");
        try {
            logger.debug("Navigating to alerts page");
            driver.get(prop.getProperty("alertUrl"));
            logger.info("Successfully navigated to JavaScript alerts page");

            // Handling Alert
            ap.handleJavaScriptPopup("alert", true, null, "You successfully clicked an alert");
            logger.info("Handled JavaScript Alert successfully");

            // Handling Confirm (Cancel)
            ap.handleJavaScriptPopup("confirm", false, null, "You clicked: Cancel");
            logger.info("Handled JavaScript Confirm Dismiss successfully");

            // Handling Prompt with input
            String inputText = "Test input";
            ap.handleJavaScriptPopup("prompt", true, inputText, "You entered: " + inputText);
            logger.info("Handled JavaScript Prompt successfully with input text");

        } catch (Exception e) {
            logger.error("Error occurred during JavaScript Popup handling test", e);
            throw e;
        }
    }
}