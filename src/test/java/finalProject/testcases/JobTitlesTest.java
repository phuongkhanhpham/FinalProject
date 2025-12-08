package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.AdminPage;
import finalProject.pages.DashboardPage;
import finalProject.pages.JobTitlesPage;
import finalProject.pages.LoginPage;
import org.testng.annotations.Test;

public class JobTitlesTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AdminPage adminPage;
    JobTitlesPage jobTitlesPage;

    @Test
    public void testAddNewJobTitle() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Job Titles");
        ExcelHelper excelHelper_1 = new ExcelHelper();
        excelHelper_1.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Employees");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();
        jobTitlesPage = adminPage.clickMenuJobTitles();

        jobTitlesPage.verifyNavigateToJobTitlesPage();
        jobTitlesPage.clickButtonAddNewJobTitle();
        jobTitlesPage.submitDataForNewJobTitle(
                excelHelper.getCellData("Job_Title", 1),
                excelHelper.getCellData("Job_Description", 1),
                excelHelper.getCellData("Note", 1));
        jobTitlesPage.verifyAddSuccessAlertVisible();
        excelHelper_1.setCellData(excelHelper.getCellData("Job_Title", 1), "Job_Title", 1);
    }
}
