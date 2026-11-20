
package com.tradekarma;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
public class TestCase1 {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() throws InterruptedException{

        // Launch local Chrome browser
        driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open EventHub login page
        // driver.get("https://eventhub.rahulshettyacademy.com/login");
        js = (JavascriptExecutor) driver;
        js.executeScript("window.location='https://www.letskodeit.com/practice'");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@href='login']")).click();
        // sendKeys("prdahal@hotamail.com"));
             driver.findElement(By.id("email")).sendKeys("prdahal@hotamail.com");
              driver.findElement(By.id("login-password")).sendKeys("Selenium@2023");
           driver.findElement(By.id("login")).click();
        // scroll down
        js.executeScript("window.scrollBy(0,1900);");

        Thread.sleep(2000);

        // scroll UP
        js.executeScript("window.scrollBy(0,-1900);");
        Thread.sleep(2000);
     WebElement element = driver.findElement(By.id("mousehover"));
  
     js.executeScript("arguments[0].scrollIntoView(true);", element);
     js.executeScript("arguments[0].click();",element);
 Thread.sleep(5000);

        long height = (Long) js.executeScript("return window.innerHeight;");
        long width = (Long) js.executeScript("return window.innerWidth;");

        System.out.println("Height: " + height);
        System.out.println("Width: " + width);




    }

    @Test
    public void testLoginPageTitle() {

        // Get page title
        String title = driver.getTitle();

        System.out.println("Page Title: " + title);

      WebElement WindowOne= driver.findElement(By.id("openwindow"));
      WindowOne.click();


        // Validate page title
       // assertTrue(
          ////      title.contains("EventHub"),
            //    "Title does not match expected value!");

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
           // driver.quit();
        }

        System.out.println("Local Chrome browser closed successfully.");
    }
}
