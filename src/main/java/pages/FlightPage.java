package pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class FlightPage extends BasePage {

    public FlightPage(WebDriver driver) {
        super(driver);
    }

    public void openTravelSection() {
        safeClick(By.xpath("(//div[@class='grid-formation grid-column-6'])[2]"));
    }

    public String selectSource(String city, String placeToSelect) {
    	By Source = By.xpath("//div[@class='css-g5y9jx r-z2wwpe r-1phboty r-18u37iz r-h0d30l r-1yadl64 r-ah5dr5']");
        safeClick(Source);
        type(By.xpath("//input[@placeholder='Search origin city/airport']"), city);
        List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='css-g5y9jx r-5kz9s3 r-13awgt0 r-18u37iz r-ytbthy']"));

        for (WebElement option : suggestions) {
            if (option.getText().contains(placeToSelect)) {
                option.click();
                break;
            }
        }
        return getText(Source);
    }

    public String selectDestination(String city, String placeToSelect) {
    	By Destination = By.xpath("(//div[@class='css-g5y9jx r-z2wwpe r-1phboty r-18u37iz r-h0d30l r-1yadl64 r-ah5dr5'])[2]");
        safeClick(Destination);
        type(By.xpath("//input[@placeholder='Search destination city/airport']"), city);
        List<WebElement> suggestions = driver.findElements(By.xpath("//div[@class='css-g5y9jx r-5kz9s3 r-13awgt0 r-18u37iz r-ytbthy']"));

        for (WebElement option : suggestions) {
            if (option.getText().contains(placeToSelect)) {
                option.click();
                break;
            }
        }
        return getText(Destination);
    }


    public void selectDepartureDate(String day, String monthYear) {

        safeClick(By.xpath("//div[normalize-space()='Departure']"));
        By firstMonthLocator = By.cssSelector("div[class='css-g5y9jx r-13awgt0 r-r2y082 r-1kb76zh'] div[class='css-146c3p1']");
        By secondMonthLocator = By.cssSelector("div[class='css-g5y9jx r-13awgt0 r-1jkjb'] div[class='css-146c3p1']");
        By nextMonthButton = By.cssSelector("svg[width='20']");

        boolean isInFirst = false;
        boolean monthFound = false;

        int maxAttempts = 12;

        for (int i = 0; i < maxAttempts; i++) {
            WebElement month1 = getElement(firstMonthLocator);
            WebElement month2 = getElement(secondMonthLocator);

            if (month1.getText().contains(monthYear)) {
                isInFirst = true;
                monthFound = true;
                break;
            } else if (month2.getText().contains(monthYear)) {
                isInFirst = false;
                monthFound = true;
                break;
            } else {
                safeClick(nextMonthButton);
            }
        }

        if (!monthFound) {
            throw new RuntimeException("Calendar month not found after "+ maxAttempts + " attempts: " + monthYear);
        }

        By dateLocator;
        if (isInFirst) {
            dateLocator = By.xpath("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[1]");
        } else {
            dateLocator = By.xpath("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[2]");
        }
        safeClick(dateLocator);
    }
    public void clickSearch() {
        safeClick(By.xpath("//div[normalize-space()='Search flights']"));
    }
}