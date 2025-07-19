package com.example.demo.repository;

import com.example.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByAuthorContainingIgnoreCase(String author);
    List<Book> findByLanguage(String language);
    // List<Book> findByIsbnContainingIgnoreCase(String isbn);
    // List<Book> findByLanguageContainingIgnoreCase(String language);
}
