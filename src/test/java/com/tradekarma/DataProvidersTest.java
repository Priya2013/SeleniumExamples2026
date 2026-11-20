package com.tradekarma;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class DataProvidersTest {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws InterruptedException {
        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;
        js.executeScript("window.location='https://www.letskodeit.com'");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@href='/login']")).click();

        Reporter.log("Running setup before class", true);
    }

    @DataProvider(name = "input")
    public Object[][] getData() {
        //Adding some comment here
        return new Object[][] {
            {"prdahal@hotmail.com", "Selenium2023"}
        };
    }

    @Test(dataProvider = "input")
    public void testLoginPageTitle(String name, String pwd) throws InterruptedException {
        Reporter.log("Executing test step", true);
        driver.findElement(By.id("email")).sendKeys(name);
        driver.findElement(By.id("login-password")).sendKeys(pwd);
        driver.findElement(By.id("login")).click();
        Thread.sleep(2000);
        js.executeScript("window.scrollBy(0, 1900);");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        Reporter.log("Chrome browser closed successfully.", true);
    }
}