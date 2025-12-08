package finalProject.pages;

import finalProject.helpers.SystemHelper;
import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class JobTitlesPage {

    private By headerJobTitles = By.xpath("//h6[normalize-space()='Job Titles']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By inputJobTitle = By.xpath("//label[normalize-space()='Job Title']/parent::div/following-sibling::div/input");
    private By inputJobDescription = By.xpath("//label[normalize-space()='Job Description']/parent::div/following-sibling::div/textarea");
    private By buttonFileUpload = By.xpath("//div[normalize-space()='Browse']");
    private By labelFileName = By.xpath("//div[@class='oxd-file-input-div']");
    private By inputAddNote = By.xpath("//textarea[@placeholder='Add note']");
    private By buttonSave = By.xpath("//button[@type='submit'][normalize-space()='Save']");
    private By alertAddSuccess = By.xpath("//p[normalize-space()='Successfully Saved']");

    public void verifyNavigateToJobTitlesPage() {
        WebUI.waitForElementVisible(headerJobTitles);
        Assert.assertTrue(WebUI.checkElementExist(headerJobTitles), "The Job Titles header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerJobTitles), "Job Titles", "The Job Titles header is not matched.");
    }

    public void clickButtonAddNewJobTitle() {
        WebUI.clickElement(buttonAdd);
    }

    public void submitDataForNewJobTitle(String jobTitle, String jobDescription, String note) {
        String filePath = SystemHelper.getCurrentDir() + "src\\test\\resources\\testdata\\jobSpecification.pdf";
        WebUI.setText(inputJobTitle, jobTitle);
        WebUI.setText(inputJobDescription, jobDescription);
        WebUI.uploadFileWithRobotClass(buttonFileUpload, filePath);
        Assert.assertEquals(WebUI.getElementText(labelFileName), "jobSpecification.pdf", "The file name is not matched.");
        WebUI.setText(inputAddNote, note);
        WebUI.clickElement(buttonSave);
    }

    public void verifyAddSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertAddSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertAddSuccess), "The add success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertAddSuccess), "Successfully Saved", "The add success alert is not matched.");
    }
}
