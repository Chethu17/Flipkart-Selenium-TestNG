package utilities;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import base.BaseTest;

public class TestListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object currentClass = result.getInstance();
        WebDriver driver =((BaseTest) currentClass).driver;
        try {
            ScreenshotUtils.takeScreenshot(driver, result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}