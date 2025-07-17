package com.example.demo.controller;
import org.springframework.ui.Model;

import com.example.demo.model.Book;
import com.example.demo.repository.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

// @RestController
@Controller

@RequestMapping("/books")
public class BookController {

    private final BookRepository bookRepo;

    public BookController(BookRepository bookRepo) {
        this.bookRepo = bookRepo;
    }

    @GetMapping("/all")
    public String getAllBooks(Model model) {
        List<Book> books = bookRepo.findAll();
        model.addAttribute("books", books);
        return "books"; 
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookRepo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Book createBook(@RequestBody Book book) {
        return bookRepo.save(book);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book updatedBook) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isEmpty()) return ResponseEntity.notFound().build();

        Book book = optionalBook.get();

        if (updatedBook.getTitle() != null) {
             book.setTitle(updatedBook.getTitle());
        }
        if (updatedBook.getAuthor() != null) {
            book.setAuthor(updatedBook.getAuthor());
        }
        if (updatedBook.getIsbn() != null) {
            book.setIsbn(updatedBook.getIsbn());
        }
        if (updatedBook.getYearPublished() != null) {
            book.setYearPublished(updatedBook.getYearPublished());
        }
        if (updatedBook.getPrice() != null) {
            book.setPrice(updatedBook.getPrice());
        }
        if (updatedBook.getStock() != null) {
            book.setStock(updatedBook.getStock());
        }

        return ResponseEntity.ok(bookRepo.save(book));
    }

    @DeleteMapping("delete/{id}")
    public String deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookRepo.deleteById(id);
        return "books";
    }
    // public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
    //     System.out.println("Deleting book with ID: " + id);
    //     if (!bookRepo.existsById(id)) return ResponseEntity.notFound().build();

    //     bookRepo.deleteById(id);
    //     return ResponseEntity.noContent().build();
    // }
}
