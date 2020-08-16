package com.codechallenge.seleniumtestng.FBtest;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class FlightBookingTest {

	
	WebDriver driver = null;
    public static boolean keepAlive = true;
    public static long purgeInterval = 10; // in milliseconds
    public static long implicitlyWait = 30; // in seconds
    public static String driverPath = "D:\\ProgarmmingWorld\\FlightBookingTestSuite\\lib\\";
   
    
 // Selenium-TestNG Suite Initialization
    @BeforeSuite
    public void suiteSetup() {
        System.out.println("suiteSetup");
        System.setProperty("webdriver.chrome.driver", driverPath + "chromedriver.exe");
        
        driver = new ChromeDriver();
        //startMonitor();
    }

    // Selenium-TestNG Suite cleanup
    @AfterSuite
    public void suiteTeardown() {
        System.out.println("suiteTeardown");
        driver.close();
        driver.quit();
    }
    
    
    @BeforeMethod
    public void beforeTest() throws InterruptedException {
        System.out.println("Open Browser");
        driver.get("https://www.cheapair.com/");
        Thread.sleep(1000);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(implicitlyWait, TimeUnit.SECONDS);
        System.out.println("exit from openBrowser()");
    }

    @AfterMethod
    public void afterTest() {
        // Intentionally left blank.
    }
    
    // Selenium-TestNG Execution Engine
    @Test                                         
    public void flightDeals() throws InterruptedException, IOException {  	
    	

        
        WebDriverWait wait = new WebDriverWait(driver, 30); 
        String departDate="12-September-2020";
     


        // Click on one way radio button
       
        WebElement radioBtn = driver.findElement(By.xpath("//input[@id='tripTypeOW']//following::span[1]"));
        radioBtn.click();   
       
        

     // Insert data into Flying from text box
        driver.findElement(By.id("from1")).clear();
       driver.findElement(By.id("from1")).sendKeys("Delhi");
        
    // Insert data into Flying to text box 
       WebElement to = wait.until(ExpectedConditions.elementToBeClickable(By.id("to1")));
       to.sendKeys("Bangalore");
       Thread.sleep(5000);
       
       
        //Open Calendar and set a predefined date
        
        WebElement departCln = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='dates']")));
        departCln.click();
        
        Thread.sleep(5000);
        
      //Split the date 

        String date_dd_MM_yyyy[] = (departDate.split("-"));
      
        
        List<WebElement> departMonths=driver.findElements(By.xpath("//span[@class='ui-datepicker-month']"));
        List<WebElement> departYears=driver.findElements(By.xpath("//span[@class='ui-datepicker-year']"));
        
      
        int counter=0;
        for(WebElement departMonth : departMonths) { 
        	
        	counter=counter+1;
        	
        	
        	if(date_dd_MM_yyyy[1].equals(departMonth.getText()))
        	{
        		
        		
        		break;
        		/*for(WebElement departYear : departYears)
        		{
        			
        			System.out.print(departYear.getText());
        			if(date_dd_MM_yyyy[2]==departYear.getText())
                	{
        				 break;
                	}
        			
        		}*/
        	}
        	else
        	{
        		
        		if(counter==2)
        		{
        		driver.findElement(By.xpath("//a[@class='ui-datepicker-next ui-corner-all']")).click();
        		departMonths=driver.findElements(By.xpath("//span[@class='ui-datepicker-month']"));
        		departYears=driver.findElements(By.xpath("//span[@class='ui-datepicker-year']"));
        		continue;
        		}
        		
        	}
        	 
        	 } 
        
      WebElement departDateSet = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='ui-datepicker-group ui-datepicker-group-last']//a[@class='ui-state-default'][contains(text(),'"+date_dd_MM_yyyy[0]+"')]")));
       departDateSet.click();
       
      
      //Click Search Flight
       Thread.sleep(8000);
       driver.findElement(By.xpath("//button[@class='btn large block']")).click();
       Thread.sleep(5000);
       
       String currentWindow = driver.getWindowHandle();

       Set<String> handles = driver.getWindowHandles();
       Iterator<String> iterator = handles.iterator();
       String subWindowHandler = null;
       while (iterator.hasNext()){
           subWindowHandler = iterator.next();
           
       }
       driver.switchTo().window(subWindowHandler); 
       driver.findElement(By.xpath("//*[@id='signUpForm']/div[1]/span")).click();
       
     
       
       driver.findElement(By.xpath("//span[@class='fltrt-sort'][contains(text(),'Price')]")).click();
       
      //WebElement firstPriceAmt= driver.findElement(By.xpath("//div[@id='stickyHeaderRegion']//following::div[@data-hk='fltrtOptRegion'][1]//ol[@data-hk='fareOptionCollection']/li[1]/div[@class='btnfare btn withsub']"));
      //firstPriceAmt.click();
       WebElement price = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id='AI0']//span[@class='fltrt-opt-fares-amount'][contains(text(),'$84')]")));
       price.click();
      //driver.findElement(By.xpath("//div[@id='AI0']//span[@class='fltrt-opt-fares-amount'][contains(text(),'$84')]")).click();
       
    
       
     
     
       Thread.sleep(5000);
       
      
       
       //Assertion Verifying the date
       
       String checkoutDateAct=driver.findElement(By.xpath("//div[@class='src__Box-sc-1sbtrzs-0 fsZetV']")).getText();
       
       String checkoutDate[] = (checkoutDateAct.split(" "));
      
       
      
       Assert.assertTrue(checkoutDate[2].equals(date_dd_MM_yyyy[1].substring(0, 3)));
       Assert.assertTrue(checkoutDate[4].equals(date_dd_MM_yyyy[2]));
       
    }
    
    
 

    		
}
