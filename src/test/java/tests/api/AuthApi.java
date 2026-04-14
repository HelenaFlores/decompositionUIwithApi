package tests.api;

import io.restassured.response.Response;
import lombok.Getter;
import models.login.LoginBodyModel;
import org.openqa.selenium.Cookie;
import tests.api.environmentApi.AuthData;

import static com.codeborne.selenide.Selenide.open;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static specs.Spec.loginRequestSpec;
import static specs.Spec.successResponseSpec;
import static tests.environment.TestData.*;

public class AuthApi {
    @Getter
    private static AuthData authData;

    public static void loginViaApiAndSetCookies() {
        LoginBodyModel loginBody  = new LoginBodyModel();
        loginBody.setUserName(userName);
        loginBody.setPassword(password);

        Response authResponse = given()
                .spec(loginRequestSpec)
                .body(loginBody )
                .when()
                .post(loginUriApi)
                .then()
                .spec(successResponseSpec)
                .extract().response();

        authData = new AuthData(
                authResponse.path("token"),
                authResponse.path("userId"),
                authResponse.path("expires")
        );

        open(minUriForApi);
        getWebDriver().manage().addCookie(new Cookie("userID", authResponse.path("userId")));
        getWebDriver().manage().addCookie(new Cookie("expires", authResponse.path("expires")));
        getWebDriver().manage().addCookie(new Cookie("token", authResponse.path("token")));
        getWebDriver().manage().addCookie(new Cookie("userName", userName));
    }
}
