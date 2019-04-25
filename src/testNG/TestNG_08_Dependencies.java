package testNG;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestNG_08_Dependencies {
  WebDriver driver;
  
  @Test(groups = "customer")
  public void TC01() {
	  System.out.println("Run TC 01");
  }
	
  // đã tới 1:35:18 
  
  
  
  
  
  // TC02 chỉ được chạy khi TC01 pass
  @Test(description = "Create new Customter", dependsOnMethods="TC01")
	public void TC02() {
	  System.out.println("Run TC 02");
	  Assert.assertTrue(false);
	  // ở đây ta để TC02 fail => TC03 sẽ không chạy và TC03 sẽ bị skip
	  // chuột phải chọn  run as testNG để chạy cái class này nhé
  }
  
  // TC03 chỉ được chạy khi TC02 pass
  @Test(dependsOnMethods="TC02")
  public void TC03() {
	  System.out.println("Run TC 03");
	  
  }
  
  @Test()
  public void TC04() {
	  System.out.println("Run TC 04");
  }
  
  @Test()
  public void TC05() {
	  System.out.println("Run TC 05");
  }
  
  @Test(description = "Edit new Customter")
  public void TC06() {
	  System.out.println("Run TC 06");
  }
 
}
