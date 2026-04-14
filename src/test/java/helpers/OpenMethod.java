package helpers;

import io.qameta.allure.Step;

import static com.codeborne.selenide.Selenide.open;

public class OpenMethod {
    @Step("Open registration page \"{value}\"")
    public OpenMethod openPage(String value) {
        open(value);

        return this;
    }
}
