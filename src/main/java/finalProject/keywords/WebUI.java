package finalProject.keywords;

import com.aventstack.extentreports.Status;
import finalProject.drivers.DriverManager;
import finalProject.helpers.PropertiesHelper;
import finalProject.reports.ExtentTestManager;
import finalProject.utils.LogUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class WebUI {

    private static int TIMEOUT = Integer.parseInt(PropertiesHelper.getValue("EXPLICIT_WAIT"));
    private static double STEP_TIME = 0;
    private static int PAGE_LOAD_TIMEOUT = 30;

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
}
