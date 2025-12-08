package finalProject.pages;

import finalProject.keywords.WebUI;
import org.openqa.selenium.By;
import org.testng.Assert;

public class LocationsPage {

    private By headerLocations = By.xpath("//h5[normalize-space()='Locations']");
    private By buttonAdd = By.xpath("//button[normalize-space()='Add']");
    private By buttonSave = By.xpath("//button[@type='submit'][normalize-space()='Save']");
    private By alertAddSuccess = By.xpath("//p[normalize-space()='Successfully Saved']");
    private By inputName = By.xpath("//label[normalize-space()='Name']/parent::div/following-sibling::div/input");
    private By inputCity = By.xpath("//label[normalize-space()='City']/parent::div/following-sibling::div/input");
    private By inputStateProvince = By.xpath("//label[normalize-space()='State/Province']/parent::div/following-sibling::div/input");
    private By inputZipPostalCode = By.xpath("//label[normalize-space()='Zip/Postal Code']/parent::div/following-sibling::div/input");
    private By dropdownCountry = By.xpath("//div[@class='oxd-select-text-input'][normalize-space()='-- Select --']");
    private By optionVietNam = By.xpath("//div[@role='option'][normalize-space()='Viet Nam']");
    private By inputPhone = By.xpath("//label[normalize-space()='Phone']/parent::div/following-sibling::div/input");
    private By inputFax = By.xpath("//label[normalize-space()='Fax']/parent::div/following-sibling::div/input");
    private By inputAddress = By.xpath("//label[normalize-space()='Address']/parent::div/following-sibling::div/textarea");
    private By inputNotes = By.xpath("//label[normalize-space()='Notes']/parent::div/following-sibling::div/textarea");

    public void verifyNavigateToLocationsPage() {
        WebUI.waitForElementVisible(headerLocations);
        Assert.assertTrue(WebUI.checkElementExist(headerLocations), "The Locations header is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(headerLocations), "Locations", "The Locations header is not matched.");
    }

    public void clickButtonAddNewLocation() {
        WebUI.clickElement(buttonAdd);
    }

    public void submitDataForNewLocation(String location, String city, String stateProvince, String zipPostalCode, String phone, String fax, String address, String note) {
        WebUI.setText(inputName, location);
        WebUI.setText(inputCity, city);
        WebUI.setText(inputStateProvince, stateProvince);
        WebUI.setText(inputZipPostalCode, zipPostalCode);
        WebUI.clickElement(dropdownCountry);
        WebUI.clickElement(optionVietNam);
        WebUI.setText(inputPhone, phone);
        WebUI.setText(inputFax, fax);
        WebUI.setText(inputAddress, address);
        WebUI.setText(inputNotes, note);
        WebUI.clickElement(buttonSave);
    }

    public void verifyAddSuccessAlertVisible() {
        WebUI.waitForElementVisible(alertAddSuccess);
        Assert.assertTrue(WebUI.checkElementExist(alertAddSuccess), "The add success alert is not displayed.");
        WebUI.assertEquals(WebUI.getElementText(alertAddSuccess), "Successfully Saved", "The add success alert is not matched.");
    }
}
