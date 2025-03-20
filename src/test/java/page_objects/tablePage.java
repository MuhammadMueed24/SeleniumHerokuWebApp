package page_objects;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class tablePage {

	WebDriver driver;

	public tablePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "(//table)[1]")
	public WebElement firstTable;

	@FindBy(xpath = "(//table)[1]/tbody/tr")
	List<WebElement> rows;

	@FindBy(xpath = "(//table)[1]/tbody/tr/td[2]")
	List<WebElement> firstNames;

	@FindBy(xpath = "(//table)[1]/tbody/tr/td[1]")
	List<WebElement> lastNames;

	@FindBy(xpath = "(//table)[1]/tbody/tr/td[3]")
	List<WebElement> emails;

	public List<String> extractCompanyNames() {

		List<String> companyNames = new ArrayList<>();
		for (int i = 0; i < firstNames.size(); i++) {
			companyNames.add(firstNames.get(i).getText() + " " + lastNames.get(i).getText());
		}
		return companyNames;
	}

	public boolean verifyCompanyExists(String company) {
		return extractCompanyNames().contains(company);
	}

	public static List<String> extractColumnData(WebElement table, int columnIndex) {
		List<String> columnData = new ArrayList<>();
		List<WebElement> rows = table.findElements(By.xpath("./tbody/tr"));

		for (WebElement row : rows) {
			String data = row.findElement(By.xpath("./td[" + columnIndex + "]")).getText();
			columnData.add(data);
		}
		return columnData;
	}

}
