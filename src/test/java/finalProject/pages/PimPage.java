package finalProject.pages;

import finalProject.helpers.SystemHelper;
import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class PimPage {

    private By headerEmployee = By.xpath("//h5[normalize-space()='Employee Information']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By inputFirstName = By.xpath("//input[@name='firstName']");
    private By inputMiddleName = By.xpath("//input[@name='middleName']");
    private By inputLastName = By.xpath("//input[@name='lastName']");
    private By inputEmployeeId = By.xpath("//label[normalize-space()='Employee Id']/parent::div/following-sibling::div/input");
    private By buttonFileUpload = By.xpath("//input[@type='file']/parent::div//button");
    private By toggleCreateLoginDetails = By.xpath("//input[@type='checkbox']/following-sibling::span");
    private By inputUsername = By.xpath("//label[normalize-space()='Username']/parent::div/following-sibling::div/input");
    private By inputPassword = By.xpath("//label[normalize-space()='Password']/parent::div/following-sibling::div/input");
    private By inputConfirmPassword = By.xpath("//label[normalize-space()='Confirm Password']/parent::div/following-sibling::div/input");
    private By buttonSave = By.xpath("//button[@type='submit'][normalize-space()='Save']");
    private By alertAddSuccess = By.xpath("//p[normalize-space()='Successfully Saved']");
    private By headerPersonalDetails = By.xpath("//h6[normalize-space()='Personal Details']");
    private By alertUpdateSuccess = By.xpath("//p[normalize-space()='Successfully Updated']");
    private By inputOtherId = By.xpath("//label[normalize-space()='Other Id']/parent::div/following-sibling::div/input");
    private By inputDriverLicenseNo = By.xpath("//label[normalize-space()=\"Driver's License Number\"]/parent::div/following-sibling::div//input");
    private By inputExpiryDate = By.xpath("//label[normalize-space()='License Expiry Date']/parent::div/following-sibling::div//input");
    private By dropdownNationality = By.xpath("//label[normalize-space()='Nationality']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By optionVietNam = By.xpath("//div[@role='option'][normalize-space()='Vietnamese']");
    private By dropdownMaritalStatus = By.xpath("//label[normalize-space()='Marital Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By optionSingle = By.xpath("//div[@role='option'][normalize-space()='Single']");
    private By inputDateOfBirth = By.xpath("//label[normalize-space()='Date of Birth']/parent::div/following-sibling::div//input");
    private By radioButtonFemale = By.xpath("//label[normalize-space()='Female']/span");
    private By inputMilitaryService = By.xpath("//label[normalize-space()='Military Service']/parent::div/following-sibling::div/input");
    private By buttonSavePersonalDetails = By.xpath("//h6[normalize-space()='Personal Details']/following-sibling::form//button[normalize-space()='Save']");
    private By buttonSaveJobDetails = By.xpath("//h6[normalize-space()='Job Details']/following-sibling::form//button[normalize-space()='Save']");
    private By tabJob = By.xpath("//a[normalize-space()='Job']");
    private By inputJoinedDate = By.xpath("//label[normalize-space()='Joined Date']/parent::div/following-sibling::div//input");
    private By dropdownJobTitle = By.xpath("//label[normalize-space()='Job Title']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By dropdownJobCategory = By.xpath("//label[normalize-space()='Job Category']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By dropdownLocation = By.xpath("//label[normalize-space()='Location']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By dropdownEmploymentStatus = By.xpath("//label[normalize-space()='Employment Status']/parent::div/following-sibling::div//div[@class='oxd-select-text-input']");
    private By menuEmployeeList = By.xpath("//a[normalize-space()='Employee List']");
    private By buttonSearch = By.xpath("//div[@class='oxd-table-filter']//button[normalize-space()='Search']");
    private By recordCount = By.xpath("//span[contains(normalize-space(),'Record Found')]");
    private By cellId = By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][2]");
    private By cellFirstAndMiddleName = By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][3]");
    private By cellLastName = By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][4]");
    private By cellJobTitle = By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][5]");
    private By cellEmploymentStatus = By.xpath("//div[@class='oxd-table-body']//div[@role='cell'][6]");

    public void verifyNavigateToPimPage() {
        WebUI.waitForElementVisible(headerEmployee);
        Assert.assertTrue(WebUI.checkElementExist(headerEmployee), "The Employee header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerEmployee), "Employee Information", "The Employee header is not matched.");
    }

    public void clickButtonAddNewUser() {
        WebUI.clickElement(buttonAdd);
    }

    public void submitDataForNewEmployee(String firstName, String middleName, String lastName, String id, String username, String password) {
        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\profile.png";
        WebUI.uploadFileWithRobotClass(buttonFileUpload, filePath);
        WebUI.setText(inputFirstName, firstName);
        WebUI.setText(inputMiddleName, middleName);
        WebUI.setText(inputLastName, lastName);
        WebUI.resetInputField(inputEmployeeId);
        WebUI.setText(inputEmployeeId, id);
        WebUI.clickElement(toggleCreateLoginDetails);
        WebUI.setText(inputUsername, username);
        WebUI.setText(inputPassword, password);
        WebUI.setText(inputConfirmPassword, password);
        WebUI.clickElement(buttonSave);
    }

    public void checkAddNewEmployeeSuccess(String firstName, String middleName, String lastName, String id) {
        WebUI.waitForElementVisible(alertAddSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertAddSuccess), "The add success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertAddSuccess), "Successfully Saved", "The add success alert is not matched.");

        WebUI.waitForElementVisible(inputFirstName);
        WebUI.assertEquals(WebUI.getElementText(By.xpath("//h6[normalize-space()='" + firstName + " " + lastName + "']")), firstName + " " + lastName, "The employee name is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputFirstName, "value"), firstName, "The first name is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputMiddleName, "value"), middleName, "The middle name is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputLastName, "value"), lastName, "The last name is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputEmployeeId, "value"), id, "The id is not matched.");
    }

    public void updatePersonalDetails(String otherId, String driverNo, String expiryDate, String dateOfBirth) {
        WebUI.setText(inputOtherId, otherId);
        WebUI.setText(inputDriverLicenseNo, driverNo);
        WebUI.setText(inputExpiryDate, expiryDate);
        WebUI.clickElement(dropdownNationality);
        WebUI.clickElement(optionVietNam);
        WebUI.clickElement(dropdownMaritalStatus);
        WebUI.clickElement(optionSingle);
        WebUI.setText(inputDateOfBirth, dateOfBirth);
        WebUI.clickElement(radioButtonFemale);
        WebUI.clickElement(buttonSavePersonalDetails);
    }

    public void checkUpdatePersonalDetailsSuccess(String otherId, String driverNo, String expiryDate, String dateOfBirth) {
        WebUI.waitForElementVisible(alertUpdateSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertUpdateSuccess), "The update success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertUpdateSuccess), "Successfully Updated", "The update success alert is not matched.");

        WebUI.waitForElementVisible(inputFirstName);
        WebUI.assertEquals(WebUI.getElementAttribute(inputOtherId, "value"), otherId, "The other Id is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputDriverLicenseNo, "value"), driverNo, "The driver license number is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputExpiryDate, "value"), expiryDate, "The Expiry Date is not matched.");
        WebUI.assertEquals(WebUI.getElementText(dropdownNationality), "Vietnamese", "The Nationality is not matched.");
        WebUI.assertEquals(WebUI.getElementText(dropdownMaritalStatus), "Single", "The Marital Status is not matched.");
        WebUI.assertEquals(WebUI.getElementAttribute(inputDateOfBirth, "value"), dateOfBirth, "The date of birth is not matched.");
        Assert.assertTrue(WebUI.getWebElement(By.xpath("//label[normalize-space()='Female']/input")).isSelected(), "The female option is not selected.");
    }

    public void clickTabJob() {
        WebUI.clickElement(tabJob);
    }

    public void updateJobDetails(String joinedDate, String jobTitle, String jobCategory, String location, String employmentStatus) {
        WebUI.setText(inputJoinedDate, joinedDate);
        WebUI.clickElement(dropdownJobTitle);
        WebUI.clickElement(By.xpath("//div[@role='option'][normalize-space()='" + jobTitle + "']"));
        WebUI.clickElement(dropdownJobCategory);
        WebUI.clickElement(By.xpath("//div[@role='option'][normalize-space()='" + jobCategory + "']"));
        WebUI.clickElement(dropdownLocation);
        WebUI.clickElement(By.xpath("//div[@role='option'][normalize-space()='" + location + "']"));
        WebUI.clickElement(dropdownEmploymentStatus);
        WebUI.clickElement(By.xpath("//div[@role='option'][normalize-space()='" + employmentStatus + "']"));
        WebUI.clickElement(buttonSaveJobDetails);
    }

    public void checkUpdateJobDetailsSuccess(String joinedDate, String jobTitle, String jobCategory, String location, String employmentStatus) {
        WebUI.waitForElementVisible(alertUpdateSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertUpdateSuccess), "The update success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertUpdateSuccess), "Successfully Updated", "The update success alert is not matched.");

        WebUI.waitForElementVisible(inputJoinedDate);
        WebUI.assertEquals(WebUI.getElementAttribute(inputJoinedDate, "value"), joinedDate, "The joined date is not matched.");
        WebUI.assertEquals(WebUI.getElementText(dropdownJobTitle), jobTitle, "The job title is not matched.");
        WebUI.assertEquals(WebUI.getElementText(dropdownJobCategory), jobCategory, "The Job Category is not matched.");
        WebUI.assertEquals(WebUI.getElementText(dropdownLocation), location, "The location is not matched.");
        WebUI.assertEquals(WebUI.getElementText(dropdownEmploymentStatus), employmentStatus, "The employment Status is not matched.");
    }

    public void clickMenuEmployeeList() {
        WebUI.waitForElementVisible(menuEmployeeList);
        WebUI.clickElement(menuEmployeeList);
        WebUI.waitForPageLoaded();
    }

    public void searchAndCheckInTable(String id, String firstAndMiddleName, String lastName, String jobTitle, String employmentStatus) {
        WebUI.setText(inputEmployeeId, id);
        WebUI.clickElement(buttonSearch);
        WebUI.waitForElementVisible(recordCount);
        WebUI.assertEquals(WebUI.getElementText(cellId), id, "The id in the table is not matched.");
        WebUI.assertEquals(WebUI.getElementText(cellFirstAndMiddleName), firstAndMiddleName, "The first and middle name in the table is not matched.");
        WebUI.assertEquals(WebUI.getElementText(cellLastName), lastName, "The last name in the table is not matched.");
        WebUI.assertEquals(WebUI.getElementText(cellJobTitle), jobTitle, "The job title in the table is not matched.");
        WebUI.assertEquals(WebUI.getElementText(cellEmploymentStatus), employmentStatus, "The employment Status in the table is not matched.");
    }
}
