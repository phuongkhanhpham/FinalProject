package finalProject.pages;

import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class AdminPage {

    private By menuJob = By.xpath("//span[normalize-space()='Job']");
    private By menuJobTitles = By.xpath("//a[normalize-space()='Job Titles']");
    private By menuJobCategories = By.xpath("//a[normalize-space()='Job Categories']");
    private By menuEmploymentStatus = By.xpath("//a[normalize-space()='Employment Status']");
    private By menuOrganization = By.xpath("//span[normalize-space()='Organization']");
    private By menuLocations = By.xpath("//a[normalize-space()='Locations']");
    private By headerAdmin = By.xpath("//h6[normalize-space()='Admin']");
    private By headerUserManagement = By.xpath("//h6[normalize-space()='User Management']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By dropdownUserRole = By.xpath("//label[normalize-space()='User Role']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By optionAdmin = By.xpath("//div[@role='option'][normalize-space()='Admin']");
    private By optionEss = By.xpath("//div[@role='option'][normalize-space()='ESS']");
    private By inputEmployeeName = By.xpath("//input[@placeholder='Type for hints...']");
    private By firstItemEmployeeName = By.xpath("//input[@placeholder='Type for hints...']/parent::div/following-sibling::div/div[@role='option'][1]");
    private By dropdownStatus = By.xpath("//label[normalize-space()='Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By optionEnabled = By.xpath("//div[@role='option'][normalize-space()='Enabled']");
    private By optionDisabled = By.xpath("//div[@role='option'][normalize-space()='Disabled']");
    private By inputUsername = By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div/input");
    private By inputPassword = By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::div/input");
    private By inputConfirmPassword = By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[@type='submit'][normalize-space()='Save']");
    private By alertAddSuccess = By.xpath("//p[normalize-space()='Successfully Saved']");
    private By buttonSearch = By.xpath("//button[normalize-space()='Search']");
    private By firstItemUsername = By.xpath("//div[@class='oxd-table-body']/div[1]//div[@role='cell'][2]/div");
    private By firstButtonEdit = By.xpath("//div[@class='oxd-table-body']/div[1]//div[@role='cell'][6]//i[@class='oxd-icon bi-pencil-fill']");
    private By firstButtonDelete = By.xpath("//div[@class='oxd-table-body']/div[1]//div[@role='cell'][6]//i[@class='oxd-icon bi-trash']");
    private By buttonConfirmDelete = By.xpath("//button[normalize-space()='Yes, Delete']");
    private By recordsCount = By.xpath("//span[contains(normalize-space(),'Records Found')]");
    private By alertDeleteSuccess = By.xpath("//p[normalize-space()='Successfully Deleted']");
    private By checkboxChangePassword = By.xpath("//input[@type='checkbox']/following-sibling::span");
    private By alertUpdateSuccess = By.xpath("//p[normalize-space()='Successfully Updated']");

    public void verifyNavigateToAdminPage() {
        WebUI.waitForElementVisible(headerAdmin);
        Assert.assertTrue(WebUI.checkElementExist(headerAdmin), "The Admin header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerAdmin), "Admin", "The Admin header is not matched.");
        Assert.assertTrue(WebUI.checkElementExist(headerUserManagement), "The User Management header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerUserManagement), "User Management", "The User Management header is not matched.");
    }

    public void clickButtonAddNewUser() {
        WebUI.clickElement(buttonAdd);
    }

    public void submitDataForNewUser(String employeeName, String username, String password) {
        WebUI.clickElement(dropdownUserRole);
        WebUI.clickElement(optionAdmin);
        WebUI.setText(inputEmployeeName, employeeName);
        WebUI.sleep(2);
        WebUI.clickElement(firstItemEmployeeName);
        WebUI.clickElement(dropdownStatus);
        WebUI.clickElement(optionEnabled);
        WebUI.setText(inputUsername, username);
        WebUI.setText(inputPassword, password);
        WebUI.setText(inputConfirmPassword, password);
        WebUI.clickElement(buttonSave);
    }

    public void verifyAddSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertAddSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertAddSuccess), "The add success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertAddSuccess), "Successfully Saved", "The add success alert is not matched.");
    }

    public void searchAndCheckUserDetail(String username) {
        WebUI.waitForElementVisible(recordsCount);
        WebUI.setText(inputUsername, username);
        WebUI.clickElement(buttonSearch);
        String usernameInTable = WebUI.getElementText(firstItemUsername);
        WebUI.assertEquals(usernameInTable, username, "The username in table is not matched.");
        WebUI.clickElement(firstButtonEdit);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
        Assert.assertEquals(WebUI.getElementText(dropdownUserRole), "Admin", "The user role is not matched.");
        Assert.assertEquals(WebUI.getElementAttribute(inputEmployeeName, "value"), "Orange  Test", "The user role is not matched.");
        Assert.assertEquals(WebUI.getElementText(dropdownStatus), "Enabled", "The status is not matched.");
        Assert.assertEquals(WebUI.getElementAttribute(inputUsername, "value"), username, "The username is not matched.");
    }

    public void deleteUser(String username) {
        WebUI.waitForElementVisible(recordsCount);
        WebUI.setText(inputUsername, username);
        WebUI.clickElement(buttonSearch);
        String usernameInTable = WebUI.getElementText(firstItemUsername);
        WebUI.assertEquals(usernameInTable, username, "The username in table is not matched.");
        WebUI.clickElement(firstButtonDelete);
        WebUI.clickElement(buttonConfirmDelete);
    }

    public void verifyDeleteSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertDeleteSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertDeleteSuccess), "The delete success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertDeleteSuccess), "Successfully Deleted", "The delete success alert is not matched.");
    }

    public void searchAndCheckDeletedUser(String username) {
        WebUI.waitForElementVisible(recordsCount);
        WebUI.setText(inputUsername, username);
        WebUI.clickElement(buttonSearch);
        Assert.assertEquals(WebUI.getElementText(recordsCount), "No Records Found", "The record number is incorrect.");
        Assert.assertFalse(WebUI.checkElementExist(firstItemUsername), "The user is not deleted successfully.");
    }

    public void updateUser(String employeeName, String username, String password) {
        WebUI.clickElement(dropdownUserRole);
        WebUI.clickElement(optionEss);
        WebUI.resetInputField(inputEmployeeName);
        WebUI.setText(inputEmployeeName, employeeName);
        WebUI.sleep(3);
        WebUI.clickElement(firstItemEmployeeName);
        WebUI.clickElement(dropdownStatus);
        WebUI.clickElement(optionDisabled);
        WebUI.resetInputField(inputUsername);
        WebUI.setText(inputUsername, username);
        WebUI.clickElement(checkboxChangePassword);
        WebUI.setText(inputPassword, password);
        WebUI.setText(inputConfirmPassword, password);
        WebUI.clickElement(buttonSave);
    }

    public void verifyUpdateSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertUpdateSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertUpdateSuccess), "The update success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertUpdateSuccess), "Successfully Updated", "The update success alert is not matched.");
    }

    public void searchAndCheckUpdatedUser(String username) {
        WebUI.waitForElementVisible(recordsCount);
        WebUI.setText(inputUsername, username);
        WebUI.clickElement(buttonSearch);
        String usernameInTable = WebUI.getElementText(firstItemUsername);
        WebUI.assertEquals(usernameInTable, username, "The username in table is not matched.");
        WebUI.clickElement(firstButtonEdit);
        WebUI.waitForPageLoaded();
        WebUI.sleep(1);
        Assert.assertEquals(WebUI.getElementText(dropdownUserRole), "ESS", "The user role is not matched.");
        Assert.assertEquals(WebUI.getElementAttribute(inputEmployeeName, "value"), "Timothy Lewis Amiano", "The user role is not matched.");
        Assert.assertEquals(WebUI.getElementText(dropdownStatus), "Disabled", "The status is not matched.");
        Assert.assertEquals(WebUI.getElementAttribute(inputUsername, "value"), username, "The username is not matched.");
    }

    public JobTitlesPage clickMenuJobTitles() {
        WebUI.waitForElementVisible(menuJob);
        WebUI.clickElement(menuJob);
        WebUI.waitForElementVisible(menuJobTitles);
        WebUI.clickElement(menuJobTitles);
        WebUI.waitForPageLoaded();
        return new JobTitlesPage();
    }

    public JobCategoriesPage clickMenuJobCategories() {
        WebUI.waitForElementVisible(menuJob);
        WebUI.clickElement(menuJob);
        WebUI.waitForElementVisible(menuJobCategories);
        WebUI.clickElement(menuJobCategories);
        WebUI.waitForPageLoaded();
        return new JobCategoriesPage();
    }

    public EmploymentStatusPage clickMenuEmploymentStatus() {
        WebUI.waitForElementVisible(menuJob);
        WebUI.clickElement(menuJob);
        WebUI.waitForElementVisible(menuEmploymentStatus);
        WebUI.clickElement(menuEmploymentStatus);
        WebUI.waitForPageLoaded();
        return new EmploymentStatusPage();
    }

    public LocationsPage clickMenuLocations() {
        WebUI.waitForElementVisible(menuOrganization);
        WebUI.clickElement(menuOrganization);
        WebUI.waitForElementVisible(menuLocations);
        WebUI.clickElement(menuLocations);
        WebUI.waitForPageLoaded();
        return new LocationsPage();
    }
}
