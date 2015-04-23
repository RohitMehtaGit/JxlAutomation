package DataDriver;

import java.io.IOException;
import jxl.read.biff.BiffException;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class ReadDataTest {

  //Global initialization of Variables
  static ExcelSheetDriver xlsUtil;
  WebDriver driver = new FirefoxDriver();

  //Constructor to initialze Excel for Data source
  public ReadDataTest() throws BiffException, IOException
  {
		//Let's assume we have only one Excel File which holds all Testcases. weird :) Just for Demo !!!
	    xlsUtil = new ExcelSheetDriver("C:/Users/rohit.mehta/Desktop/gmail.xls");
	    //Load the Excel Sheet Col in to Dictionary for Further use in our Test cases.
	    ExcelSheetDriver.ColumnDictionary();
  }

  @BeforeTest
  public void EnvironmentalSetup()
  {
	  driver.get("https://stage-login.plumlytics.com");
  }

  @Test
  /*
   * Author : Rohit Mehta
   * Description : To Perform login operation in plumlytics
   */
  public void GmailLoginPage() throws InterruptedException {

	  //Create a for loop.. for iterate through our Excel sheet for all the test cases.
	  for(int rowCnt = 1;rowCnt< ExcelSheetDriver.RowCount();rowCnt++)
	  {

		  //Enter User Name by reading data from Excel
		  WebElement userName = driver.findElement(By.xpath("//input[@type='email']"));
		  userName.clear();
		  userName.sendKeys(ExcelSheetDriver.ReadCell(ExcelSheetDriver.GetCell("username"), rowCnt));

		  //Enter Password
		  WebElement password = driver.findElement(By.xpath("//input[@type='password']"));
		  password.clear();
		  password.sendKeys(ExcelSheetDriver.ReadCell(ExcelSheetDriver.GetCell("password"), rowCnt));

		  //Click on the Sign In Button
		  WebElement signin = driver.findElement(By.xpath("//button[@class='btn btn-primary login ng-binding']"));
		  signin.click();

		  //Sleep for some time,so that we can see things in action @ Screen :)
		  Thread.sleep(2000);
	  }
  }

}