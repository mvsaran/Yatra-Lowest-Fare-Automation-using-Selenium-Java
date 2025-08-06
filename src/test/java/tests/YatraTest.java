// This is the 'tests' package which groups all test-related files
package tests;

// Importing necessary tools from Selenium and TestNG (a testing framework)
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;

import pages.CalenderPage; // Calendar page class to check fares
import pages.HomePage;     // Home page class to interact with homepage elements

// This is the test class that automates the process of finding lowest flight fares
public class YatraTest {
    WebDriver driver;                // Controls the browser
    HomePage homePage;              // Object to interact with homepage
    CalenderPage calendarPage;      // Object to interact with calendar page

    // This method runs once **before any test** (setup step)
    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();     // Create browser options
        options.addArguments("--disable-notifications"); // Turn off browser pop-ups

        driver = new ChromeDriver(options);              // Start a new Chrome browser with options

        driver.manage().window().maximize();             // Maximize the browser window
        driver.get("https://www.yatra.com");             // Open the Yatra website

        // Create page objects to interact with the site
        homePage = new HomePage(driver);
        calendarPage = new CalenderPage(driver);

        // Close any login pop-up if it appears
        homePage.closeLoginPopupIfPresent();
    }

    // This is the actual test that runs (automates fare comparison)
    @Test
    public void testLowestFares() throws InterruptedException {
        homePage.openDepartureCalendar();   // Click on the calendar input field

        Thread.sleep(2000);  // Wait for 2 seconds (for calendar to fully open)

        // Get the lowest fare from current month
        String currentMonthFare = calendarPage.getLowestFareInMonth(calendarPage.getMonthElement(0));

        // Get the lowest fare from next month
        String nextMonthFare = calendarPage.getLowestFareInMonth(calendarPage.getMonthElement(1));

        // Print both lowest fares in console
        System.out.println("✅ Current Month: " + currentMonthFare);
        System.out.println("✅ Next Month: " + nextMonthFare);

        // Call compare method to see which month has lower fare
        compare(currentMonthFare, nextMonthFare);
    }

    // This method compares the lowest fare from current and next month
    private void compare(String current, String next) {
        // Break the string into two parts: date and price (by splitting at " - ₹")
        String[] currentParts = current.split(" - ₹");
        String[] nextParts = next.split(" - ₹");

        String currentDate = currentParts[0].trim();               // Get the current month's date
        int currentFare = Integer.parseInt(currentParts[1].trim()); // Convert fare from text to number

        String nextDate = nextParts[0].trim();                     // Get the next month's date
        int nextFare = Integer.parseInt(nextParts[1].trim());      // Convert fare from text to number

        // Print both fares clearly
        System.out.println("🔎 Current Month Lowest Fare:");
        System.out.println("🗓 Date: " + currentDate);
        System.out.println("💰 Fare: ₹" + currentFare);

        System.out.println("\n🔎 Next Month Lowest Fare:");
        System.out.println("🗓 Date: " + nextDate);
        System.out.println("💰 Fare: ₹" + nextFare);

        // Compare and print which month has the lower fare
        if (currentFare < nextFare) {
            System.out.println("\n✅ Lowest fare is in current month on " + currentDate + " with ₹" + currentFare);
        } else if (currentFare > nextFare) {
            System.out.println("\n✅ Lowest fare is in next month on " + nextDate + " with ₹" + nextFare);
        } else {
            System.out.println("\n✅ Fare is same for both months: ₹" + currentFare);
        }
    }

    // This method runs after all tests are done (clean-up step)
    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit(); // Close the browser if it’s open
    }
}
