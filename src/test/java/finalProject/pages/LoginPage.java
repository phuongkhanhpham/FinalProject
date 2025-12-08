package finalProject.pages;

import finalProject.drivers.DriverManager;
import finalProject.helpers.PropertiesHelper;
import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LoginPage {

    private By inputUsername = By.xpath("//input[@name='username']");
    private By inputPassword = By.xpath("//input[@name='password']");
    private By buttonLogin = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//p[normalize-space()='Invalid credentials']");

    public void login(String username, String password) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputUsername);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputUsername, username);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
    }

    public void verifyLoginSuccess() {
        WebUI.assertNotContains(DriverManager.getDriver().getCurrentUrl(), "login", "User is still in Login page");
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "dashboard", "User is still in Login page");
    }

    public DashboardPage loginHRM() {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.clearText(inputUsername);
        WebUI.clearText(inputPassword);
        WebUI.setText(inputUsername, "Admin");
        WebUI.setText(inputPassword, "admin123");
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);

        verifyLoginSuccess();

        return new DashboardPage();
    }

    public void verifyLoginFailed() {
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "login", "User is navigated to another page");
        WebUI.waitForElementVisible(errorMessage);
        Assert.assertTrue(WebUI.checkElementExist(errorMessage), "The error message is not displayed.");
        Assert.assertEquals(WebUI.getElementText(errorMessage), "Invalid credentials", "The error message is not matched.");
    }
}
