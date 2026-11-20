package com.tradekarma;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TestNGClass {

    WebDriver driver;
    JavascriptExecutor js;

    @BeforeClass(alwaysRun = true)
    public void setUp() throws InterruptedException {
        // ChromeOptions options = new ChromeOptions();
        // options.addArguments("--headless=new");
        // options.addArguments("--window-size=1920,1080");

        // driver = new ChromeDriver(options);
        driver = new ChromeDriver();

        js = (JavascriptExecutor) driver;
        js.executeScript("window.location='https://www.letskodeit.com'");
        Thread.sleep(2000);

        driver.findElement(By.xpath("//a[@href='/login']")).click();

        WebElement email = driver.findElement(By.id("email"));
        email.sendKeys("prdahal@hotmail.com");
        System.out.println(email.getDomProperty("value"));
        email.sendKeys(Keys.TAB);

        WebElement password = driver.findElement(By.id("login-password"));
        password.sendKeys(Keys.TAB);
        password.sendKeys("Selenium2023");

        WebElement loginBtn = driver.findElement(By.id("login"));
        loginBtn.sendKeys(Keys.ENTER);
        System.out.println("keyword works here");

        js.executeScript("window.scrollBy(0,1900);");
        Thread.sleep(2000);
    }

    @Test
    public void testLoginPageTitle() {
        SoftAssert sa = new SoftAssert();

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        WebElement myHomePageTitle = driver.findElement(
                By.xpath("//h1[@class='dynamic-heading' and contains(text(),'My Courses')]"));
        System.out.println(myHomePageTitle.isDisplayed());

        String actualText = myHomePageTitle.getText().trim();
        String expectedText = "My Courses";
        sa.assertEquals(actualText, expectedText, "Heading text mismatch");

        WebElement practiceDropDown = driver.findElement(
                By.xpath("//div[contains(@class, 'dropdown')]//a[contains(text(), 'PRA')]"));
        System.out.println(practiceDropDown.isDisplayed());
        System.out.println(practiceDropDown.isEnabled());
        System.out.println("Priority 1");

        sa.assertAll();
    }

    @Test(groups = {"smoke"})
    public void test2() {
        SoftAssert sa = new SoftAssert();

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        WebElement myHomePageTitle = driver.findElement(
                By.xpath("//h1[@class='dynamic-heading' and contains(text(),'My Courses')]"));
        System.out.println(myHomePageTitle.isDisplayed());

        String actualText = myHomePageTitle.getText().trim();
        String expectedText = "My Courses";
        sa.assertEquals(actualText, expectedText, "Heading text mismatch");

        WebElement practiceDropDown = driver.findElement(
                By.xpath("//div[contains(@class, 'dropdown')]//a[contains(text(), 'PRA')]"));
        System.out.println(practiceDropDown.isDisplayed());
        System.out.println(practiceDropDown.isEnabled());
        System.out.println("Priority 2 SMoke");

        sa.assertAll();
    }

    @Test(groups = {"smoke"}, dependsOnMethods = {"test4"})
    public void test3() {
        SoftAssert sa = new SoftAssert();

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        WebElement myHomePageTitle = driver.findElement(
                By.xpath("//h1[@class='dynamic-heading' and contains(text(),'My Courses')]"));
        System.out.println(myHomePageTitle.isDisplayed());

        String actualText = myHomePageTitle.getText().trim();
        String expectedText = "My Courses";
        sa.assertEquals(actualText, expectedText, "Heading text mismatch");

        WebElement practiceDropDown = driver.findElement(
                By.xpath("//div[contains(@class, 'dropdown')]//a[contains(text(), 'PRA')]"));
        System.out.println(practiceDropDown.isDisplayed());
        System.out.println(practiceDropDown.isEnabled());
        System.out.println("Priority 3 SMoke");

        sa.assertAll();
    }

    @Test(groups = {"smoke"})
    public void test4() {
        SoftAssert sa = new SoftAssert();

        String title = driver.getTitle();
        System.out.println("Page Title: " + title);

        WebElement myHomePageTitle = driver.findElement(
                By.xpath("//h1[@class='dynamic-heading' and contains(text(),'My Courses')]"));
        System.out.println(myHomePageTitle.isDisplayed());

        String actualText = myHomePageTitle.getText().trim();
        String expectedText = "My Courses";
        sa.assertEquals(actualText, expectedText, "Heading text mismatch");

        WebElement practiceDropDown = driver.findElement(
                By.xpath("//div[contains(@class, 'dropdown')]//a[contains(text(), 'PRA')]"));
        System.out.println(practiceDropDown.isDisplayed());
        System.out.println(practiceDropDown.isEnabled());

        WebElement textMessage = driver.findElement(
                By.xpath("//div[@class='text-center dynamic-text' and contains(text(), 'You have not enrolled to any courses.')]"));
        sa.assertTrue(textMessage.getText().contains("You have not enrolled"),
                "Message did not contain expected text");
        System.out.println("Priority 0");

        sa.assertAll();
        System.out.println("");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
        System.out.println("Headless Chrome browser closed successfully.");
    }
}