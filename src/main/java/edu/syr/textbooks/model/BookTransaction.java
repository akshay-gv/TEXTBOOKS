package edu.syr.textbooks.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
    @Data
    public class BookTransaction {

        @Id
        private String transactionId;

        private String bookId;
        private String type;
        private double priceAtTransaction;


}
