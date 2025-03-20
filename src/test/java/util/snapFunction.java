package util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import test_base.baseClass;

public class snapFunction extends baseClass {

	public String get_Screenshot(String testCaseName) throws IOException {

		if (driver == null) {
			throw new RuntimeException("WebDriver instance is null. Cannot take screenshot.");
		}

		try {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotName = testCaseName + "_" + timestamp;

			File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
			if (!screenshotDir.exists()) {
				screenshotDir.mkdirs();
			}

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);

			String destFile = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
			FileUtils.copyFile(src, new File(destFile));

			// Return relative path for report
			return destFile;
		} catch (Exception e) {
			throw new IOException("Failed to capture screenshot: " + e.getMessage(), e);
		}
	}

	public String getPassed_Screenshot(String testCaseName) throws IOException {
		return get_Screenshot("PASSED_" + testCaseName);
	}

	public static String takeScreenshot(WebDriver driver, String testCaseName) throws IOException {

		if (driver == null) {
			throw new RuntimeException("WebDriver instance is null. Cannot take screenshot.");
		}

		try {
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
			String screenshotName = testCaseName + "_" + timestamp;

			File screenshotDir = new File(System.getProperty("user.dir") + "/screenshots");
			if (!screenshotDir.exists()) {
				screenshotDir.mkdirs();
			}

			TakesScreenshot ts = (TakesScreenshot) driver;
			File src = ts.getScreenshotAs(OutputType.FILE);

			String destFile = System.getProperty("user.dir") + "/screenshots/" + screenshotName + ".png";
			FileUtils.copyFile(src, new File(destFile));

			return destFile;
		} catch (Exception e) {
			throw new IOException("Failed to capture screenshot: " + e.getMessage(), e);
		}
	}
}
