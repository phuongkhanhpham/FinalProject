package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.DashboardPage;
import finalProject.pages.LoginPage;
import finalProject.pages.PimPage;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

public class PimTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    PimPage pimPage;

    @Test
    public void testAddNewEmployee() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Employees");
        String id = "Id" + ThreadLocalRandom.current().nextInt(10_000_000);
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        pimPage = dashboardPage.clickMenuPim();

        pimPage.verifyNavigateToPimPage();

        // Add new employee
        pimPage.clickButtonAddNewUser();
        pimPage.submitDataForNewEmployee(
                excelHelper.getCellData("First_Name", 1),
                excelHelper.getCellData("Middle_Name", 1),
                excelHelper.getCellData("Last_Name", 1),
                id,
                excelHelper.getCellData("Username", 1),
                excelHelper.getCellData("Password", 1));

        pimPage.checkAddNewEmployeeSuccess(
                excelHelper.getCellData("First_Name", 1),
                excelHelper.getCellData("Middle_Name", 1),
                excelHelper.getCellData("Last_Name", 1),
                id);

        // Update Personal Details
        pimPage.updatePersonalDetails(
                excelHelper.getCellData("Other _Id", 1),
                excelHelper.getCellData("Driver_License_Number", 1),
                excelHelper.getCellData("License_Expiry_Date", 1),
                excelHelper.getCellData("Date_of_Birth", 1));
        pimPage.checkUpdatePersonalDetailsSuccess(
                excelHelper.getCellData("Other _Id", 1),
                excelHelper.getCellData("Driver_License_Number", 1),
                excelHelper.getCellData("License_Expiry_Date", 1),
                excelHelper.getCellData("Date_of_Birth", 1));

        // Update Job Details
        pimPage.clickTabJob();
        pimPage.updateJobDetails(
                excelHelper.getCellData("Joined_Date", 1),
                excelHelper.getCellData("Job_Title", 1),
                excelHelper.getCellData("Job_Category", 1),
                excelHelper.getCellData("Location", 1),
                excelHelper.getCellData("Employment_Status", 1));
        pimPage.checkUpdateJobDetailsSuccess(
                excelHelper.getCellData("Joined_Date", 1),
                excelHelper.getCellData("Job_Title", 1),
                excelHelper.getCellData("Job_Category", 1),
                excelHelper.getCellData("Location", 1),
                excelHelper.getCellData("Employment_Status", 1));

        // Search in table
        pimPage.clickMenuEmployeeList();
        pimPage.searchAndCheckInTable(id,
                excelHelper.getCellData("First_Name", 1) + " " + excelHelper.getCellData("Middle_Name", 1),
                excelHelper.getCellData("Last_Name", 1),
                excelHelper.getCellData("Job_Title", 1),
                excelHelper.getCellData("Employment_Status", 1));
    }
}
