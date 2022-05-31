package com.example.bookstore.entity;

import com.example.bookstore.dto.BookDTO;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@RequiredArgsConstructor
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private int bookID;
    private String name;
    private String author;
    private float price;
    private LocalDate arrivalDate;

    private String coverImage;
    @JsonDeserialize
    private int quantity;

    public Book(BookDTO bookDTO) {
        this.name = bookDTO.getName();
        this.author = bookDTO.getAuthor();
        this.price = bookDTO.getPrice();
        this.arrivalDate = bookDTO.getArrivalDate();
        this.coverImage = bookDTO.getCoverImage();
        this.quantity = bookDTO.getQuantity();
    }

    public Book(int id, BookDTO bookDTO) {
        this.bookID = id;
        this.name = bookDTO.getName();
        this.author = bookDTO.getAuthor();
        this.price = bookDTO.getPrice();
        this.arrivalDate = bookDTO.getArrivalDate();
        this.coverImage = bookDTO.getCoverImage();
        this.quantity = bookDTO.getQuantity();
    }

    public Book(Book book) {
        this.bookID = book.bookID;
        this.name = book.getName();
        this.author = book.getAuthor();
        this.price = book.getPrice();
        this.arrivalDate = book.getArrivalDate();
        this.coverImage = book.getCoverImage();
        this.quantity = book.getQuantity();
    }
}