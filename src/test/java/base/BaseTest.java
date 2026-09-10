package base;

import java.io.File;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
//import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import utilities.ConfigReader;
import utilities.TestListener;

@Listeners(TestListener.class)
public class BaseTest {

    public WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setup() {
        
    	ChromeOptions options = new ChromeOptions();
        //options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        //options.addArguments("--disable-gpu");
        options.addArguments("--window-size=1920,1080");

        options.setPageLoadStrategy(PageLoadStrategy.EAGER);
        
    	driver = new ChromeDriver(options);    
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(ConfigReader.getProperty("implicitWait"))));
        driver.get(ConfigReader.getProperty("url"));

        try {
            driver.findElement(By.xpath("//span[@role='button']")).click();
        } catch (Exception e) {
            System.out.println("Popup not present");
        }
        
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
    
    @BeforeSuite
    public void clearOldScreenshots() {
        File folder = new File("screenshots");
        File[] files = folder.listFiles();
        if (files != null) {
            for (File file : files) {
                file.delete();
            }
        }
    }
    
}