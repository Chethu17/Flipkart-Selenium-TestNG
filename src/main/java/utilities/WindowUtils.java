package utilities;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.WebDriver;

public class WindowUtils {

    WebDriver driver;

    public WindowUtils(WebDriver driver) {
        this.driver = driver;
    }

    // Switch to child window and return parent id
    public String switchToChildWindow() {
        String parent = driver.getWindowHandle();
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
                break;
            }
        }
        return parent;
    }

    // Switch using index
    public void switchToWindow(int index) {
        List<String> windows = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(windows.get(index));
    }

    // Close current tab and return to parent
    public void closeCurrentTabAndSwitchToParent(String parent) {
        driver.close();
        driver.switchTo().window(parent);
    }

    // Close all child tabs and return parent
    public void closeAllChildWindows(String parent) {
        for (String win : driver.getWindowHandles()) {
            if (!win.equals(parent)) {
                driver.switchTo().window(win);
                driver.close();
            }
        }
        driver.switchTo().window(parent);
    }

    // Get current tab title
    public String getCurrentWindowTitle() {
        return driver.getTitle();
    }

    // Get total tab count
    public int getWindowCount() {
        return driver.getWindowHandles().size();
    }
}