package testNG;

import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestNG_01_Annotations {
	
  @Test
  public void TC01() {
	  System.out.println("Run TC 01");
  }
	
  @Test
	public void TC02() {
	  System.out.println("Run TC 02");
  }
  
  @Test
  public void TC03() {
	  System.out.println("Run TC 03");
	  
  }
  
  @Test
  public void TC04() {
	  System.out.println("Run TC 04");
  }
  
  @Test
  public void TC05() {
	  System.out.println("Run TC 05");
  }
  
  @Test
  public void TC06() {
	  System.out.println("Run TC 06");
  }
  
  
 
}
