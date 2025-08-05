package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;
import java.util.concurrent.TimeoutException;

public class HomePage {
    WebDriver driver;
    WebDriverWait wait;

    By closePopup = By.xpath("//span[@class='style_cross__q1ZoV']/img[@alt='cross']");
    By departureDate = By.xpath("//div[@aria-label='Departure Date inputbox' and @role='button']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void closeLoginPopupIfPresent() {
        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(closePopup));
		popup.click();
    }

    public void openDepartureCalendar() {
        WebElement departure = wait.until(ExpectedConditions.elementToBeClickable(departureDate));
        departure.click();
    }
}
