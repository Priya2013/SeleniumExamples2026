package com.tradekarma;

import com.microsoft.playwright.Browser;
import com.microsoft.playwright.BrowserContext;
import com.microsoft.playwright.BrowserType;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.options.SelectOption;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MultiWindowTest {

    protected Playwright playwright;
    protected Browser browser;
    Locator locator;

    // Define separate contexts and pages for each window
    protected BrowserContext context;
    protected Page page;

    @BeforeMethod
    public void setUp() {
        playwright = Playwright.create();

        // Launch the browser instance
        browser = playwright.chromium().launch(
                new BrowserType.LaunchOptions()
                        .setChannel("chrome")
                        .setHeadless(false));

        // --- Context A (Window 1) ---
        context = browser.newContext();
        page = context.newPage(); // <-- FIXED: Added this line to initialize the page!
        page.navigate("https://rahulshettyacademy.com/loginpagePractise/");
    }

    @Test
    public void LoginPage() {

        Locator userRadioBtn = page.getByRole(AriaRole.RADIO, new Page.GetByRoleOptions().setName("User"));
        userRadioBtn.click();
        System.out.print(userRadioBtn.isChecked());
        page.locator("#okayBtn").click();
        page.locator("#terms").click();
        Locator agreement = page.locator("span:has-text(\"I Agree to the terms and conditions\")");
        System.out.println("Agreement visible : " + agreement.isVisible());

        page.locator(".form-control").selectOption(new SelectOption().setValue("teacher"));
    
    // @Test// public void testBothWindows() {
    // // Interact with Window 1 (Context A)
    // System.out.println("Window A Title: " + page.title());

    // Locator blinkingText = page.locator(".blinkingText");

    // // Wait for the new page/tab to open when clicking the blinking text
    // Page newPage = context.waitForPage(() -> {
    // blinkingText.first().click();
    // });

    // newPage.waitForLoadState();

    // // Extract the text from the child window
    // String childtext = newPage.locator(".red").textContent();
    // System.out.println("Child Text Content: " + childtext);

    // // Parse out the email ID using split
    // String emailID = childtext.split("at ")[1].split(" ")[0];
    // System.out.println("Extracted Email ID: " + emailID);

    // // Optional pause to observe
     page.waitForTimeout(30000);
    // }
}

    @AfterMethod
    public void tearDown() {
        if (browser != null) {
            browser.close();
        }
        if (playwright != null) {
            playwright.close();
        }
        System.out.println("All browser windows closed successfully.");
    }
}