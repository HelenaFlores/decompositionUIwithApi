package tests;

import helpers.WithLogin;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import tests.api.BooksApi;
import tests.environment.TestBase;
import pages.collectionPage.AddCollectionPage;

import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static tests.environment.TestData.*;

public class AddBooksCollectionTests extends TestBase {
    private AddCollectionPage collectionPage = new AddCollectionPage();


    @Test
    @WithLogin
    void addBookToCollectionTest() {

        step("Сlearing the collection of all books", () -> {
            BooksApi.deleteAllBooks();
        });

        step("Add book with ISBN " + isbn + " in collection", () -> {
            BooksApi.addBook(isbn);
        });

        step("Open profile page", () -> {
            open(profileUri);
        });

        step("Checking name of a book", () -> {
            collectionPage.checkNameBook(nameBook);
        });
    }

    @Test
    @WithLogin
    void negative400BookToCollectionTest() {

        step("Сlearing the collection of all books", () -> {
            BooksApi.deleteAllBooks();
        });

        step("Add book with ISBN " + isbn + " in collection", () -> {
            BooksApi.addBook(isbn);
        });

        step("Add dublicate book with ISBN " + isbn + " in collection", () -> {
            BooksApi.addBookDublicate(isbn);
        });
    }

    @Test
    void negative401AddBookToCollectionTest() {
        String invalidUserId = "invalid-user-id-12345";
        step("Add book with ISBN " + isbn + " in collection without authorization", () -> {
            BooksApi.addBookUnauthorized(isbn, invalidUserId);
        });

    }
}