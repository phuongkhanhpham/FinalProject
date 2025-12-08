package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.AdminPage;
import finalProject.pages.DashboardPage;
import finalProject.pages.JobCategoriesPage;
import finalProject.pages.LoginPage;
import org.testng.annotations.Test;

public class JobCategoriesTest extends BaseTest {

    LoginPage loginPage;
    DashboardPage dashboardPage;
    AdminPage adminPage;
    JobCategoriesPage jobCategoriesPage;

    @Test
    public void testAddNewJobCategory() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Job Categories");
        ExcelHelper excelHelper_1 = new ExcelHelper();
        excelHelper_1.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Employees");
        loginPage = new LoginPage();
        dashboardPage = loginPage.loginHRM();
        adminPage = dashboardPage.clickMenuAdmin();
        jobCategoriesPage = adminPage.clickMenuJobCategories();

        jobCategoriesPage.verifyNavigateToJobCategoriesPage();
        jobCategoriesPage.clickButtonAddNewJobCategories();
        jobCategoriesPage.submitDataForNewJobCategories(excelHelper.getCellData("Job_Category", 1));
        jobCategoriesPage.verifyAddSuccessAlertVisible();
        excelHelper_1.setCellData(excelHelper.getCellData("Job_Category", 1), "Job_Category", 1);
    }
}
