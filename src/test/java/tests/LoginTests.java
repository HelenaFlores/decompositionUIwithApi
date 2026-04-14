package tests;

import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import tests.environment.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static tests.environment.TestData.*;

public class LoginTests extends TestBase {
    private LoginPage loginPage = new LoginPage();
    @Test
    void successfulLoginWithUiTest() {
        step("Open page form", () -> {
        open(loginUri);
        step("Fill form", () -> {
        loginPage.setUserName(userName)
                .setPassword(password)
                .loginClick();
        });
        step("Check form", () -> {
        loginPage.checkUserName(userName);
            });
        });
    }
    @Test
    @WithLogin
    void successfulLoginWithApiTest() {
        step("Open profile page for check", () -> {
        open(profileUri);
        });
        step("Check form", () -> {
            loginPage.checkUserName(userName);
        });
    }
}