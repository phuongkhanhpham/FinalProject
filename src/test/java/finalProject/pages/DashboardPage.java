package finalProject.pages;

import finalProject.keywords.WebUI;
import org.openqa.selenium.By;

public class DashboardPage {

    private By menuAdmin = By.xpath("//span[normalize-space()='Admin']");
    private By menuPim = By.xpath("//span[normalize-space()='PIM']");

    public AdminPage clickMenuAdmin() {
        WebUI.waitForElementVisible(menuAdmin);
        WebUI.clickElement(menuAdmin);
        WebUI.waitForPageLoaded();
        return new AdminPage();
    }

    public PimPage clickMenuPim() {
        WebUI.waitForElementVisible(menuPim);
        WebUI.clickElement(menuPim);
        WebUI.waitForPageLoaded();
        return new PimPage();
    }
}
