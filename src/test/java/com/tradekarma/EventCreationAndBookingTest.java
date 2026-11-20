
package com.tradekarma;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import org.testng.annotations.Test;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.AriaRole;
import com.microsoft.playwright.assertions.PlaywrightAssertions;

import static org.testng.Assert.assertTrue;

public class EventCreationAndBookingTest extends BaseTest {

    @Test
    public void testCreateAndBookEvent() {

        Random random = new Random();

        // 1. Verify EventHub login page
        String title = page.title();

        System.out.println("EventHub title: " + title);

        assertTrue(
            title.contains("EventHub"),
            "Unexpected page title: " + title
        );

        // 2. Login using environment variables
        String email = System.getenv("EVENTHUB_EMAIL");
        String password = System.getenv("EVENTHUB_PASSWORD");

        assertTrue(email != null && !email.isBlank(),
            "EVENTHUB_EMAIL environment variable is missing");

        assertTrue(password != null && !password.isBlank(),
            "EVENTHUB_PASSWORD environment variable is missing");

        page.getByRole(
            AriaRole.TEXTBOX,
            new Page.GetByRoleOptions().setName("Email")
        ).fill(email);

        page.getByRole(
            AriaRole.TEXTBOX,
            new Page.GetByRoleOptions().setName("Password")
        ).fill(password);

        page.getByRole(
            AriaRole.BUTTON,
            new Page.GetByRoleOptions().setName("Sign In")
        ).click();

        // Verify successful login
        Locator browseEvents = page.getByRole(
            AriaRole.LINK,
            new Page.GetByRoleOptions().setName("Browse Events")
        );

        PlaywrightAssertions.assertThat(browseEvents).isVisible();

        browseEvents.click();

        // 3. Open Add New Event
        page.getByRole(
            AriaRole.BUTTON,
            new Page.GetByRoleOptions().setName("Add New Event")
        ).click();

        // 4. Generate a unique event title
        String randomEventTitle =
            "QAEvent" + System.currentTimeMillis();

        String randomEmail =
            "qa" + System.currentTimeMillis() + "@test.com";

        System.out.println("Event title: " + randomEventTitle);
        System.out.println("Booking email: " + randomEmail);

        // 5. Generate a future event date
        String futureDate = LocalDateTime.now()
            .plusDays(1)
            .withHour(16)
            .withMinute(23)
            .withSecond(0)
            .withNano(0)
            .format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm"));

        System.out.println("Event date: " + futureDate);

        // 6. Fill event details
        page.getByLabel("Title").fill(randomEventTitle);

        page.locator("textarea")
            .fill("Event created through Playwright automation.");

        page.locator("[id='event-date-&-time']")
            .fill(futureDate);

        page.locator("#category").selectOption("Sports");

        page.getByPlaceholder("Venue name & address")
            .fill("535 Kolbo Dr");

        page.locator("#city").fill("Austin");

        page.getByPlaceholder("0.00").fill("123");

        page.getByPlaceholder("e.g. 500").fill("3");

        // 7. Submit event
        page.locator("#add-event-btn").click();

        // Capture application response for debugging
        page.waitForTimeout(2000);

        System.out.println("URL after event submission: " + page.url());

        System.out.println(
            "Page content after submission:\n" +
            page.locator("body").innerText()
        );

        // 8. Navigate to events and verify event creation
        page.locator("#nav-events").click();

        Locator eventCards = page.getByTestId("event-card");

        Locator targetCard = eventCards.filter(
            new Locator.FilterOptions().setHasText(randomEventTitle)
        );

        PlaywrightAssertions.assertThat(targetCard)
            .isVisible(
                new com.microsoft.playwright.assertions.LocatorAssertions
                    .IsVisibleOptions()
                    .setTimeout(15000)
            );

        System.out.println("Event created successfully!");

        // 9. Read available seats
        String seatsText = targetCard.getByText("seats").innerText();

        System.out.println("Available seats: " + seatsText);

        // 10. Click Book Now
        targetCard.locator("#book-now-btn").click();

        // 11. Enter customer details
        page.locator("#customer-email").fill(randomEmail);

        // Generate random 10-digit phone number
        StringBuilder phone = new StringBuilder();

        for (int i = 0; i < 10; i++) {
            phone.append(random.nextInt(10));
        }

        String phoneNumber = phone.toString();

        System.out.println("Phone number: " + phoneNumber);

        page.getByPlaceholder("+91 98765 43210")
            .fill(phoneNumber);

        page.getByPlaceholder("Your full name")
            .fill(randomEventTitle);

        // 12. Confirm booking
        page.locator("#confirm-booking").click();

        // 13. Verify booking confirmation
        Locator bookingConfirmation =
            page.getByText("Your tickets are reserved");

        PlaywrightAssertions.assertThat(bookingConfirmation)
            .isVisible(
                new com.microsoft.playwright.assertions.LocatorAssertions
                    .IsVisibleOptions()
                    .setTimeout(15000)
            );

        System.out.println("--------------------------------");
        System.out.println("EVENT CREATION: PASSED");
        System.out.println("EVENT BOOKING: PASSED");
        System.out.println("Event: " + randomEventTitle);
        System.out.println("Customer: " + randomEmail);
        System.out.println("--------------------------------");
    }
}
