package edu.syr.textbooks.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "book")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Book {

    @Id
    private String id;

    private String ISBN;
    private String authors;
    private String title;
    private String edition;
    private double price;



}
