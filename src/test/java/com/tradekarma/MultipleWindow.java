
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
import org.openqa.selenium.By;

public class MultipleWindow {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeMethod
    public void setUp() throws InterruptedException {

        // Launch local Chrome browser
        driver = new ChromeDriver();

        // Maximize browser
        driver.manage().window().maximize();

        // Open EventHub login page
        // driver.get("https://eventhub.rahulshettyacademy.com/login");
        js = (JavascriptExecutor) driver;
        js.executeScript("window.location='https://www.letskodeit.com/practice'");
        Thread.sleep(2000);

        // scroll down
        js.executeScript("window.scrollBy(0,1900);");

        Thread.sleep(2000);

    }

    @Test
    public void testLoginPageTitle() {

        // Get page title
        String title = driver.getTitle();

        System.out.println("Page Title: " + title);
        String ParentWindow = driver.getWindowHandle();
        System.out.println("ParentWindow: " + ParentWindow);
        WebElement WindowOne = driver.findElement(By.id("openwindow"));
        WindowOne.click();
        Set<String> handles= driver.getWindowHandles();
        for(String handle:handles){
             System.out.println("Windows: " + handle);
             if(!handle.equals(ParentWindow)){
                driver.switchTo().window(handle);
                   System.out.println("Windows: " + handle);
                   driver.close();
             }
 
        }
        driver.switchTo().window(ParentWindow);
         System.out.println("parent Windows: " + ParentWindow);

    }

    // Validate page title
    // assertTrue(
    ////      title.contains("EventHub"),
    // "Title does not match expected value!");

    

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }

        System.out.println("Local Chrome browser closed successfully.");
    }
}
