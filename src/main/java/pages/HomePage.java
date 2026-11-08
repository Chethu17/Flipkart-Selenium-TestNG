package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {

    By searchBox = By.name("q");

    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void searchProduct(String product) {
        click(searchBox);     
        driver.findElement(searchBox).sendKeys(Keys.CONTROL + "a");
        driver.findElement(searchBox).sendKeys(Keys.DELETE);
        type(searchBox, product);
    }
    
    public void pressEnterInSearch() {
        driver.findElement(searchBox).sendKeys(Keys.ENTER);
    }
    
}
