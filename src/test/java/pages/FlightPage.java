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
        click(By.xpath("(//div[@class='grid-formation grid-column-6'])[2]"));
    }

    public String selectSource(String city, String placeToSelect) {
    	By Source = By.xpath("//div[@class='css-g5y9jx r-z2wwpe r-1phboty r-18u37iz r-h0d30l r-1yadl64 r-ah5dr5']");
        click(Source);
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
        click(Destination);
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
        click(By.xpath("//div[normalize-space()='Departure']"));
        boolean isInFirst = false;
        while (true) {
            WebElement Month1 = getElement(By.cssSelector("div[class='css-g5y9jx r-13awgt0 r-r2y082 r-1kb76zh'] div[class='css-146c3p1']"));
            WebElement Month2 = getElement(By.cssSelector("div[class='css-g5y9jx r-13awgt0 r-1jkjb'] div[class='css-146c3p1']"));            
            if (Month1.getText().contains(monthYear)) {
            	isInFirst = true;
                break;
            }
            else if(Month2.getText().contains(monthYear)){
            	isInFirst = false;
            	break;
            }
            else {
                click(By.cssSelector("svg[width='20']"));
            }
        }
        
        if (isInFirst) {
        	click(By.xpath("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[1]"));
        }
        else {
        	click(By.xpath("(//div[@class='css-146c3p1'][normalize-space()='"+ day + "'])[2]"));
        }
        
    }

    public void clickSearch() {
        click(By.xpath("//div[@class='css-g5y9jx r-1q142lx r-pfqljz r-1ipicw7']//div[2]"));
    }
}