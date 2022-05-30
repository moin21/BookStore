package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;

import java.util.List;

public interface IBookService {
    Book addBook(BookDTO bookDTO);

    Book getById(int id);

    List<Book> getBookList();

    String deleteById(int id);

    Book updateById(int id, BookDTO bookDTO);

    List<Book> findBookByName(String bName);

    List<Book> sortByName();

    List<Book> sortByPrice();

    Book updateQuantityById(int id, int quantity);

    List<Book> sortBookList(String field);
}
