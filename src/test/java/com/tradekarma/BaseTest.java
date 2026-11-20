package com.tradekarma;

import java.util.Random;
import org.testng.annotations.BeforeMethod; // <-- Added BeforeMethod import
import org.testng.annotations.AfterMethod;  // <-- Added AfterMethod import
import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;
import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.assertions.LocatorAssertions;
import com.microsoft.playwright.assertions.PlaywrightAssertions;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.options.AriaRole;
import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class BaseTest {
    
    // Protected so child test classes can directly access `page`, `browser`, etc.
    protected Playwright playwright;
    protected Browser browser;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();
        
        // Launching real Chrome via channel
        browser = playwright.chromium().launch(
            new BrowserType.LaunchOptions()
                .setChannel("chrome") 
                .setHeadless(false)
        );
        
        page = browser.newPage();
        page.navigate("https://eventhub.rahulshettyacademy.com/login");
        PlaywrightAssertions.setDefaultAssertionTimeout(7000);
        page.setDefaultTimeout(8000);
    }

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        System.out.println("Browser window closed successfully.");
    }
}