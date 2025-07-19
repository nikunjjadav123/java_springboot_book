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

    @GetMapping("/update/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model) {
        Book book = bookRepo.findById(id)
            .orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
        model.addAttribute("book", book);
        return "edit-book-form";
    }
    
    @PostMapping("/update/{id}")
    public String updateBook(@PathVariable Long id, @ModelAttribute Book updatedBook, RedirectAttributes redirectAttributes) {
        Optional<Book> optionalBook = bookRepo.findById(id);
        if (optionalBook.isEmpty()) return "redirect:/books/all";

        Book book = optionalBook.get();

        if (updatedBook.getTitle() != null) {
             book.setTitle(updatedBook.getTitle());
        }
        if (updatedBook.getLanguage() != null) {
             book.setLanguage(updatedBook.getLanguage());
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
        bookRepo.save(book);
        redirectAttributes.addFlashAttribute("message", book.getLanguage() + " Book '" + book.getTitle() + "' updated successfully!");
        return "redirect:/books/all";
    }

    @GetMapping("/new") 
    public String showNewBookForm(Model model) {
        model.addAttribute("book", new Book()); 
        return "add-book-form";
    }
    @PostMapping("/save")
    public String createBook(@ModelAttribute Book book, RedirectAttributes redirectAttributes) {
        bookRepo.save(book);
        redirectAttributes.addFlashAttribute("message", "Book '" + book.getTitle() + "' added successfully!");
        return "redirect:/books/all";
    }

    @DeleteMapping("delete/{id}")
     public ResponseEntity<Book> deleteBook(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bookRepo.deleteById(id);
        return ResponseEntity.ok().build();
    }

    // --- Search Endpoints ---
    
    @GetMapping("/search")
    public String searchByTitle(@RequestParam(value = "keyword", required = false) String keyword, Model model) {
        List<Book> books = bookRepo.findByTitleContainingIgnoreCase(keyword);
        if (books.isEmpty()) {
            books = bookRepo.findByAuthorContainingIgnoreCase(keyword);
        }
        model.addAttribute("books", books);
        model.addAttribute("keyword", keyword);
        return "books";
    }

    @GetMapping("/language")
    public String filterByLanguage(@RequestParam(value = "language", required = false) String language, Model model) {
        List<Book> books = bookRepo.findByLanguage(language);
        model.addAttribute("books", books);
        model.addAttribute("selectedLanguage", language);
        model.addAttribute("languages", List.of("English", "Hindi", "Gujarati", "Tamil", "Marathi"));
        return "books";
    }

}
