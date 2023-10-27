package edu.syr.textbooks.controller;

import edu.syr.textbooks.model.Book;
import edu.syr.textbooks.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getBooks();
    }

    @PostMapping("/buy/{id}")
    public String buyBook(@PathVariable String id) {
        if (bookService.buyBook(id)) {
            return "Success";
        } else {
            return "Failure";
        }
    }

    @PostMapping("/sell/{id}")
    public String sellBook(@PathVariable String id) {
        double newPrice = bookService.sellBook(id);
        if (newPrice >= 0) {
            return "Success, new price: " + newPrice;
        } else {
            return "Failure";
        }
    }

    @PostMapping("/add")
    public Book addBook(@RequestBody Book newBook) {
        return bookService.addBook(newBook);
    }

    @PostMapping("/sell/isbn/{isbn}")
    public ResponseEntity<String> sellBookByISBN(@PathVariable String isbn) {
        double price = bookService.sellBookByISBN(isbn);

        if (price != -1.0) {
            return new ResponseEntity<>("Success. Price: $" + price, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Failure", HttpStatus.BAD_REQUEST);
        }
    }

}
