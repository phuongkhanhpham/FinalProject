package finalProject.testcases;

import finalProject.common.BaseTest;
import finalProject.pages.LoginPage;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    LoginPage loginPage;

    @Test(priority = 2)
    @Parameters({"email", "password"})
    public void loginSuccess(String email, String password) {
        loginPage.login(email, password);
    }

    @Test(priority = 1)
    public void loginSuccess1() {
        loginPage = new LoginPage();
        loginPage.login("Admin", "Admin123");
    }
}
