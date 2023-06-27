package com.example.SpringRest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;


    @RequestMapping("/")
    public String index1() {
        return "Congratulations from bookController.java";
    }

    @GetMapping("/books")
    public List<Book> index() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    public Book show(@PathVariable String id) {
         long bookId = Long.parseLong(id);
        return bookRepository.findById(bookId).get();
    }

    @PostMapping("/books/search")
    public List<Book> search(@RequestBody Map<String, String> body) {
        String searchTerm = body.get("text");
        return bookRepository.findByTitleContainingOrDescriptionContaining(searchTerm, searchTerm);
    }


    @PostMapping("/books")
    public Book create(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/book/{id}")
    public Book update(@PathVariable String id, @RequestBody Map<String, String> body) {
        long bookId = Long.parseLong(id);
        Book bookToUpdate = bookRepository.findById(bookId).get();

        String title = body.get("title");
        String author = body.get("author");
        String description = body.get("description");

        bookToUpdate.setTitle(title);
        bookToUpdate.setAuthor(author);
        bookToUpdate.setDescription(description);

        return bookRepository.save(bookToUpdate);
    }

    @DeleteMapping("book/{id}")
    public boolean delete(@PathVariable String id) {
        long bookId = Long.parseLong(id);
        bookRepository.deleteById(bookId);
        return true;
    }

}
