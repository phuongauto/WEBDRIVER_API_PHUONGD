package selenium;

import java.util.List;
import java.util.Random;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;


public class Topic09_UploadFiles {
  WebDriver driver;
  Alert alert;
  Actions action;
  Select select;
  JavascriptExecutor js;
  WebDriverWait waitExplicit;
  // Get relative path
  String rootFolder = System.getProperty("user.dir");
  
  String filename01 = "picture 01.png";
  String filename02 = "picture 02.jpg";
  String filename03 = "BreakingBad SS5 ep7.jpg";
  
  String fileNamePath01 = rootFolder + "\\UploadFiles\\" + filename01;
  String fileNamePath02 = rootFolder + "\\UploadFiles\\" + filename02;
  String fileNamePath03 = rootFolder + "\\UploadFiles\\" + filename03;
  
  String firefoxPath = rootFolder + "\\UploadFiles\\firefox.exe";
  String chromePath = rootFolder + "\\UploadFiles\\chrome.exe";
  String iePath = rootFolder + "\\UploadFiles\\ie.exe";
  
  // khai báo mảng này để dùng cho case upload 1 file 1 lần. Đối với multiple thì không cần
  String [] files = {fileNamePath01, fileNamePath02, fileNamePath03};
  
  @BeforeTest
  public void beforeTest() {
	  System.setProperty("webdriver.chrome.driver", ".\\driver\\chromedriver.exe");
	  driver = new ChromeDriver();
	  
	  js = (JavascriptExecutor)	driver;
	  waitExplicit = new WebDriverWait(driver, 30);
	  
	  System.out.println("Root folder path:" + rootFolder);
	  System.out.println("file 01 path: " + fileNamePath01);
	  System.out.println("file 02 path: " + fileNamePath02);
	  System.out.println("file 03 path: " + fileNamePath03);
	  
//	  System.setProperty("webdriver.gecko.driver", ".\\driver\\geckodriver.exe");
//	  driver = new FirefoxDriver();
  
	  
	  
//	  System.setProperty("webdriver.ie.driver", ".\\driver\\IEDriverServer.exe");
//	  driver = new InternetExplorerDriver();
  }
  
  //Tao random de dung cho email
  public int randomNumber() {
	  int random = (int )(Math.random() * 50 + 1);
	  return random;
  }
  
  //@Test
  public void TC01_SendKeys_Multiple_Upload() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  Thread.sleep(3000);
	  
