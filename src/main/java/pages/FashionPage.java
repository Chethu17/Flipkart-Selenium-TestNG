package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import base.BasePage;

public class FashionPage extends BasePage {

    By fashionTab = By.xpath("(//div[@class='css-g5y9jx'])[11]");

    public FashionPage(WebDriver driver) {
        super(driver);
    }

    public void openFashionTab() {
        click(fashionTab);
    }
    
    public void selectSuggestion(String value) {
    	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(4));
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("div[class='lfFUxn'] li[class='Swx5kP']")));

        List<WebElement> suggestions = driver.findElements(By.cssSelector("div[class='lfFUxn'] li[class='Swx5kP']"));
        for (WebElement option : suggestions) {
            if (option.getText().contains(value)) {
            	wait.until(ExpectedConditions.elementToBeClickable(option));
            	option.click();
                break;
            }
        }
    }

    public String getFirstProductName() {
        return getElement(By.xpath("(//div[@class='Fo1I0b'])[1]")).getText();
    }
    
    public void openFirstProduct() {
        click(By.xpath("(//div[@class='p0C73x']//a[@class='atJtCj'])[1]"));
    }
}