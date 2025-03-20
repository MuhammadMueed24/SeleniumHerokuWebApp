package page_objects;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import util.helpingClass;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;

public class fileUploadPage {
	WebDriver driver;

	public fileUploadPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "file-upload")
	WebElement fileUploadInput;

	@FindBy(id = "file-submit")
	WebElement submitButton;

	@FindBy(id = "uploaded-files")
	WebElement uploadedFiles;

	public void uploadFileUsingSendKeys(String filePath) {
		fileUploadInput.sendKeys(filePath);
		submitButton.click();
	}

	public void uploadFileUsingRobot(String filePath) throws AWTException, InterruptedException {
		try {
			// Ensure the file exists before proceeding
			File file = new File(filePath);
			if (!file.exists()) {
				throw new RuntimeException("File does not exist at: " + filePath);
			}

			// Make the file input visible and clickable using JavaScript and helper methods
			helpingClass helper = new helpingClass();

			// Wait for the file input element to be visible and clickable
			WebElement fileUploadInputElement = helper.waitForElementToBeVisible(driver, fileUploadInput);
			helper.waitForElementToBeClickable(driver, fileUploadInputElement);

			// Scroll the element into view if necessary
			helper.scrollToElement(driver, fileUploadInputElement);

			// Click the file input element to open the file dialog
			fileUploadInputElement.click();

			// Wait for native file dialog to appear (adjust timing as needed)
			Thread.sleep(1000);

			// Create Robot instance
			Robot robot = new Robot();

			// Copy the file path to the clipboard
			StringSelection selection = new StringSelection(filePath);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection, null);

			// Simulate the keyboard actions to paste the file path
			robot.delay(1000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.delay(1000); // Wait for paste operation to complete

			// Press Enter to confirm file selection
			robot.keyPress(KeyEvent.VK_ENTER);
			robot.delay(100);
			robot.keyRelease(KeyEvent.VK_ENTER);

			// Wait for the file dialog to close and the file to be selected
			Thread.sleep(2000);

			// Now, click the submit button to complete the upload process
			WebElement submitButtonElement = helper.waitForElementToBeVisible(driver, submitButton);
			helper.waitForElementToBeClickable(driver, submitButtonElement);
			helper.jsClick(driver, submitButtonElement);

			// Wait for the upload to complete
			Thread.sleep(2000);
		} catch (Exception e) {
			throw new RuntimeException("File upload failed: " + e.getMessage());
		}
	}

	public void verifyFileUpload(String expectedFileName) {
		Assert.assertEquals(uploadedFiles.getText().trim(), expectedFileName, "File upload verification failed.");
	}
}