	  // Step 02 - Sử dụng phương thức sendKeys để upload file nhiều file cùng lúc chạy cho 3 trình duyệt (IE/ Firefox/ Chrome)
	  if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
      WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
      uploadFile.sendKeys(fileNamePath01 + "\n" + fileNamePath02 + "\n" + fileNamePath03);
      Thread.sleep(1000);
	  } else {
          // Chưa handle được case sendkey multiple vào IE :(
		  System.out.println("Go to IE");
          WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
          js.executeScript("arguments[0].click()", uploadFile);
          uploadFile.sendKeys(fileNamePath01 + "\n" + fileNamePath02 + "\n" + fileNamePath03);
          Thread.sleep(1000);
	  }
	  
	  // Step 03 - Kiểm tra file đã được chọn thành công
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='picture 01.png']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='picture 02.jpg']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//p[text()='BreakingBad SS5 ep7.jpg']")).isDisplayed());
	  
	  // Step 04 - Click Start button để upload cho cả 3 file
	  List <WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
	  for(WebElement startButton: startButtons) {
		  startButton.click();
		  Thread.sleep(1500);
	  }
	  
	  // Step 05 - Sau khi upload thành công verify cả 3 file đã được upload
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename01 + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename02 + "']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename03 + "']")).isDisplayed());
  }
  
  //@Test
  public void TC02_AutoIT() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  
	  WebElement uploadFile = driver.findElement(By.xpath("//span[contains(@class,'fileinput-button')]"));
	  uploadFile.click();
	  Thread.sleep(3000);
	  
	  // Execute file exe
	  Runtime.getRuntime().exec(new String[] { chromePath, fileNamePath01 });
	  Thread.sleep(3000);
	  
	  uploadFile.click();
	  Thread.sleep(3000);
	  Runtime.getRuntime().exec(new String[] { chromePath, fileNamePath02 });
	  Thread.sleep(3000);
	  
	  uploadFile.click();
	  Thread.sleep(3000);
	  Runtime.getRuntime().exec(new String[] { chromePath, fileNamePath03 });
	  Thread.sleep(3000);
	  
	  List <WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
	  for(WebElement startButton: startButtons) {
		  js.executeScript("arguments[0].click()", startButton);
		  Thread.sleep(1500);
	  }
	  // Step 05 - Sau khi upload thành công verify cả 3 file đã được upload
		  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename01 + "']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename02 + "']")).isDisplayed());
		  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename03 + "']")).isDisplayed());
		  
  }
  
  //@Test chưa làm sao copy nhiều file vào clipboard được
  public void TC03_Robot() throws Exception {
	  driver.get("http://blueimp.github.com/jQuery-File-Upload/");
	  Thread.sleep(3000);
	  
	  // Specify the file location with extension
	  StringSelection select1 = new  StringSelection(fileNamePath01);
	  //StringSelection select2 = new  StringSelection(fileNamePath02);
      //StringSelection select3 = new  StringSelection(fileNamePath03);

      // Copy to clipboard
      Toolkit.getDefaultToolkit().getSystemClipboard().setContents(select1, null);
      
      if (driver.toString().contains("chrome")  || driver.toString().contains("firefox")) {
          WebElement uploadFile =  driver.findElement(By.cssSelector(".fileinput-button"));
          uploadFile.click();
          Thread.sleep(1000);
      } else {
          System.out.println("Go to IE");
          WebElement uploadFile =  driver.findElement(By.xpath("//input[@type='file']"));
          js.executeScript("arguments[0].click()", uploadFile);
          Thread.sleep(1000);
      }

      Robot robot = new Robot();
      Thread.sleep(1000);

      // Nhan phim Enter
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);

      // Nhan xuong Ctrl - V
      robot.keyPress(KeyEvent.VK_CONTROL);
      robot.keyPress(KeyEvent.VK_V);

      // Nha Ctrl - V
      robot.keyRelease(KeyEvent.VK_CONTROL);
      robot.keyRelease(KeyEvent.VK_V);
      Thread.sleep(1000);

      // Nhan Enter
      robot.keyPress(KeyEvent.VK_ENTER);
      robot.keyRelease(KeyEvent.VK_ENTER);
      Thread.sleep(1000);
      
      // 
      List <WebElement> startButtons = driver.findElements(By.xpath("//span[text()='Start']"));
	  for(WebElement startButton: startButtons) {
		  startButton.click();
		  Thread.sleep(1500);
	  }
	  
	  Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename01 + "']")).isDisplayed());
	  //Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename02 + "']")).isDisplayed());
	  //Assert.assertTrue(driver.findElement(By.xpath("//a[text()='" + filename03 + "']")).isDisplayed());
  }
  
  @Test
  public void TC04_UploadFiles() throws Exception {
	  // Step 01 - Open URL
	  driver.get("https://encodable.com/uploaddemo/");
	  
	  // Step 02 - Choose Files to Upload (Ex: UploadFile.jpg)
	  WebElement uploadFile = driver.findElement(By.xpath("//div[@class='upform_pair']//input[@type='file']"));
	  uploadFile.sendKeys(fileNamePath02);
	  
	  // Step 03 - Select dropdown (Upload to: /uploaddemo/files/)
	  // 3.1 Click to open dropdown
	  driver.findElement(By.xpath("//div[@class='upform_pair']//select[@name='subdir1']")).click();
	  
	  // 3.2 Dùng vòng lặp duyệt qua từng item trong dropdown
	  List <WebElement> Folders = driver.findElements(By.xpath("//div[@class='upform_pair']//select[@name='subdir1']//option"));
	  for (WebElement folder: Folders) {
		  WebElement targetFolder = driver.findElement(By.xpath("//div[@class='upform_pair']//select[@name='subdir1']//option[text()='/uploaddemo/files/']"));
		  
		  // nếu targetFolder mà hiển thị thì click vào nó để chọn
		  if (targetFolder.isDisplayed()) {
			  targetFolder.click();
		  } else {
			  // nếu không thì scroll tới targetFolder rồi click
			  js.executeScript("arguments[0].scrollIntoView(true)", folder);
			  targetFolder.click();
		  }
		  break;
	  }
	  
	  // Step 04 - Input random folder to 'New subfolder? Name:) textbox (Ex: dam1254353)
	  WebElement subfolder = driver.findElement(By.xpath("//div[@class='upform_pair']//input[@type='text']"));
	  subfolder.sendKeys("phuongdam" + randomNumber());
	  
	  // Step 05 - Input email and firstname (dam@gmail.com/ DAM DAO)
	  driver.findElement(By.xpath("//div[@class='clearfixtb']//input[@name='email_address']")).sendKeys("phuongdam01@gmail.com");
	  driver.findElement(By.xpath("//div[@class='clearfixtb']//input[@name='first_name']")).sendKeys("PhuongDam");
	  
	  // Step 06 - Click Begin Upload (Note: Wait for page load successfully)
	  driver.findElement(By.xpath("//div[@id='uploadbuttonwrapper']//input[@type='button']")).click();
	  waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@id='uploadDoneContainer']//dl[@id='fcuploadsummary']//dt[text()='Your upload is complete:']")));
	
	  // Step 07 - Verify information
	    // + Email Address: dam@gmail.com/ First Name: DAM DAO
	    // + File name: UploadFile.jpg
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dl[@id='fcuploadsummary']//dd[text()='Email Address: phuongdam01@gmail.com']")).isDisplayed());
	  Assert.assertTrue(driver.findElement(By.xpath("//div[@id='uploadDoneContainer']//dl[@id='fcuploadsummary']//dd[text()='First Name: PhuongDam']")).isDisplayed());
	  
	  // Step 08 - Click 'View Uploaded Files' link
	  driver.findElement(By.xpath("//div[@id='fc_content']/following-sibling::div//a[text()='View Uploaded Files']")).click();
	  
	  // Step 09 - Click to random folder (Ex: dam1254353)
	  Random r = new java.util.Random();
	  List<WebElement> uploadedFolders = driver.findElements(By.xpath("//table[@id='filelist']//tr[contains(@class,'dirrow')]"));
	  WebElement randomFolder = uploadedFolders.get(r.nextInt(uploadedFolders.size()));
	  randomFolder.click();
	  
	  // nếu vào folder không có item nào thì quay trở lại
//	  if (driver.findElement(By.xpath("//div[@class='sizeinfo']//span[text()='0 files / 0 folders / 0 KB']")).isDisplayed()) {
//		  driver.findElement(By.xpath("//a[text()='uploads']")).click();
//		  // tiếp tục vào 1 folder khác
//		  Random r2 = new java.util.Random();
//		  List<WebElement> uploadedFolders2 = driver.findElements(By.xpath("//table[@id='filelist']//tr[contains(@class,'dirrow')]"));
//		  WebElement randomFolder2 = uploadedFolders2.get(r2.nextInt(uploadedFolders2.size()));
//		  randomFolder2.click();
//	  } else {
//		  Assert.assertTrue(driver.findElement(By.xpath("//td[@class='fname thumb']//a[@class='thumb']")).isDisplayed());
//	  }
	  
	  

	  
	  

  }
  
  
  @AfterTest
  public void afterTest() {
	  //driver.quit();
  }

}
