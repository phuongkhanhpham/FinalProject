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

    public void login(String username, String password) {
        WebUI.openURL(PropertiesHelper.getValue("URL"));
        WebUI.waitForPageLoaded();
        WebUI.setText(inputUsername, username);
        WebUI.setText(inputPassword, password);
        WebUI.clickElement(buttonLogin);
        WebUI.waitForPageLoaded();
    }

    public void verifyLoginSuccess() {
        WebUI.assertNotContains(DriverManager.getDriver().getCurrentUrl(), "login", "User is still in Login page");
        WebUI.assertContains(DriverManager.getDriver().getCurrentUrl(), "dashboard", "User is still in Login page");
    }
}
