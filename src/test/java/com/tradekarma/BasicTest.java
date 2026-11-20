package com.tradekarma;
import java.util.Random;
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

public class BasicTest { // 1. Added 'public'

  @Test
  public void BasicTest1() { // 2. Added 'public'
    Playwright playwright = Playwright.create();
    Browser browser = null;

    try {
      browser = playwright.chromium().launch(
          new BrowserType.LaunchOptions()
              .setChannel("chrome")
              .setHeadless(false));

      Page page = browser.newPage();
      PlaywrightAssertions.setDefaultAssertionTimeout(7000);
      page.setDefaultTimeout(8000);
      page.navigate("https://eventhub.rahulshettyacademy.com/login");

      String title = page.title();
      System.out.println("EventHub title: " + title);

      assertTrue(title.contains("EventHub"), "Unexpected page title: " + title);

      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Email"))
          .fill("prdahal@hotmail.com", new Locator.FillOptions().setTimeout(1000));
      page.getByRole(AriaRole.TEXTBOX, new Page.GetByRoleOptions().setName("Password")).fill("Nepal@977");
      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Sign In")).click();
      assertThat(
          page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →"))).isVisible();
      page.getByRole(AriaRole.LINK, new Page.GetByRoleOptions().setName("Browse Events →")).click();
      // Optional: brief pause to visually confirm login succeeded before closing

      page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("Add New Event")).click();
      ;
      // Locates a textbox associated with the label "Title" and fills it
     // Generate a random event title
      Random rand = new Random();
      int nameLength = 6;
      StringBuilder sb = new StringBuilder();
      String characters = "abcdefghijklmnopqrstuvwxyz";

      for (int i = 0; i < nameLength; i++) {
          int index = rand.nextInt(characters.length());
          char randomChar = characters.charAt(index);
          if (i == 0) {
              sb.append(Character.toUpperCase(randomChar));
          } else {
              sb.append(randomChar);
          }
      }
      String randomEventTitle = sb.toString();
      System.out.println("Generated Event Title: " + randomEventTitle);
      String randomEmail = sb.toString() + "@test.com";
        System.out.println("Generated Email: " + randomEmail);

      page.getByLabel("Title").fill(randomEventTitle);
      page.getByPlaceholder("Describe the event…").fill("Event description goes here.");
      page.locator("#event-date-\\&-time").fill("2026-11-18T16:23");
      page.locator("#category").selectOption("Sports");
      // page.getByPlaceholder("Describe the event...").fill("some description here");
      // page.getByRole(AriaRole.TEXTBOX,new
      // Page.GetByRoleOptions().setName("Title")).fill("Priya");
      page.getByPlaceholder("Venue name & address").fill("535 kolbo dr");
      page.locator("#city").fill("Austin");
      page.getByPlaceholder("0.00").fill("123");
      page.getByPlaceholder("e.g. 500").fill("3");
      // page.getByRole(AriaRole.BUTTON,new
      // Page.GetByRoleOptions().setName("submit")).click();
      page.locator("#add-event-btn").click(new Locator.ClickOptions().setTimeout(10000));

      page.waitForTimeout(3000);

      assertThat(page.getByText("Event Created!")).isVisible();
      page.locator("#nav-events").click();
      page.getByTestId("nav-events").click();
      Locator eventCards = page.getByTestId("event-card");
      System.out.println("Even Count is: " + eventCards.count());
      // page.getByRole(AriaRole.ARTICLE).filter(new
      // Locator.FilterOptions().setHasText("FestivalFeaturedDilli
      // Diwali")).getByTestId("book-now-btn").click();

      // Visibility of the card
      Locator targetCard = eventCards.filter(new Locator.FilterOptions().setHasText(randomEventTitle));

      assertThat(targetCard).isVisible(
          new LocatorAssertions.IsVisibleOptions().setTimeout(10000));
      // Plain .isVisible() returns a boolean (true or false) immediately
      boolean isVisible = targetCard.isVisible();
      System.out.println("Is the card visible? " + isVisible);
      String seatstext = targetCard.getByText("seats").innerText();
      //int seatNumberAfterBooking=Integer.seatstext.split("");
      System.out.println("Seat Available " + seatstext);
     targetCard.locator("#book-now-btn").click();
   
   
      page.locator("#customer-email").fill(randomEmail);
   
        int[] numbers={0,1,2,3,4,5,6,7,8,9};
        
        int PhoneLength=10;
        
        StringBuilder sb1 = new StringBuilder();
        for(int i=0;i<PhoneLength;i++){
         int digit=rand.nextInt(10);
           
         sb1.append(digit);
        }
         String randomPhoneNumber = sb1.toString();
        System.out.println("Random Phone Number: " + randomPhoneNumber);
    
      page.getByPlaceholder("+91 98765 43210").fill(randomPhoneNumber);
      //String randomEventTitle = sb.toString();
      page.getByPlaceholder("Your full name").fill(randomEventTitle);
      page.locator("#confirm-booking").click();
        assertThat(page.getByText("Your tickets are reserved")).isVisible();
      //page.getByLabel("View My Bookings").click();
        //  page.getByRole(AriaRole.BUTTON, new Page.GetByRoleOptions().setName("View My Bookings")).click();
     
      page.pause();

    } finally {
      // 3. Cleanly close browser and Playwright instances
      if (browser != null) {
        browser.close();
      }
      playwright.close();
    }
  }
}