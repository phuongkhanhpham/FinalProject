package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.AdminPage;
import finalProject.pages.DashboardPage;
import finalProject.pages.EmploymentStatusPage;
import finalProject.pages.LoginPage;
import org.testng.annotations.Test;

public class EmploymentStatusTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AdminPage adminPage;
    EmploymentStatusPage employmentStatusPage;

    @Test
    public void testAddNewEmploymentStatus() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Employment Status");
        ExcelHelper excelHelper_1 = new ExcelHelper();
        excelHelper_1.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Employees");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();
        employmentStatusPage = adminPage.clickMenuEmploymentStatus();

        employmentStatusPage.verifyNavigateToEmploymentStatusPage();
        employmentStatusPage.clickButtonAddNewEmploymentStatus();
        employmentStatusPage.submitDataForNewEmploymentStatus(excelHelper.getCellData("Employment_Status", 1));
        employmentStatusPage.verifyAddSuccessAlertVisible();
        excelHelper_1.setCellData(excelHelper.getCellData("Employment_Status", 1), "Employment_Status", 1);
    }
}
