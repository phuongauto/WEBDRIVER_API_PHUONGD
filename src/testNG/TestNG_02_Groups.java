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

public class TestNG_02_Groups {
	
  @Test(groups = "customer")
  public void TC01() {
	  System.out.println("Run TC 01");
  }
	
  @Test(groups = "payment", description = "Create new Customter", priority = 1)
	public void TC02() {
	  System.out.println("Run TC 02");
  }
  
  @Test(groups = "payment", enabled = false)
  public void TC03() {
	  System.out.println("Run TC 03");
	  
  }
  
  @Test(groups = "customer")
  public void TC04() {
	  System.out.println("Run TC 04");
  }
  
  @Test(groups = "my account")
  public void TC05() {
	  System.out.println("Run TC 05");
  }
  
  @Test(groups = "payment", description = "Edit new Customter", priority = 0)
  public void TC06() {
	  System.out.println("Run TC 06");
  }
  
  
 
}
