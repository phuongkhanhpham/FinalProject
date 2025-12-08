package finalProject.keywords;

import com.aventstack.extentreports.Status;
import finalProject.drivers.DriverManager;
import finalProject.helpers.PropertiesHelper;
import finalProject.reports.ExtentTestManager;
import finalProject.utils.LogUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class WebUI {

    private static int TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT"));
    private static int STEP_TIME = Integer.parseInt(PropertiesHelper.getValue("STEP_TIME"));
    private static int PAGE_LOAD_TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("PAGE_LOAD_TIMEOUT"));

    public static void sleep(double second) {
        try {
            Thread.sleep((long) (1000 * second));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static void openURL(String url) {
        DriverManager.getDriver().get(url);
        sleep(STEP_TIME);
        LogUtils.info("\uD83C\uDF10 Open URL: " + url);
        ExtentTestManager.logMessage(Status.PASS, "\uD83C\uDF10 Open URL: " + url);
    }

    public static void waitForPageLoaded() {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(30), Duration.ofMillis(500));
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();

        //Wait for Javascript to load
        ExpectedCondition<Boolean> jsLoad = new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver driver) {
                return js.executeScript("return document.readyState").toString().equals("complete");
            }
        };
    }

    public static void setText(By by, String value) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(value);
        LogUtils.info("⌨\uFE0F Set text " + value + " on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "⌨\uFE0F Set text " + value + " on element " + by);
    }

    public static void waitForElementVisible(By by) {
        try {
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(TIMEOUT), Duration.ofMillis(500));
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (Throwable error) {
            LogUtils.info("⏰ Timeout waiting for the element Visible. " + by.toString());
            Assert.fail("⏰ Timeout waiting for the element Visible. " + by.toString());
        }
    }

    public static WebElement getWebElement(By by) {
        return DriverManager.getDriver().findElement(by);
    }

    public static void clickElement(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).click();
        LogUtils.info("\uD83D\uDDB1\uFE0F Click on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83D\uDDB1\uFE0F Click on element " + by);
    }

    public static String getCurrentURL() {
        return DriverManager.getDriver().getCurrentUrl();
    }

    public static void assertNotContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("❌ Assert NOT contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.INFO, "❌ Assert NOT contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertFalse(check, message);
    }

    public static void assertContains(String actual, String expected, String message) {
        waitForPageLoaded();
        LogUtils.info("✅ Assert contains: " + actual + " and " + expected);
        ExtentTestManager.logMessage(Status.INFO, "✅ Assert contains: " + actual + " and " + expected);
        boolean check = actual.contains(expected);
        Assert.assertTrue(check, message);
    }

    public static void clearText(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).clear();
        LogUtils.info("\uD83E\uDDF9 Clear text on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "\uD83E\uDDF9 Clear text on element " + by);
    }

    public static void resetInputField(By by) {
        waitForElementVisible(by);
        sleep(STEP_TIME);
        getWebElement(by).sendKeys(Keys.CONTROL + "a");
        getWebElement(by).sendKeys(Keys.DELETE);
        LogUtils.info("🔄 Reset input field (CTRL+A + DELETE) on element " + by);
        ExtentTestManager.logMessage(Status.PASS, "🔄 Reset input field on element " + by);
    }


    public static List<WebElement> getWebElements(By by) {
        return DriverManager.getDriver().findElements(by);
    }

    public static Boolean checkElementExist(By by) {
        List<WebElement> listElement = getWebElements(by);

        if (listElement.size() > 0) {
            LogUtils.info("checkElementExist: " + true + " --- " + by);
            return true;
        } else {
            LogUtils.info("checkElementExist: " + false + " --- " + by);
            return false;
        }
    }

    public static String getElementText(By by) {
        waitForElementVisible(by);
        LogUtils.info("Get text of element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Get text of element " + by);
        String text = getWebElement(by).getText();
        LogUtils.info("==> TEXT: " + text);
        ExtentTestManager.logMessage(Status.INFO, "==> TEXT: " + text);
        return text;
    }

    public static void assertEquals(Object actual, Object expected, String message) {
        waitForPageLoaded();
        LogUtils.info("Assert equals: " + actual + " \uD83D\uDFF0 " + expected);
        ExtentTestManager.logMessage(Status.INFO, "Assert equals: " + actual + " and " + expected);
        Assert.assertEquals(actual, expected, message);
    }

    public static String getElementAttribute(By by, String attributeName) {
        waitForElementVisible(by);
        LogUtils.info("Get attribute of element " + by);
        ExtentTestManager.logMessage(Status.PASS, "Get attribute of element " + by);
        String value = getWebElement(by).getAttribute(attributeName);
        LogUtils.info("==> Attribute value: " + value);
        ExtentTestManager.logMessage(Status.INFO, "==> Attribute value: " + value);
        return value;
    }

    public static void uploadFileWithRobotClass(By elementFileForm, String filePath) {
        //Click để mở form upload
        WebUI.clickElement(elementFileForm);
        WebUI.sleep(2);

        // Khởi tạo Robot class
        Robot rb = null;
        try {
            rb = new Robot();
        } catch (AWTException e) {
            e.printStackTrace();
        }

        // Copy File path vào Clipboard
        StringSelection str = new StringSelection(filePath);
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(str, null);

        // Nhấn Control+V để dán
        rb.keyPress(KeyEvent.VK_CONTROL);
        rb.keyPress(KeyEvent.VK_V);

        // Xác nhận Control V trên
        rb.keyRelease(KeyEvent.VK_CONTROL);
        rb.keyRelease(KeyEvent.VK_V);

        WebUI.sleep(1);

        // Nhấn Enter
        rb.keyPress(KeyEvent.VK_ENTER);
        rb.keyRelease(KeyEvent.VK_ENTER);

        WebUI.sleep(2);
    }
}
