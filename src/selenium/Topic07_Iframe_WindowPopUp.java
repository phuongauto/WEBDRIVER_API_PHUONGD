package selenium;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic07_Iframe_WindowPopUp {
  WebDriver driver;
  JavascriptExecutor js;
  WebDriverWait waitExplicit;
  Actions action;
  Alert alert;
  Select select;
  
  @BeforeTest
  public void beforeTest() {
	  driver = new FirefoxDriver();
	  js = (JavascriptExecutor) driver;
	  waitExplicit = new WebDriverWait(driver, 30);
	  action = new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
  }

  @Test
  public void TC01() {
	  driver.get("http://hdfcbank.com");
	  /*Tại sao phải wait 10 giây? 
	   * Vì khi selenium đi tìm elements trong 1 list, nếu nó không tìm thấy element nào trong khoảng thời gian trong hàm wait thì nó cũng không đánh fail và nó chuyển tới bước tiếp theo.
	   * Khi selenium đi tìm element mà không thấy thì sẽ đánh fail luôn. Do đó ở đây ta dùng List <WebElement> để chạy được cả 2 case: 
	   			1 - nếu không thấy pop up(ko tìm thấy element) thì bước tới bước tiếp theo
	   			2 - nếu thấy pop up(thấy element - tức là notificationIframeSize > 0 thì nhảy vào pop up và thao tác với nó. Trong trường hợp nó tìm dc nhiều hơn 1 element thì nó sẽ thao tác với element đầu tiên.
	   */
	  driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	  List <WebElement> notificationIframe = driver.findElements(By.xpath("//iframe[@id='vizury-notification-template']"));
	  int notificationIframeSize = notificationIframe.size();
	  System.out.println("So luong iframe tim duoc la " + notificationIframe.size());
	  
	  //	size > 0 thì tức là iframe xuất hiện
	  if(notificationIframeSize > 0) {
		  driver.switchTo().frame(notificationIframe.get(0));
		  
		  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='container-div']/img")).isDisplayed());
		  
		  js.executeScript("arguments[0].click()", driver.findElement(By.xpath("//div[@id='div-close']")));
		 //	trong thời gian page còn đang load thì pop up đã xổ ra trước rồi, do đó có thể dùng javascript cho chắc ăn cái test case hơn là dùng driver.findElement(By.xpath("//div[@id='div-close']")).click();
		  
		  System.out.println("Close pop up success!");
		  
		 // 	Sau khi thao tác xong với cái iframe A, muốn thao tác với iframe B thì phải nhảy về parentFrame đã.
		  driver.switchTo().parentFrame();
		  
	  }
	  
	  System.out.println("Pass handle pop up!");
	  driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	  
	  //	Switch qua iframe chứa text
	  WebElement whatAre = driver.findElement(By.xpath("//div[@class='flipBannerWrap']//iframe"));
	  driver.switchTo().frame(whatAre);
	  Assert.assertTrue(driver.findElement(By.xpath("//span[@id='messageText' and contains(text(),'What are you')]")).isDisplayed());
	  
	  //	Switch về lại parent
	  driver.switchTo().parentFrame();
	  
	  //	Switch vào cái sliding banner
	  WebElement slidingIframe = driver.findElement(By.xpath("//div[@class='slidingbanners']//iframe"));
	  driver.switchTo().frame(slidingIframe);
	  
	  //	Verify banner images = 6
	  List< WebElement> bannerImages = driver.findElements(By.xpath("//img[@class='bannerimage']"));
	  System.out.println("Banner image = " + bannerImages.size());
	  Assert.assertEquals(bannerImages.size(), 6);
	  
	  //	Check 6 image element = real image
	  for(WebElement image: bannerImages) {
		  Assert.assertTrue(isImageLoadedSuccess(image));
	  }
	  
	  //	Switch về lại parent
	  driver.switchTo().parentFrame();
	  
	  //	Verify flipBanner is displayed
	  WebElement flipBanner = driver.findElement(By.xpath("//div[@class='flipBanner']"));
	  Assert.assertTrue(flipBanner.isDisplayed());
	  
	  //	Verify flipBanner has 8 items
	  List<WebElement>	flipBannerItems = driver.findElements(By.xpath("//div[@class='flipBanner']//img[@class='front icon']"));
	  System.out.println("Flip Banner = " + flipBannerItems.size());
	  Assert.assertEquals(flipBannerItems.size(), 8);
	  
	  //	Check 8 flip banner element = real image and they are displayed
	  for (WebElement banner: flipBannerItems ) {
		  Assert.assertTrue(isImageLoadedSuccess(banner));
		  Assert.assertTrue(banner.isDisplayed());
		  //System.out.println("8 flip banner element = real image and they are displayed");
	  }
	  
	  
  }
  
  public boolean isImageLoadedSuccess(WebElement imageElement) {
	  return (boolean) js.executeScript("return arguments[0].complete && typeof arguments[0].naturalWidth != \"undefined\" && arguments[0].naturalWidth > 0", imageElement);
  }
  
  //@Test
  public void TC02() {
	  
	  
  }
  
  //@Test
  public void TC03() {
	  
	  
  }
  
  
  
  @AfterTest
  public void afterTest() {
	  driver.quit();
  }

}
