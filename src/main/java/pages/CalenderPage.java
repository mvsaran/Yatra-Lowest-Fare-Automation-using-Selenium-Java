// This is part of the 'pages' package (used to group related files together)
package pages;

// These are necessary tools from the Selenium library to control the browser
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;  // Used to define wait time in seconds
import java.util.List;      // Allows us to work with lists of elements

// This class represents the Calendar Page where we check flight prices by date
public class CalenderPage {
    WebDriver driver;       // The main object that controls the browser
    WebDriverWait wait;     // Used to pause/wait until elements are ready

    // This locator points to all visible months in the calendar
    By calendarMonths = By.xpath("//div[@class='react-datepicker__month-container']");
    
    // This locator points to the price shown on each date
    By dayPrices = By.xpath(".//span[contains(@class, 'custom-day-content')]");

    // Constructor - this runs when we create a new CalendarPage object
    public CalenderPage(WebDriver driver) {
        this.driver = driver; // Store the browser driver passed to it
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Wait up to 10 seconds for elements
    }

    // This method returns one of the visible calendar month sections (0 = current month, 1 = next month)
    public WebElement getMonthElement(int index) {
        // Wait until all calendar month containers are visible, then store them in a list
        List<WebElement> monthContainers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarMonths));
        
        // Return the month element based on the index passed (example: 0 = this month)
        return monthContainers.get(index);
    }

    // This method finds the date with the lowest fare (price) in the given month element
    public String getLowestFareInMonth(WebElement monthElement) {
        // Get all the date elements that show price within the given month
        List<WebElement> priceElements = monthElement.findElements(dayPrices);

        int lowestFare = Integer.MAX_VALUE; // Start with a very high number
        WebElement bestDate = null;         // This will store the date with the lowest fare

        // Go through each price element (each date)
        for (WebElement price : priceElements) {
            // Get the price as text, remove ₹ symbol and commas, and trim spaces
            String text = price.getText().replace("₹", "").replace(",", "").trim();

            // Check if the text is not empty (some days may not have price)
            if (!text.isEmpty()) {
                int fare = Integer.parseInt(text); // Convert price from text to number
                // Check if this price is lower than our lowest found so far
                if (fare < lowestFare) {
                    lowestFare = fare;    // Update lowest price
                    bestDate = price;     // Save the date with lowest price
                }
            }
        }

        // If we found at least one valid fare
        if (bestDate != null) {
            // Go up two levels from price span to get the full date element
            WebElement fullDateElement = bestDate.findElement(By.xpath("./../.."));
            // Get the readable date (like "Mon Dec 01 2025") from the element
            String dateLabel = fullDateElement.getAttribute("aria-label");

            // Return the final result: date and fare
            return dateLabel + " - ₹" + lowestFare;
        }

        // If no fare was found
        return "No price found";
    }
}
