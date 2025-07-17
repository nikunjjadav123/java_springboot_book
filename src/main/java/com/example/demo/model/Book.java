package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String isbn;

    private int yearPublished;

    private double price;

    private int stock;

    public Book() {}
    public Book(String title, String author, String isbn, Integer yearPublished, Double price, Integer stock) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.yearPublished = yearPublished;
        this.price = price;
        this.stock = stock;
    }

    public Long getId() { 
        return id; 
    }
    public String getTitle() { 
        return title; 
    }
    public void setTitle(String title) { 
        this.title = title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public void setAuthor(String author) { 
        this.author = author; 
    }
    public String getIsbn() { 
        return isbn; 
    }
    public void setIsbn(String isbn) { 
        this.isbn = isbn; 
    }

    public Integer getYearPublished() { 
        return yearPublished; 
    }
    public void setYearPublished(Integer yearPublished) { 
        this.yearPublished = yearPublished; 
    }

    public Double getPrice() { 
        return price; 
    }
    public void setPrice(Double price) { 
        this.price = price; 
    }

    public Integer getStock() { 
        return stock; 
    }
    public void setStock(Integer stock) { 
        this.stock = stock; 
    }

    
}
