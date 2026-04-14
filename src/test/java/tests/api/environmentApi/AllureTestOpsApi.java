package tests.api.environmentApi;

import com.google.gson.Gson;
import config.App;
import helpers.AllureRestAssuredFilter;
import lombok.Getter;
import com.google.gson.annotations.SerializedName;

import static io.restassured.RestAssured.given;

public class AllureTestOpsApi {

    private static final String ALLURE_TESTOPS_SESSION = "ALLURE_TESTOPS_SESSION";

    public static String getAuthorizationCookie() {
        String xsrfToken = getAuthorization().getJti();
        return given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .header("X-XSRF-TOKEN", xsrfToken)
                .header("Cookie", "XSRF-TOKEN=" + xsrfToken)
                .formParam("username", App.config.userLogin())
                .formParam("password", App.config.userPassword())
                .when()
                .post("/api/login/system")
                .then()
                .statusCode(200)
                .extract().response()
                .getCookie(ALLURE_TESTOPS_SESSION);
    }

    private static AuthorizationResponseDto getAuthorization() {
        String json = given()
                .filter(AllureRestAssuredFilter.withCustomTemplates())
                .formParam("grant_type", "apitoken")
                .formParam("scope", "openid")
                .formParam("token", App.config.userToken())
                .when()
                .post("/api/uaa/oauth/token")
                .then()
                .statusCode(200)
                .extract().response().asString();
        return AuthorizationResponseDto.fromJson(json);
    }

    @Getter
    static class AuthorizationResponseDto {
        @SerializedName("expires_in")
        private Integer expiresIn;
        @SerializedName("token_type")
        private String tokenType;
        private String scope;
        @SerializedName("access_token")
        private String accessToken;
        private String jti;

        static AuthorizationResponseDto fromJson(String json) {
            return new Gson().fromJson(json, AuthorizationResponseDto.class);
        }
    }
}