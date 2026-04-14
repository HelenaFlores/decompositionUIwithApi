package pages;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class LoginPage {
    private final SelenideElement userNameInput =  $("#userName");
    private final SelenideElement passwordInput =  $("#password");
    private final SelenideElement userNameValue =  $("#userName-value");
    private final SelenideElement loginButton =  $("#login");

    @Step("Type first name \"{value}\"")
    public LoginPage setUserName(String value) {
        userNameInput.setValue(value);

        return this;
    }

    @Step("Type password \"{value}\"")
    public LoginPage setPassword(String value) {
        passwordInput.setValue(value);

        return this;
    }

    @Step("Click to button login")
    public LoginPage loginClick() {
        loginButton.click();

        return this;
    }
    @Step("Type password \"{value}\"")
    public LoginPage checkUserName(String value) {
        userNameValue.shouldHave(text(value));

        return this;
    }
}
