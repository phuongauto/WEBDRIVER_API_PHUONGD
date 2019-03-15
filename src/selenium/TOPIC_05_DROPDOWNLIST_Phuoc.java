package selenium;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TOPIC_05_DROPDOWNLIST_Phuoc {
    WebDriver driver;
	WebDriverWait waitExplicit;
	JavascriptExecutor executor;
	// initial xpath
	String parentDropdown = "//span[@aria-owns='color_listbox']//span[@class='k-select']";
	String allItem = "//ul[@id='color_listbox']/child::li";
	String expectedOrange = "Orange";
	String expectedBlack = "Black";
	String expectedGrey = "Grey";
	String capColor = "//label[@id='color_label']";
	By orangeIsDisplay = By.xpath("//span[@class='k-input' and contains(text(),'Orange')]");
	By blackIsDisplay = By.xpath("//span[@class='k-input' and contains(text(),'Black')]");
	By greyIsDisplay = By.xpath("//span[@class='k-input' and contains(text(),'Grey')]");
	@BeforeClass
	public void beforeTest() {
		//System.setProperty("webdriver.chrome.driver", "./driver/chromedriver");
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 30);
		executor = (JavascriptExecutor) driver;
		driver.manage().deleteAllCookies();

	}

	@Test
	public void TC01() throws Exception {
		driver.get("https://demos.telerik.com/kendo-ui/dropdownlist/index");
		//Selected Orange
		selectIteamInCustomDropDown(parentDropdown, allItem, expectedOrange);
		Assert.assertTrue(isElementDisplayed(orangeIsDisplay));
		driver.navigate().refresh();
		Thread.sleep(1000);
		//Selected Black
		selectIteamInCustomDropDown(parentDropdown, allItem, expectedBlack);
		Assert.assertTrue(isElementDisplayed(blackIsDisplay));
		driver.navigate().refresh();
		Thread.sleep(1000);
		//selected Grey
		selectIteamInCustomDropDown(parentDropdown, allItem, expectedGrey);
		Assert.assertTrue(isElementDisplayed(greyIsDisplay));

	}

	@AfterClass
	public void tearDown() {
		driver.quit();
	}

	public void selectIteamInCustomDropDown(String parentXpath, String allIteamXpath, String expectedValueiteam)
			throws Exception {
		WebElement parentDropDown = driver.findElement(By.xpath(parentXpath));
		waitExplicit.until(ExpectedConditions.visibilityOf(parentDropDown));
		executor.executeScript("arguments[0].scrollIntoView(true);", parentDropDown);
		executor.executeScript("arguments[0].click();", parentDropDown);
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allIteamXpath)));
		List<WebElement> allElements = driver.findElements(By.xpath(allIteamXpath));
		System.out.println("All items in dropdown is:" + allElements.size());
		for (WebElement childElement : allElements) {
			System.out.println("each child is" + childElement.getText());
			if (childElement.getText().equals(expectedValueiteam)) {
				executor.executeScript("arguments[0].scrollIntoView(true);", childElement);
				waitExplicit.until(ExpectedConditions.visibilityOf(childElement));
				Thread.sleep(1000);
				executor.executeScript("arguments[0].click();", childElement);
				break;
			}
		}
	}

	public void selectItemInDropdown(WebDriver driver, String locator, String value) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		select.selectByVisibleText(value);
	}

	public String getFirstSelectedItem(WebDriver driver, String locator) {
		Select select = new Select(driver.findElement(By.xpath(locator)));
		return select.getFirstSelectedOption().getText();
	}

	public boolean isElementDisplayed(By valued) {
		if (driver.findElement(valued).isDisplayed()) {
			System.out.println("Element [" + valued + "] is displayed");
			return true;
		} else {

			System.out.println("Element [" + valued + "] is not displayed");
			return false;
		}

	}

}