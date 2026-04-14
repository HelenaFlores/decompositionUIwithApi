package pages.collectionPage;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import pages.LoginPage;

import static com.codeborne.selenide.Selenide.$;
import static tests.environment.TestData.isbn;
import static tests.environment.TestData.nameBook;

public class DeleteCollectionPage {
    private final SelenideElement basketIcon =  $("#delete-record-" + isbn);
    private final SelenideElement modalOkButton =  $("#closeSmallModal-ok");
    private final SelenideElement profileWrapper =  $(".profile-wrapper");
    @Step("Type first name \"{value}\"")
    public DeleteCollectionPage clickBasketIcon() {
        basketIcon.click();

        return this;
    }
    @Step("Type first name \"{value}\"")
    public DeleteCollectionPage clickModalOkButton() {
        modalOkButton.click();

        return this;
    }
    @Step("Type first name \"{value}\"")
    public DeleteCollectionPage checkProfileWrapper() {
        profileWrapper.shouldNotHave(Condition.exactText(nameBook));

        return this;
    }
}
