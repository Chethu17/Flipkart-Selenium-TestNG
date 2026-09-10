package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.ElectronicsPage;
import pages.HomePage;
import utilities.WindowUtils;

public class ElectronicsTest extends BaseTest {

    @Test(priority=1, groups="smoke")
    public void verifySwitchedtoElectronics() {
        ElectronicsPage electronics = new ElectronicsPage(driver);
        electronics.openElectronicsTab();        
        Assert.assertTrue(driver.getCurrentUrl().contains("elec"));
    }
    
    @Test(priority=2, groups="smoke")
    public void verifySearchProduct() throws InterruptedException {
    	HomePage home = new HomePage(driver);
        ElectronicsPage electronics = new ElectronicsPage(driver);
    	home.searchProduct("laptop");
    	Thread.sleep(2000);
        electronics.selectSuggestion("under 40000");
        Assert.assertTrue(driver.getCurrentUrl().contains("laptop"));
    }
    
    @Test(priority=3, groups="smoke")
    public void verifyApplyFilter() throws InterruptedException {
        ElectronicsPage electronics = new ElectronicsPage(driver);
        electronics.applyBrandFilters();
        Assert.assertTrue(driver.getPageSource().contains("MOTOROLA"));
    }
    
    @Test(priority=4, groups="smoke")
    public void verifyScrollPage() {
        ElectronicsPage electronics = new ElectronicsPage(driver);
        electronics.scrollPage();
    }
    
    @Test(priority=5, groups="smoke")
    public void verifyOpenLaptop() {
        ElectronicsPage electronics = new ElectronicsPage(driver);
        electronics.openLaptop();
        Assert.assertTrue(driver.getWindowHandles().size() > 1);
    }
    
    @Test(priority=6, groups="smoke")
    public void verifySwitchWindow() {     
        WindowUtils window = new WindowUtils(driver);
        window.switchToChildWindow();
        ElectronicsPage electronics = new ElectronicsPage(driver);
        //electronics.clickBuyButton();
        Assert.assertTrue(driver.getTitle().length() > 0);
    }
}