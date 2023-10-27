package edu.syr.textbooks.repository;

import edu.syr.textbooks.model.BookTransaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BookTransactionRepository extends MongoRepository<BookTransaction, String> {


    List<BookTransaction> findByBookId(String bookId);

}