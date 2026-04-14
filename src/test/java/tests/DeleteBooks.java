package tests;

import helpers.WithLogin;
import org.junit.jupiter.api.Test;
import pages.collectionPage.DeleteCollectionPage;
import tests.api.BooksApi;
import tests.environment.TestBase;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static tests.environment.TestData.isbn;
import static tests.environment.TestData.profileUri;

public class DeleteBooks extends TestBase {

    private DeleteCollectionPage deleteCollectionPage = new DeleteCollectionPage();
    @Test
    @WithLogin
    void deleteBookToCollectionTest() {

        step("Сlearing the collection of all books", () -> {
            BooksApi.deleteAllBooks();
        });

        step("Add book with ISBN " + isbn + " in collection", () -> {
            BooksApi.addBook(isbn);
        });

        step("Open profile page", () -> {
            open(profileUri);
        });

        step("Delete book with ISBN" + isbn, () -> {
            deleteCollectionPage.clickBasketIcon()
                    .clickModalOkButton();
        });

        step("Checking for the absence of a book", () -> {
            deleteCollectionPage.checkProfileWrapper();
        });
    }
}
