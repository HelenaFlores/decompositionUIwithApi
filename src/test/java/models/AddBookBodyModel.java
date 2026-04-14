package models;

import lombok.Data;

@Data
public class AddBookBodyModel {
    String userId;
    IsbnItem[] collectionOfIsbns;

    @Data
    public static class IsbnItem {
        String isbn;
    }
}