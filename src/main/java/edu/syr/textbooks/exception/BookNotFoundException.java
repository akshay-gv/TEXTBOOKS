package edu.syr.textbooks.exception;

public class BookNotFoundException extends RuntimeException {

    public BookNotFoundException(String id) {
        super("Book not found with id: " + id);
    }

    public BookNotFoundException(String id, Throwable cause) {
        super("Book not found with id: " + id, cause);
    }

}