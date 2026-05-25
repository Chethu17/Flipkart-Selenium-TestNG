package pages;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
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
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    	By laptop =By.xpath("(//div[@class='RG5Slk'])[11]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(laptop));
        wait.until(ExpectedConditions.elementToBeClickable(laptop));
        
    	Actions act = new Actions(driver);
		WebElement element = driver.findElement(laptop);
		act.moveToElement(element).perform();
		element.click();
       
    }

    public void clickBuyButton() {
    	System.out.println(driver.getCurrentUrl());
        By buyButton = By.xpath("(//div[@class='_1psv1zeb9 _1psv1ze0 _1psv1zeku _1psv1ze6r'])[2]");
        WebElement button =wait.until(ExpectedConditions.elementToBeClickable(buyButton));
        button.click();
    }
}