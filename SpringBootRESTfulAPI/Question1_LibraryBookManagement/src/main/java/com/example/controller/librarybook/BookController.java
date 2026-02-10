package auca.ac.rw.restfullApiAssignment.controller.library;

import auca.ac.rw.restfullApiAssignment.modal.library.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private List<Book> books = new ArrayList<>();
    private Long nextId = 4L;

    public BookController() {
        books.add(new Book(1L, "Clean Code", "Robert Martin", "978-0132350884", 2008));
        books.add(new Book(2L, "Effective Java", "Joshua Bloch", "978-0134685991", 2018));
        books.add(new Book(3L, "The Pragmatic Programmer", "David Thomas", "978-0201616224", 1999));
    }

    // get all books
    @GetMapping
    public ResponseEntity<List<Book>> getAllBooks() {
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // search book by title
    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBook(@RequestParam String title) {

        List<Book> result = new ArrayList<>();

        for (Book book : books) {
            if (book.getTitle().toLowerCase().contains(title.toLowerCase())) {
                result.add(book);
            }
        }

        if (result.size() == 0) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    // get book by id
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                return new ResponseEntity<>(book, HttpStatus.OK);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    // add new book
    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book) {

        book.setId(nextId);
        nextId++;

        books.add(book);
        return new ResponseEntity<>(book, HttpStatus.CREATED);
    }

    // delete book
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {

        for (Book book : books) {
            if (book.getId().equals(id)) {
                books.remove(book);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
