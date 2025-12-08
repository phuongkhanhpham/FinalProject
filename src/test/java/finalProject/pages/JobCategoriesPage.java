package finalProject.pages;

import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class JobCategoriesPage {

    private By headerJobCategories = By.xpath("//h6[normalize-space()='Job Categories']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By alertAddSuccess = By.xpath("//p[normalize-space()='Successfully Saved']");
    private By inputName = By.xpath("//label[normalize-space()='Name']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[@type='submit'][normalize-space()='Save']");

    public void verifyNavigateToJobCategoriesPage() {
        WebUI.waitForElementVisible(headerJobCategories);
        Assert.assertTrue(WebUI.checkElementExist(headerJobCategories), "The Job Categories header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerJobCategories), "Job Categories", "The Job Categories header is not matched.");
    }

    public void clickButtonAddNewJobCategories() {
        WebUI.clickElement(buttonAdd);
    }

    public void submitDataForNewJobCategories(String jobCategory) {
        WebUI.setText(inputName, jobCategory);
        WebUI.clickElement(buttonSave);
    }

    public void verifyAddSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertAddSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertAddSuccess), "The add success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertAddSuccess), "Successfully Saved", "The add success alert is not matched.");
    }
}
