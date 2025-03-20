package page_objects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

public class loginPage {

	public loginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "username")
	WebElement username;

	@FindBy(id = "password")
	WebElement password;

	@FindBy(xpath = "//button[@type='submit']")
	WebElement loginBtn;

	@FindBy(xpath = "//i[text()=' Logout']")
	WebElement logoutBtn;

	@FindBy(css=".error")
	WebElement errorMsg;

	public void login(String name, String pass) {

		username.sendKeys(name);
		password.sendKeys(pass);
		loginBtn.click();
	}

	public void verifyLogin() {
		Assert.assertTrue(logoutBtn.isDisplayed());
	}

	public void verifyInvalidLogin() {
		Assert.assertTrue(errorMsg.isDisplayed());
	}
}
