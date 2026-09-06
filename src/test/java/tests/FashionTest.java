package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.FashionPage;
import pages.HomePage;
import utilities.WindowUtils;

public class FashionTest extends BaseTest {

    HomePage home;
    FashionPage fashion;
    WindowUtils window;

    @Test(groups="smoke", timeOut=10000, priority=-1)
    public void verifyHoverMenu() throws InterruptedException {
        Actions act = new Actions(driver);
        act.moveToElement(driver.findElement(By.xpath("//span[normalize-space()='Login']"))).perform();
        WebElement hoverMenu = driver.findElement(By.cssSelector("span.v1zwn24")); 
        Assert.assertTrue(hoverMenu.isDisplayed());
    }
    
    @Test(priority = 1, groups = {"smoke"})
    public void verifyUserCanOpenFashionTab() {
        fashion = new FashionPage(driver);
        fashion.openFashionTab();
        Assert.assertTrue(driver.getCurrentUrl().contains("flipkart"));
    }

    @Test(priority = 2, groups = {"smoke"})
    public void verifyUserCanSearchShirts() throws InterruptedException {
        home = new HomePage(driver);
        Thread.sleep(2000);
        home.searchProduct("shirts");        
        Assert.assertTrue(driver.findElements(By.cssSelector("div[class='lfFUxn'] li[class='Swx5kP']")).size() > 0);
    }

    @Test(priority = 3, groups = {"smoke"})
    public void verifyUserCanSelectSearchSuggestion() {
        fashion = new FashionPage(driver);
        fashion.selectSuggestion("shirts for men");
        Assert.assertTrue(driver.getCurrentUrl().contains("shirts"));
      //Assert.assertTrue(driver.getPageSource().contains("shirts"));
    }

    @Test(priority = 4, groups = {"regression"})
    public void verifyFirstProductNameIsDisplayed() {
        fashion = new FashionPage(driver);
        String product = fashion.getFirstProductName();
        System.out.println(product);        
        Assert.assertFalse(product.isEmpty());
    }

    @Test(priority = 5, groups = {"smoke"})
    public void verifyUserCanOpenFirstProduct() {
        fashion = new FashionPage(driver);
        fashion.openFirstProduct();
        Assert.assertTrue(driver.getWindowHandles().size() > 1);
    }

    String parent;
    @Test(priority = 6, groups = {"regression"})
    public void verifyUserCanSwitchToProductWindow() {
        window = new WindowUtils(driver);
        parent=window.switchToChildWindow();
        Assert.assertTrue(driver.getWindowHandles().size() == 2);
    }
    
    @Test(priority = 7, groups = {"regression"})
    public void verifyUserCanCloseWindow() {
        window = new WindowUtils(driver);
        window.closeCurrentTabAndSwitchToParent(parent);
        Assert.assertTrue(driver.getWindowHandles().size() == 1);
    }
    
    
}