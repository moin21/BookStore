package com.example.bookstore.service;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService implements IBookService {
    @Autowired
    BookRepository bookRepository;

    public Book addBook(BookDTO bookDTO) {
        Book book = new Book(bookDTO);
        return bookRepository.save(book);
    }

    public Book getById(int id) {
        return bookRepository.findById(id).orElseThrow(() -> new CustomException("Book  with id " + id + " does not exist in database..!"));

    }

    public List<Book> getBookList() {
        if (bookRepository.findAll().isEmpty()) {
            throw new CustomException("No Books in the bookshelf.");
        } else return bookRepository.findAll();
    }

    public String deleteById(int id) {
        if (bookRepository.findById(id).isPresent()) {
            bookRepository.deleteById(id);
            return "Book with ID: " + id + " is Deleted Successfully!!";
        } else throw new CustomException("No book matches with the given ID");
    }

    public Book updateById(int id, BookDTO bookDTO) {
        if (bookRepository.findById(id).isPresent()) {
            Book book = new Book(id, bookDTO);
            return bookRepository.save(book);
        } else throw new CustomException("No book matches with the given ID");
    }

    public List<Book> findBookByName(String bName) {
        List<Book> book = bookRepository.findBookByName(bName);

        if (book.size() != 0) {
            return book;

        } else throw new CustomException("No book named " + bName + " exists in shelf!!");
    }

    public List<Book> sortByName() {
        List<Book> sortedBooks = bookRepository.findAll().stream().sorted(Comparator.comparing(Book::getName)).collect(Collectors.toList());

        if (sortedBooks.size() != 0) {
            return sortedBooks;

        } else throw new CustomException("No Books in the bookshelf.");
    }

    public List<Book> sortByPrice() {
        List<Book> sortedBooks = bookRepository.findAll().stream().sorted(Comparator.comparing(Book::getPrice)).collect(Collectors.toList());

        if (sortedBooks.size() != 0) {
            return sortedBooks;

        } else throw new CustomException("No Books in the bookshelf.");
    }
 public  List<Book> sortBookList(String field){
     List<Book> sortedList;
     if (field.equals("name")) {
         sortedList = sortByName();
     } else if (field.equals("price")) {
         sortedList = sortByPrice();
     } else {
         sortedList = getBookList();
     }
     return sortedList;

 }
    public Book updateQuantityById(int id, int quantity) throws CustomException {
        Book book = this.getById(id);
        book.setQuantity(quantity);
        return bookRepository.save(book);
    }
}
