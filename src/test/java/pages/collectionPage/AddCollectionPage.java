package pages.collectionPage;

import com.codeborne.selenide.SelenideElement;
import io.qameta.allure.Step;
import tests.environment.TestData;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class AddCollectionPage {
    private final SelenideElement nameBook =  $("#see-book-" + TestData.nameBook.replace(" ", "\\ "));

    @Step("Type first name \"{value}\"")
    public AddCollectionPage checkNameBook(String value) {
        nameBook.shouldHave(text(value));

        return this;
    }
}
