
package com.tradekarma;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

import java.util.Set;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
public class Action {

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
             driver.findElement(By.id("email")).sendKeys("prdahal@hotmail.com");
              driver.findElement(By.id("login-password")).sendKeys("Selenium2023");
           driver.findElement(By.id("login")).click();
        // scroll down
        js.executeScript("window.scrollBy(0,1900);");

        Thread.sleep(2000);


    }

    @Test
    public void testLoginPageTitle() throws InterruptedException{

        String title = driver.getTitle();

        System.out.println("Page Title: " + title);
          WebElement PracticeDropDown=  driver.findElement(By.xpath("//div[contains(@class, 'dropdown')]//a[contains(text(), 'PRA')]"));
         System.out.println(PracticeDropDown.isDisplayed());
           System.out.println(PracticeDropDown.isEnabled());
           //a[@href="/practice" ]

          js.executeScript("arguments[0].click();",PracticeDropDown);
          driver.findElement(By.xpath("  //a[@href='/practice']")).click();
          String mainWindow= driver.getWindowHandle();
          System.out.println(mainWindow);
          Set<String> handles= driver.getWindowHandles();
          for(String handle:handles){
          if(!handle.equals(mainWindow)){
            driver.switchTo().window(handle);
            WebElement mainElement= driver.findElement(By.id("mousehover"));
            Actions action= new Actions(driver);
            action.moveToElement(mainElement).perform();
            Thread.sleep(4000);
              WebElement subElementTop= driver.findElement(By.xpath("//div[@class='mouse-hover-content']//a[text()='Top']"));
               action.moveToElement(subElementTop).click().perform();
                 Thread.sleep(4000);

                 
          }
          }



        // Validate page title
       // assertTrue(
          ////      title.contains("EventHub"),
            //    "Title does not match expected value!");

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        System.out.println("Local Chrome browser closed successfully.");
    }
}
