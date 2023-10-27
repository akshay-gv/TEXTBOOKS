package edu.syr.textbooks.service;


import edu.syr.textbooks.exception.BookNotFoundException;
import edu.syr.textbooks.model.Book;
import edu.syr.textbooks.model.BookTransaction;
import edu.syr.textbooks.repository.BookRepository;
import edu.syr.textbooks.repository.BookTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private BookTransactionRepository bookTransactionRepository;

    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    public Book addBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public Book getBookById(String id) {
        Book book = bookRepository.findBookById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }

    public boolean buyBook(String bookId) {
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

        BookTransaction transaction = new BookTransaction();
        transaction.setBookId(book.getId());
        transaction.setType("BUY");
        transaction.setPriceAtTransaction(book.getPrice());

        bookTransactionRepository.save(transaction);

        double newPrice = book.getPrice() * 0.9;
        book.setPrice(newPrice);
        bookRepository.save(book);

        return true; }

    public double sellBook(String bookId) {
        Book book = getBookById(bookId);
        if (book != null) {
            BookTransaction transaction = new BookTransaction();
            transaction.setBookId(book.getId());
            transaction.setType("SELL");
            transaction.setPriceAtTransaction(book.getPrice());
            bookTransactionRepository.save(transaction);
            double newPrice = book.getPrice() * 0.9;
            book.setPrice(newPrice);
            bookRepository.save(book);

            return newPrice;
        }
        return -1.0;
    }


    public double sellBookByISBN(String isbn) {
        Book book = bookRepository.findByIsbn(isbn);

        BookTransaction transaction = new BookTransaction();
        transaction.setBookId(book.getId());
        transaction.setType("SELL");
        transaction.setPriceAtTransaction(book.getPrice());

        bookTransactionRepository.save(transaction);

        double newPrice = book.getPrice() * 0.9;
        book.setPrice(newPrice);
        bookRepository.save(book);

        return newPrice;
    }


}
