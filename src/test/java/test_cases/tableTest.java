package test_cases;

import org.testng.annotations.Test;
import org.testng.annotations.BeforeClass;
import java.util.List;
import page_objects.tablePage;
import test_base.baseClass;

public class tableTest extends baseClass {
    tablePage tp;
    
    @BeforeClass
    public void setupTest() {
        logger.info("Starting table test setup");
        try {
            tp = new tablePage(driver); // Initialize after driver is set up
            logger.info("Table page object initialized successfully");
        } catch (Exception e) {
            logger.error("Failed to initialize table page object", e);
            throw e;
        }
    }
    
    @Test
    public void testExtractCompanyNames() {
        logger.info("Starting test: Extract company names from table");
        try {
            logger.debug("Navigating to tables page");
            driver.get(prop.getProperty("tableUrl"));
            logger.info("Successfully navigated to tables page");
            
            logger.debug("Attempting to extract company names from table");
            List<String> companyNames = tp.extractCompanyNames();
            
            logger.debug("Extracted {} company names", companyNames.size());
            logger.info("Company names extraction completed successfully");
            logger.debug("Extracted Company Names: {}", companyNames);
            
            // Replacing System.out with logger
            logger.info("Extracted Company Names: {}", companyNames);
        } catch (Exception e) {
            logger.error("Error occurred during company names extraction test", e);
            throw e;
        }
    }
    
    @Test
    public void testVerifyCompanyExists() {
        logger.info("Starting test: Verify company exists in table");
        try {
            logger.debug("Navigating to tables page");
            driver.get(prop.getProperty("tableUrl"));
            logger.info("Successfully navigated to tables page");
            
            String expectedCompany = "Jason Doe";
            logger.debug("Checking if company '{}' exists in table", expectedCompany);
            
            boolean exists = tp.verifyCompanyExists(expectedCompany);
            
            if (exists) {
                logger.info("Company '{}' found in table as expected", expectedCompany);
            } else {
                logger.warn("Company '{}' not found in table", expectedCompany);
            }
            
            // Replacing System.out with logger
            logger.info("{} exists: {}", expectedCompany, exists);
        } catch (Exception e) {
            logger.error("Error occurred during company verification test", e);
            throw e;
        }
    }
    
    @Test
    public void ExtractData() {
        logger.info("Starting test: Extract column data from table");
        try {
            logger.debug("Navigating to tables page");
            driver.get(prop.getProperty("tableUrl"));
            logger.info("Successfully navigated to tables page");
            
            int columnIndex = 2;
            logger.debug("Attempting to extract data from column index {}", columnIndex);
            
            List<String> columnData = tablePage.extractColumnData(tp.firstTable, columnIndex);
            
            logger.debug("Extracted {} items from column {}", columnData.size(), columnIndex);
            logger.info("Column data extraction completed successfully");
            
            // Replacing System.out with logger
            logger.info("Extracted Data from Column {}: {}", columnIndex, columnData);
        } catch (Exception e) {
            logger.error("Error occurred during column data extraction test", e);
            throw e;
        }
    }
}