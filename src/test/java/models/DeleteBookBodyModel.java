package models;

import lombok.Data;

@Data
public class DeleteBookBodyModel {
    String userId, isbn;
}
