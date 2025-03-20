package test_cases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import page_objects.fileUploadPage;
import test_base.baseClass;
import java.awt.*;
import java.io.File;
import java.net.URISyntaxException;

public class fileUpload extends baseClass {
    fileUploadPage fp;
    
    @BeforeClass
    public void setupTest() throws URISyntaxException {
        logger.info("Starting file upload test setup");
        try {
            fp = new fileUploadPage(driver);
            logger.info("File upload page object initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize file upload page object", e);
            throw e;
        }
    }
    
    @Test
    public void testFileUploadUsingSendKeys() throws URISyntaxException {
        logger.info("Starting test: File upload using SendKeys method");
        try {
            logger.debug("Navigating to upload page");
            driver.get(prop.getProperty("fileUploadUrl"));
            logger.info("Successfully navigated to file upload page");
            
            logger.debug("Locating test file resource");
            File file = new File(getClass().getClassLoader().getResource("file.txt").toURI());
            String filePath = file.getAbsolutePath();
            logger.debug("Test file path: {}", filePath);
            
            logger.debug("Attempting to upload file using SendKeys method");
            fp.uploadFileUsingSendKeys(filePath);
            logger.info("File upload operation completed");
            
            logger.debug("Verifying file upload for 'file.txt'");
            fp.verifyFileUpload("file.txt");
            logger.info("File upload using SendKeys verified successfully");
        } catch (URISyntaxException e) {
            logger.error("Error locating test file resource", e);
            throw e;
        } catch (Exception e) {
            logger.error("Error occurred during file upload test with SendKeys", e);
            throw e;
        }
    }
    
    @Test
    public void testFileUploadUsingRobot() throws AWTException, URISyntaxException, InterruptedException {
        logger.info("Starting test: File upload using Robot class");
        try {
            // Navigate to the file upload page
            driver.get(prop.getProperty("fileUploadUrl"));
            logger.info("Navigated to file upload page");

            // Get absolute path of test file
            File file = new File(getClass().getClassLoader().getResource("file.txt").toURI());
            String filePath = file.getAbsolutePath();
            logger.debug("Test file path: {}", filePath);

            // Verify file exists
            if (!file.exists()) {
                throw new RuntimeException("Test file not found at: " + filePath);
            }

            // Upload file using Robot class
            fileUploadPage fp = new fileUploadPage(driver);
            fp.uploadFileUsingRobot(filePath);
            logger.info("File upload initiated");

            // Verify upload (this method should verify if the file uploaded correctly)
            fp.verifyFileUpload("file.txt");
            logger.info("File upload verification successful");

        } catch (Exception e) {
            logger.error("Error in file upload test: ", e);
            throw e;
        }
    }
}



