package tests.api;

import models.AddBookBodyModel;
import tests.environment.TestBase;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static specs.Spec.*;
import static tests.environment.TestData.bookUriApi;

public class BooksApi extends TestBase {
    public static String getAuthToken() {
        return AuthApi.getAuthData().getToken();
    }

    private static String getUserId() {
        return AuthApi.getAuthData().getUserId();
    }
    // Очистка коллекции всех книг
    public static void deleteAllBooks() {
         given()
                 .spec(bookRequestSpec)
                 .header("Authorization", "Bearer " + getAuthToken())
                 .queryParam("UserId", getUserId())
                 .when()
                 .delete(bookUriApi)
                 .then()
                 .spec(noContentResponseSpec)
                 .extract()
                 .response();
    }

    // Добавление книги в коллекцию
    public static void addBook(String isbn) {
        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(getUserId());

        AddBookBodyModel.IsbnItem isbnItem = new AddBookBodyModel.IsbnItem();
        isbnItem.setIsbn(isbn);
        bookData.setCollectionOfIsbns(new AddBookBodyModel.IsbnItem[]{isbnItem});

        given()
                .spec(bookRequestSpec)
                .header("Authorization", "Bearer " + getAuthToken())
                .body(bookData)
                .when()
                .post(bookUriApi)
                .then()
                .spec(createdResponseSpec)
                .extract()
                .response();
    }

    // Добавление дубликата книги в коллекцию
    public static void addBookDublicate(String isbn) {
        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(getUserId());

        AddBookBodyModel.IsbnItem isbnItem = new AddBookBodyModel.IsbnItem();
        isbnItem.setIsbn(isbn);
        bookData.setCollectionOfIsbns(new AddBookBodyModel.IsbnItem[]{isbnItem});

        given()
                .header("Authorization", "Bearer " + getAuthToken())
                .body(bookData)
                .when()
                .post(bookUriApi)
                .then()
                .spec(badRequestResponseSpec)
                .body("code", is("1210"))
                .body("message", is("ISBN already present in the User's Collection!"));
    }

    // Добавление книги в коллекцию без авторизации
    public static void addBookUnauthorized(String isbn, String userId) {
        AddBookBodyModel bookData = new AddBookBodyModel();
        bookData.setUserId(userId);

        AddBookBodyModel.IsbnItem isbnItem = new AddBookBodyModel.IsbnItem();
        isbnItem.setIsbn(isbn);
        bookData.setCollectionOfIsbns(new AddBookBodyModel.IsbnItem[]{isbnItem});

        given()
                .log().uri()
                .log().method()
                .log().body()
                .contentType(JSON)
                .body(bookData)
                .when()
                .post(bookUriApi)
                .then()
                .spec(unauthorizedResponseSpec)
                .body("code", is("1200"))
                .body("message", is("User not authorized!"));
    }
}
