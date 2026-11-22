package pages;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementClickInterceptedException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import base.BasePage;

public class ElectronicsPage extends BasePage {

    public ElectronicsPage(WebDriver driver) {
        super(driver);
    }

    public void openElectronicsTab() {
        click(By.xpath("//div[text()='Electronics']"));
        wait.until(ExpectedConditions.urlContains("elec"));
    }

    public void selectSuggestion(String value) {
        By suggestions = By.cssSelector("div.lfFUxn li.Swx5kP");

        new WebDriverWait(driver, Duration.ofSeconds(15)).ignoring(StaleElementReferenceException.class).until(d -> {
                for (WebElement option : d.findElements(suggestions)) {
                    if (option.isDisplayed() && option.getText().contains(value)) {
                        option.click();
                        return true;
                    }
                }
                return false;
            });
    }

    public void applyBrandFilters() throws InterruptedException {
    	By brandFilter = By.xpath("//div[text()='Brand']");  
    	By firstFilter =By.xpath("//div[text()='HP']");
    	By secondFilter = By.xpath("//div[text()='MOTOROLA']");
        click(brandFilter);
        click(firstFilter);
        Thread.sleep(2000);      
        click(brandFilter);
        click(By.xpath("(//div[@class='GN2Hca rQQNAD'])[1]"));
        
        Actions act = new Actions(driver);
        WebElement element = driver.findElement(secondFilter);
		act.moveToElement(element).perform();
		element.click();
    }
    
    public void scrollPage() {
        scroll(0, 300);
    }

    public void openLaptop() {
    	  Actions act = new Actions(driver);
		    By laptop = By.xpath("(//div[@class='ZFwe0M row'])[1]");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(laptop));
        
        act.moveToElement(element).perform();
		    element.click();
    /*
    // Scroll element to the center of the viewport
    ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView({block:'center', inline:'nearest'});", element);
		
    // Wait until element becomes clickable and click
    wait.until(ExpectedConditions.elementToBeClickable(laptop)).click();*/
       
    }


    public void clickBuyButton() {
    	System.out.println(driver.getCurrentUrl());
        By buyNow = By.xpath("//div[normalize-space()='Buy now']");
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

        wait.until(d -> {
            try {
                d.findElement(buyNow).click();
                return true;
            } catch (StaleElementReferenceException | ElementClickInterceptedException e) {
                return false;   // page still changing, try again
            }
        });
    }
}
