package selenium;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Topic_02_Xpath_Part_01 {
	WebDriver driver;
  
  	@BeforeTest
	public void beforeTest() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void TC_01_Login_Empty() throws InterruptedException {
		// 8 loại locator trong Selenium
		
		//Click on link "My Account" to open Sign in/up page
		//driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		
		//Leave Username/Password textbox blank
		//<input id="email" class="input-text required-entry validate-email" type="email" title="Email Address" name="login[username]" spellcheck="false" autocorrect="off" autocapitalize="off"/>
		//Click Login button
		//driver.findElement(By.xpath(".//*[@id='send2']")).click();
		
		//Use ID 
//		System.out.println("Sendkey email textbox by: ID:");
//		driver.findElement(By.id("email")).sendKeys("alo@gmail.com");
//		Thread.sleep(3000);
//		driver.findElement(By.id("email")).clear();
		
		//Use Class
//		System.out.println("2 - Senkey to Email textbox by: Class");
//		driver.findElement(By.className("validate-email")).sendKeys("ngon@gmail.com");
//		Thread.sleep(3000);
//		driver.findElement(By.className("validate-email")).clear();
		
//		//Use Name
//		System.out.println("2 - Senkey to Email textbox by: Name");
//		driver.findElement(By.name("login[username]")).sendKeys("name@gmail.com");
//		Thread.sleep(3000);
//		driver.findElement(By.name("login[username]")).clear();
//		
		//Use CSS
//		System.out.println("2 - Senkey to Email textbox by: CSS - ID ");
//		driver.findElement(By.cssSelector("input[id='email']")).sendKeys("css@gmail.com");
//		Thread.sleep(3000);
//		
//		System.out.println("2 - Senkey to Password textbox by: CSS - Name");
//		driver.findElement(By.cssSelector("input[name='login[password]']")).sendKeys("css@gmail.com");
//		driver.findElement(By.xpath(".//*[@id='send2']")).click();
//		Thread.sleep(3000);
//		
//		//Use Partial Link Text
//		System.out.println("2 - Senkey to Email textbox by: Partial Link Text");
//		driver.findElement(By.partialLinkText("CONTACT")).click();	
//		Thread.sleep(3000);
//		
		//Use  Link Text
//		System.out.println("2 - Senkey to Email textbox by: Link Text");
//		driver.findElement(By.linkText("SITE MAP")).click();
//		Thread.sleep(3000);
//		
//		//Use  Xpath
//		driver.get("http://live.guru99.com/index.php/customer/account/login/");
//		System.out.println("8 - Xpath cover ID");
//		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("xpath@id.com");
//		driver.findElement(By.xpath("//input[@id='email']")).clear();
//		Thread.sleep(3000);
//		
		
//		System.out.println("8 - Xpath cover Class");
//		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).sendKeys("xpath@class.com");
//		driver.findElement(By.xpath("//input[@class='input-text required-entry validate-email']")).clear();
//		Thread.sleep(3000);
//		
//		
//		System.out.println("8 - Xpath cover Name");
//		driver.findElement(By.xpath("//input[@name='login[username]']")).sendKeys("xpath@id.com");
//		driver.findElement(By.xpath("//input[@name='login[username]']")).clear();
//		Thread.sleep(3000);

		driver.get("http://live.guru99.com/");
		//Open Login screen
			driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		//Leave username/password blank
			driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("");
			driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("");
		//Click Login button
			driver.findElement(By.xpath(".//*[@id='send2']")).click();
		//Verify error message under username
			String errorMessUsername = driver.findElement(By.xpath(".//*[@id='advice-required-entry-email']")).getText();
			Assert.assertEquals(errorMessUsername, "This is a required field.");
		//Verify error message under password
			String errorMessPassword = driver.findElement(By.xpath(".//*[@id='advice-required-entry-pass']")).getText();
			Assert.assertEquals(errorMessPassword, "This is a required field.");
	}
	
	@Test
	public void TC_02_Login_With_Invalid_Email() {
		driver.get("http://live.guru99.com/");
		//Open Login screen
		driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		//Input invalid email to Email textbox
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("123434234@12312.123123");
		//Click Login button
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		//Verify error message
		String	errorMessInvalidEmail = driver.findElement(By.xpath(".//*[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(errorMessInvalidEmail, "Please enter a valid email address. For example johndoe@domain.com.");
	}
	
	@Test
	public void TC_03_Login_With_Password_Less_Than_06Character() {
		driver.get("http://live.guru99.com/");
		//Open Login screen
		driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		//Input correct email to Email textbox
		driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		//Input incorrect password
		driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("123");
		//Click Login button
		driver.findElement(By.xpath(".//*[@id='send2']")).click();
		//Verify error message
		String	errorMess6character = driver.findElement(By.xpath(".//*[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(errorMess6character, "Please enter 6 or more characters without leading or trailing spaces.");
	}
	@Test
	public void TC_04_Login_With_Incorrect_Password() {
		driver.get("http://live.guru99.com/");
		//Open Login screen
			driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[3]/div/div[4]/ul/li[1]/a")).click();
		//Input correct email to Email textbox
			driver.findElement(By.xpath(".//*[@id='email']")).sendKeys("automation@gmail.com");
		//Input incorrect password
			driver.findElement(By.xpath(".//*[@id='pass']")).sendKeys("Auto1500$");
		//Click Login button
			driver.findElement(By.xpath(".//*[@id='send2']")).click();
		//Verify error message
			//String	errorMessIncorrectPassword = driver.findElement(By.xpath(".//*[@id='top']/body/div[1]/div/div[2]/div/div/div/ul/li/ul/li/span")).getText();
			String	errorMessIncorrectPassword = driver.findElement(By.xpath("//span[contains(text(),'Invalid login or password.')]")).getText();
			Assert.assertEquals(errorMessIncorrectPassword, "Invalid login or password.");
	}

	
	@AfterTest
	public void afterTest() {
		driver.quit();
	}

}

