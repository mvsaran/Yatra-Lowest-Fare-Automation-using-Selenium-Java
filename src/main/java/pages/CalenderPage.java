package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.List;

public class CalenderPage {
    WebDriver driver;
    WebDriverWait wait;

    By calendarMonths = By.xpath("//div[@class='react-datepicker__month-container']");
    By dayPrices = By.xpath(".//span[contains(@class, 'custom-day-content')]");

    public CalenderPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement getMonthElement(int index) {
        List<WebElement> monthContainers = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(calendarMonths));
        return monthContainers.get(index); // 0 = current month, 1 = next
    }

    public String getLowestFareInMonth(WebElement monthElement) {
        List<WebElement> priceElements = monthElement.findElements(dayPrices);

        int lowestFare = Integer.MAX_VALUE;
        WebElement bestDate = null;

        for (WebElement price : priceElements) {
            String text = price.getText().replace("₹", "").replace(",", "").trim();
            if (!text.isEmpty()) {
                int fare = Integer.parseInt(text);
                if (fare < lowestFare) {
                    lowestFare = fare;
                    bestDate = price;
                }
            }
        }

        if (bestDate != null) {
            WebElement fullDateElement = bestDate.findElement(By.xpath("./../.."));
            String dateLabel = fullDateElement.getAttribute("aria-label");
            return dateLabel + " - ₹" + lowestFare;
        }
        return "No price found";
    }
}
