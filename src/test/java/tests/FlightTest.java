package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.FlightPage;

public class FlightTest extends BaseTest {


    @Test(priority=1, groups="smoke")
    public void verifyFlightSearch() {       
    	FlightPage flight = new FlightPage(driver);
        flight.openTravelSection();
        WebElement flightButton = driver.findElement(By.xpath("(//div[@role='tab'])[1]"));
        Assert.assertTrue(flightButton.isDisplayed());
    }
    
    @Test(priority=2, groups="smoke")
    public void verifySourceSelected() {
    	FlightPage flight = new FlightPage(driver);
    	String selectedSource = flight.selectSource("Mu","Mumbai");
    	Assert.assertTrue(selectedSource.contains("Mumbai"));
    }
    
    @Test(priority=3, groups="smoke")
    public void verifyDestinationSelected() {
    	FlightPage flight = new FlightPage(driver);
    	String selectedDestination = flight.selectDestination("Be","Bengaluru");
    	Assert.assertTrue(selectedDestination.contains("Bengaluru"));
    }
    
     
    @Test(priority=4, groups="smoke")
    @Parameters({ "day", "monthYear" }) 
    public void verifyDateSelected(String day, String monthYear) {       
    	FlightPage flight = new FlightPage(driver);
        flight.selectDepartureDate(day, monthYear);
    }
    
    @Test(priority=5, groups="smoke")
    public void verifySearchResults() {       
    	FlightPage flight = new FlightPage(driver);
        flight.clickSearch();
        Assert.assertTrue(driver.getCurrentUrl().contains("travel"));
    }
    
}