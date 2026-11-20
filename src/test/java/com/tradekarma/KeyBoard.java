
package com.tradekarma;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;
import org.testng.asserts.Assertion;
import org.testng.Assert;

import static org.testng.Assert.assertTrue;
import org.testng.asserts.SoftAssert;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.Alert;
public class KeyBoard {
//private static final Logger log = LogManager.getLogger(LoggingConsole//.class);
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
        js.executeScript("window.location='https://www.letskodeit.com'");
        Thread.sleep(2000);
           driver.findElement(By.xpath("//a[@href='/login']")).click();
        // sendKeys("prdahal@hotamail.com"));
       WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("prdahal@hotmail.com");

// // Select all, copy, and paste
// email.sendKeys(Keys.chord(Keys.COMMAND, "a"));
// email.sendKeys(Keys.chord(Keys.COMMAND, "c"));
// email.sendKeys(Keys.chord(Keys.COMMAND, "v"));

System.out.println(email.getDomProperty("value"));

             email.sendKeys(Keys.TAB);
              
         WebElement password=     driver.findElement(By.id("login-password"));
          password.sendKeys(Keys.TAB);
        password.sendKeys("Selenium2023");
         WebElement loginBtn=  driver.findElement(By.id("login"));
         loginBtn.sendKeys(Keys.ENTER);
         System.out.println("keyword works here");
        // scroll down
        js.executeScript("window.scrollBy(0,1900);");

        Thread.sleep(2000);

    }

    @Test
    public void testLoginPageTitle() {
      SoftAssert sa= new SoftAssert();

        // // Get page title
        String title = driver.getTitle();

        System.out.println("Page Title: " + title);
        WebElement MyHomePageTitle=driver.findElement(By.xpath("//h1[@class='dynamic-heading' and contains(text(),'My Courses ')] "));
        System.out.println(MyHomePageTitle.isDisplayed());
     String actualText = MyHomePageTitle.getText().trim();
    String expectedText = "NewCourses"; //My Courses


    try {
      sa.assertEquals(actualText, expectedText, "Heading text mismatch");
       // Assert.assertEquals(actualText, expectedText, "Heading text mismatch");
        System.out.println("Assertion PASS: Expected '" + expectedText + "' but found '" + actualText + "'");
    } catch (AssertionError e) {
        System.out.println("Assertion FAIL: Expected '" + expectedText + "' but found '" + actualText + "'");
        throw e;
    }
     WebElement PracticeDropDown=  driver.findElement(By.xpath("//div[contains(@class, 'dropdown')]//a[contains(text(), 'PRA')]"));
         System.out.println(PracticeDropDown.isDisplayed());
           System.out.println(PracticeDropDown.isEnabled());
           //a[@href="/practice" ]

        // Validate page title
       // assertTrue(
          ////      title.contains("EventHub"),
            //    "Title does not match expected value!");
   sa.assertAll();
    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
         //   driver.quit();
        }

        System.out.println("Local Chrome browser closed successfully.");
    }
}
