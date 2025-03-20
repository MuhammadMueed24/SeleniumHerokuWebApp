package page_objects;

import org.openqa.selenium.Alert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class alertPage {

	WebDriver driver;

	public alertPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
    @FindBy(xpath = "//button[text()='Click for JS Alert']")
    WebElement jsAlertButton;

    @FindBy(xpath = "//button[text()='Click for JS Confirm']")
    WebElement jsConfirmButton;

    @FindBy(xpath = "//button[text()='Click for JS Prompt']")
    WebElement jsPromptButton;

    @FindBy(id = "result")
    WebElement resultText;
    
    
    // Method to handle JS Alert (Click OK)
    public void handleAlert(String expectedText) {
        jsAlertButton.click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(resultText.getText(), expectedText);
    }

    // Method to handle JS Confirm (Accept/Dismiss)
    public void handleConfirm(boolean accept, String expectedText) {
        jsConfirmButton.click();
        Alert confirmAlert = driver.switchTo().alert();
        if (accept) {
            confirmAlert.accept();
        } else {
            confirmAlert.dismiss();
        }
        Assert.assertEquals(resultText.getText(), expectedText);
    }

    // Method to handle JS Prompt (Send Text & OK)
    public void handlePrompt(String inputText) {
        jsPromptButton.click();
        Alert promptAlert = driver.switchTo().alert();
        promptAlert.sendKeys(inputText);
        promptAlert.accept();
        Assert.assertTrue(resultText.getText().contains(inputText));
    }
    
    public void handleJavaScriptPopup(String popupType, boolean accept, String inputText, String expectedText) {
        switch (popupType.toLowerCase()) {
            case "alert":
                jsAlertButton.click();
                driver.switchTo().alert().accept();
                break;

            case "confirm":
                jsConfirmButton.click();
                Alert confirmAlert = driver.switchTo().alert();
                if (accept) {
                    confirmAlert.accept();
                } else {
                    confirmAlert.dismiss();
                }
                break;

            case "prompt":
                jsPromptButton.click();
                Alert promptAlert = driver.switchTo().alert();
                if (inputText != null) {
                    promptAlert.sendKeys(inputText);
                }
                promptAlert.accept();
                break;

            default:
                throw new IllegalArgumentException("Invalid popup type: " + popupType);
        }

        // Validate the expected result text
        Assert.assertEquals(resultText.getText(), expectedText);
    }
	

}
