package utilities;


import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
    	 Object currentClass = result.getInstance();
    	    WebDriver driver = ((BaseTest) currentClass).driver;

    	    if (driver == null) {
    	        System.out.println("Driver is null. Screenshot skipped.");
    	        return;
    	    }

    	    try {
    	        ScreenshotUtils.takeScreenshot(driver, result.getName());
    	    } catch (Exception e) {
    	        System.out.println("Screenshot could not be taken: " + e.getMessage());
    	    }
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}