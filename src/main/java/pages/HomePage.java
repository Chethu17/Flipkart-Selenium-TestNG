package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import base.BasePage;

public class HomePage extends BasePage {

    By searchBox = By.name("q");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String text) {
        By box = By.name("q");
        By suggestions = By.cssSelector("li.Swx5kP");

        for (int attempt = 1; attempt <= 3; attempt++) {
            WebElement el = wait.until(ExpectedConditions.refreshed(
                    ExpectedConditions.elementToBeClickable(box)));
            el.click();
            el.clear();
            for (char c : text.toCharArray()) {
                el.sendKeys(String.valueOf(c));
            }
            try {
                wait.until(d -> d.findElements(suggestions)
                                 .stream().anyMatch(WebElement::isDisplayed));
                return;
            } catch (TimeoutException e) {
                // dropdown didn't open, retry
            }
        }
        throw new RuntimeException("Suggestions never opened");
    }
    
    public void pressEnterInSearch() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }
    
}
