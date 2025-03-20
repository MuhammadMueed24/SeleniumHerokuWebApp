package util;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import test_base.baseClass;

public class helpingClass extends baseClass {
	private static final Duration DEFAULT_WAIT_DURATION = Duration.ofSeconds(20);
	private static final Duration DEFAULT_POLLING_INTERVAL = Duration.ofMillis(500);

	public WebDriverWait getWait(WebDriver driver) {
		return new WebDriverWait(driver, DEFAULT_WAIT_DURATION);
	}

	public WebDriverWait getWait(WebDriver driver, long timeoutInSeconds) {
		return new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
	}

	public Wait<WebDriver> getFluentWait(WebDriver driver) {
		return new FluentWait<>(driver).withTimeout(DEFAULT_WAIT_DURATION).pollingEvery(DEFAULT_POLLING_INTERVAL)
				.ignoring(NoSuchElementException.class).ignoring(StaleElementReferenceException.class);
	}

	public WebElement waitForElementToBeVisible(WebDriver driver, By locator) {
		return getWait(driver).until(ExpectedConditions.visibilityOfElementLocated(locator));
	}

	public WebElement waitForElementToBeVisible(WebDriver driver, WebElement element) {
		return getWait(driver).until(ExpectedConditions.visibilityOf(element));
	}

	public WebElement waitForElementToBeClickable(WebDriver driver, By locator) {
		return getWait(driver).until(ExpectedConditions.elementToBeClickable(locator));
	}

	public WebElement waitForElementToBeClickable(WebDriver driver, WebElement element) {
		return getWait(driver).until(ExpectedConditions.elementToBeClickable(element));
	}

	public Boolean isElementVisible(WebDriver driver, WebElement element) {
		try {
			waitForElementToBeVisible(driver, element);
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public void waitForPageLoad(WebDriver driver) {
		ExpectedCondition<Boolean> pageLoadCondition = new ExpectedCondition<Boolean>() {
			public Boolean apply(WebDriver driver) {
				return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
			}
		};
		getWait(driver).until(pageLoadCondition);
	}

	public void scrollToElement(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void jsClick(WebDriver driver, WebElement element) {
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public void selectByVisibleText(WebElement element, String visibleText) {
		Select select = new Select(element);
		select.selectByVisibleText(visibleText);
	}

	public List<WebElement> waitForAllElementsVisible(WebDriver driver, By locator) {
		return getWait(driver).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
	}

	public void hoverOverElement(WebDriver driver, WebElement element) {
		Actions actions = new Actions(driver);
		actions.moveToElement(element).build().perform();
	}
}