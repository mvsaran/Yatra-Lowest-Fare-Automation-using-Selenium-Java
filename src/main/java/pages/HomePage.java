// This file is part of the 'pages' package (used to group all page-related classes)
package pages;

// These are tools from Selenium to control the browser and wait for elements
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;                 // Used to specify wait time in seconds
import java.util.concurrent.TimeoutException;  // (Optional here, but used for handling timeouts if needed)

// This class represents the homepage of the flight booking website
public class HomePage {
    WebDriver driver;       // Used to control the web browser
    WebDriverWait wait;     // Used to wait until elements are ready (like popups, buttons, etc.)

    // Locator to find the close/cancel button on a popup (usually login popup)
    By closePopup = By.xpath("//span[@class='style_cross__q1ZoV']/img[@alt='cross']");

    // Locator to find the 'Departure Date' calendar input field
    By departureDate = By.xpath("//div[@aria-label='Departure Date inputbox' and @role='button']");

    // Constructor: runs automatically when you create a HomePage object
    public HomePage(WebDriver driver) {
        this.driver = driver;   // Save the browser driver passed from the test
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // Wait max 10 seconds for elements
    }

    // This method checks if a login popup is showing, and closes it
    public void closeLoginPopupIfPresent() {
        // Wait until the close button of the popup is visible
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(closePopup));
        // Click on it to close the popup
        popup.click();
    }

    // This method clicks on the departure date field to open the calendar
    public void openDepartureCalendar() {
        // Wait until the departure date field is clickable
        WebElement departure = wait.until(ExpectedConditions.elementToBeClickable(departureDate));
        // Click on it to open the calendar
        departure.click();
    }
}
