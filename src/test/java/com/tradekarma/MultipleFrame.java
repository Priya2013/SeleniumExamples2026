
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
import org.openqa.selenium.Alert;
public class MultipleFrame {

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
    public void testLoginPageTitle() {

        // Get page title
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
            driver.findElement(By.id("alertbtn")).click();
            Alert alert= driver.switchTo().alert();
           alert.accept();
          }
          }

        //div[@class="dropdown open"]
      //  driver.navigate().to("https://www.letskodeit.com/practice");

    //  d  WebElement mainFrame= driver.findElement(By.id("courses-iframe"));
    //    driver.switchTo().frame(mainFrame);
    //       System.out.println("mainFrame: " + mainFrame);

              // WebElement ChildFrame= driver.findElement(By.id("courses-iframe"));
       //driver.switchTo().frame(ChildFrame);
        //  System.out.println("mainFrame: " + ChildFrame);

          driver.switchTo().defaultContent();

int iframeCount = driver.findElements(By.tagName("iframe")).size();
int frameCount = driver.findElements(By.tagName("frame")).size();
int totalFrames = driver.findElements(By.cssSelector("iframe, frame")).size();

// System.out.println("iframes: " + iframeCount + ", frames: " + frameCount + ", total: " + totalFrames);
        // Validate page title
       // assertTrue(
          ////      title.contains("EventHub"),
            //    "Title does not match expected value!");

    }

    @AfterMethod
    public void tearDown() {

        if (driver != null) {
         //   driver.quit();
        }

        System.out.println("Local Chrome browser closed successfully.");
    }
}
