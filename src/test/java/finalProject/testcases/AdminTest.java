package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.AdminPage;
import finalProject.pages.DashboardPage;
import finalProject.pages.LoginPage;
import org.testng.annotations.Test;

import java.util.Hashtable;

public class AdminTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AdminPage adminPage;

    @Test
    public void testAddNewUser() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "User");
        ExcelHelper excelHelper_1 = new ExcelHelper();
        excelHelper_1.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Login");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();

        adminPage.verifyNavigateToAdminPage();
        adminPage.clickButtonAddNewUser();
        adminPage.submitDataForNewUser(
                excelHelper.getCellData("Employee_Name", 1),
                excelHelper.getCellData("Username", 1),
                excelHelper.getCellData("Password", 1));
        adminPage.verifyAddSuccessAlertVisible();
        adminPage.searchAndCheckUserDetail(excelHelper.getCellData("Username", 1));
        excelHelper_1.setCellData(excelHelper.getCellData("Username", 1), "Username", 1);
        excelHelper_1.setCellData(excelHelper.getCellData("Password", 1), "Password", 1);
    }

    @Test
    public void testEditUser() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "User");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();

        adminPage.verifyNavigateToAdminPage();
        adminPage.clickButtonAddNewUser();
        adminPage.submitDataForNewUser(
                excelHelper.getCellData("Employee_Name", 2),
                excelHelper.getCellData("Username", 2),
                excelHelper.getCellData("Password", 2));
        adminPage.verifyAddSuccessAlertVisible();
        adminPage.searchAndCheckUserDetail(excelHelper.getCellData("Username", 2));

        adminPage.updateUser(
                excelHelper.getCellData("Employee_Name", 3),
                excelHelper.getCellData("Username", 3),
                excelHelper.getCellData("Password", 3));
        adminPage.verifyUpdateSuccessAlertVisible();
        adminPage.searchAndCheckUpdatedUser(excelHelper.getCellData("Username", 3));
    }

    @Test
    public void testDeleteUser() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "User");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();

        adminPage.verifyNavigateToAdminPage();
        adminPage.clickButtonAddNewUser();
        adminPage.submitDataForNewUser(
                excelHelper.getCellData("Employee_Name", 4),
                excelHelper.getCellData("Username", 4),
                excelHelper.getCellData("Password", 4));
        adminPage.verifyAddSuccessAlertVisible();

        adminPage.deleteUser(excelHelper.getCellData("Username", 4));
        adminPage.verifyDeleteSuccessAlertVisible();
        adminPage.searchAndCheckDeletedUser(excelHelper.getCellData("Username", 4));
    }
}
