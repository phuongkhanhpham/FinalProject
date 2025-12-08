package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.helpers.ExcelHelper;
import finalProject.pages.LoginPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test
    public void loginSuccess() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Login");
        loginPage = new LoginPage();
        loginPage.login(
                excelHelper.getCellData("Username", 1),
                excelHelper.getCellData("Password", 1));
        loginPage.verifyLoginSuccess();
    }

    @Test
    public void loginFailedWithInvalidUsername() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Login");
        loginPage = new LoginPage();
        loginPage.login(
                excelHelper.getCellData("Username", 2),
                excelHelper.getCellData("Password", 2));
        loginPage.verifyLoginFailed();
    }

    @Test
    public void loginFailedWithInvalidPassword() {
        ExcelHelper excelHelper = new ExcelHelper();
        excelHelper.setExcelFile("src/test/resources/testdata/HRM.xlsx", "Login");
        loginPage = new LoginPage();
        loginPage.login(
                excelHelper.getCellData("Username", 3),
                excelHelper.getCellData("Password", 3));
        loginPage.verifyLoginFailed();
    }
}
