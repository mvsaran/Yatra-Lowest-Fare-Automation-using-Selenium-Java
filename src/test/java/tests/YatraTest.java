package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.*;
import org.testng.annotations.*;
import pages.CalenderPage;
import pages.HomePage;

public class YatraTest {
    WebDriver driver;
    HomePage homePage;
    CalenderPage calendarPage;

    @BeforeClass
    public void setup() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-notifications");
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.get("https://www.yatra.com");

        homePage = new HomePage(driver);
        calendarPage = new CalenderPage(driver);

        homePage.closeLoginPopupIfPresent();
    }

    @Test
    public void testLowestFares() throws InterruptedException {
        homePage.openDepartureCalendar();

        Thread.sleep(2000); // Wait for calendar animation (optional)

        String currentMonthFare = calendarPage.getLowestFareInMonth(calendarPage.getMonthElement(0));
        String nextMonthFare = calendarPage.getLowestFareInMonth(calendarPage.getMonthElement(1));

        System.out.println("✅ Current Month: " + currentMonthFare);
        System.out.println("✅ Next Month: " + nextMonthFare);

        compare(currentMonthFare, nextMonthFare);
    }

    private void compare(String current, String next) {
        // Split string into date and fare
        String[] currentParts = current.split(" - ₹");
        String[] nextParts = next.split(" - ₹");

        String currentDate = currentParts[0].trim(); // e.g., Monday, September 16, 2025
        int currentFare = Integer.parseInt(currentParts[1].trim());

        String nextDate = nextParts[0].trim(); // e.g., Wednesday, October 2, 2025
        int nextFare = Integer.parseInt(nextParts[1].trim());

        System.out.println("🔎 Current Month Lowest Fare:");
        System.out.println("🗓 Date: " + currentDate);
        System.out.println("💰 Fare: ₹" + currentFare);

        System.out.println("\n🔎 Next Month Lowest Fare:");
        System.out.println("🗓 Date: " + nextDate);
        System.out.println("💰 Fare: ₹" + nextFare);

        if (currentFare < nextFare) {
            System.out.println("\n✅ Lowest fare is in current month on " + currentDate + " with ₹" + currentFare);
        } else if (currentFare > nextFare) {
            System.out.println("\n✅ Lowest fare is in next month on " + nextDate + " with ₹" + nextFare);
        } else {
            System.out.println("\n✅ Fare is same for both months: ₹" + currentFare);
        }
    }


    @AfterClass
    public void tearDown() {
        if (driver != null) driver.quit();
    }
}
