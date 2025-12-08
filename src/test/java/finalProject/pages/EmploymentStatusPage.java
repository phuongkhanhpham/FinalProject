package finalProject.pages;

import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class EmploymentStatusPage {

    private By headerEmploymentStatus = By.xpath("//h6[normalize-space()='Employment Status']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By alertAddSuccess = By.xpath("//p[normalize-space()='Successfully Saved']");
    private By inputName = By.xpath("//label[normalize-space()='Name']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[@type='submit'][normalize-space()='Save']");

    public void verifyNavigateToEmploymentStatusPage() {
        WebUI.waitForElementVisible(headerEmploymentStatus);
        Assert.assertTrue(WebUI.checkElementExist(headerEmploymentStatus), "The Employment Status header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerEmploymentStatus), "Employment Status", "The Employment Status header is not matched.");
    }

    public void clickButtonAddNewEmploymentStatus() {
        WebUI.clickElement(buttonAdd);
    }

    public void submitDataForNewEmploymentStatus(String employmentStatus) {
        WebUI.setText(inputName, employmentStatus);
        WebUI.clickElement(buttonSave);
    }

    public void verifyAddSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertAddSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertAddSuccess), "The add success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertAddSuccess), "Successfully Saved", "The add success alert is not matched.");
    }
}
