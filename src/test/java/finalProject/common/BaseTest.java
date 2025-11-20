package finalProject.common;

import finalProject.drivers.DriverManager;
import finalProject.helpers.CaptureHelper;
import finalProject.helpers.PropertiesHelper;
import finalProject.listeners.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.annotations.*;

import java.time.Duration;

@Listeners(TestListener.class)
public class BaseTest {

    @BeforeMethod
    @Parameters({"browser"})
    public void createDriver(@Optional("chrome") String browser) {
        WebDriver driver;

        if (PropertiesHelper.getValue("BROWSER").isEmpty() && PropertiesHelper.getValue("BROWSER").isBlank()) {
            browser = browser;
        } else {
            browser = PropertiesHelper.getValue("BROWSER");
        }

        switch (browser) {
            case "chrome":
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt Chrome.");
                break;
            case "firefox":
                driver = new FirefoxDriver();
                System.out.println("Khởi chạy trình duyệt Firefox.");
                break;
            case "safari":
                driver = new SafariDriver();
                System.out.println("Khởi chạy trình duyệt Safari.");
                break;
            default:
                driver = new ChromeDriver();
                System.out.println("Khởi chạy trình duyệt Chrome mặc định.");
                break;
        }

        DriverManager.setDriver(driver);

        DriverManager.getDriver().manage().window().maximize();
        DriverManager.getDriver().manage().timeouts().pageLoadTimeout(Duration.ofSeconds(60));
    }

    @AfterMethod
    public void closeDriver() {

        CaptureHelper.stopRecord();

        if (DriverManager.getDriver() != null) {
            DriverManager.quit();
        }
    }
}
