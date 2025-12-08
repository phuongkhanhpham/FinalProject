package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.AdminPage;
import finalProject.pages.DashboardPage;
import finalProject.pages.LocationsPage;
import finalProject.pages.LoginPage;
import org.testng.annotations.Test;

public class LocationsTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AdminPage adminPage;
    LocationsPage locationsPage;

    @Test
    public void testAddNewLocation() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Locations");
        ExcelHelper excelHelper_1 = new ExcelHelper();
        excelHelper_1.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Employees");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();
        locationsPage = adminPage.clickMenuLocations();

        locationsPage.verifyNavigateToLocationsPage();
        locationsPage.clickButtonAddNewLocation();
        locationsPage.submitDataForNewLocation(
                excelHelper.getCellData("Location", 1),
                excelHelper.getCellData("City", 1),
                excelHelper.getCellData("State/Province", 1),
                excelHelper.getCellData("Zip/Postal Code", 1),
                excelHelper.getCellData("Phone", 1),
                excelHelper.getCellData("Fax", 1),
                excelHelper.getCellData("Address", 1),
                excelHelper.getCellData("Note", 1));
        locationsPage.verifyAddSuccessAlertVisible();
        excelHelper_1.setCellData(excelHelper.getCellData("Location", 1), "Location", 1);
    }
}
