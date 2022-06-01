package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {

    @Autowired
    IUserService iUserService;

    @Autowired
    IBookService iBookService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;


    public Cart addToCart(CartDTO cartDTO) {
        UserData userData = iUserService.getUserById(cartDTO.getUserId());
        Book book = iBookService.getById(cartDTO.getBookId());
        if (cartDTO.getQuantity() <= book.getQuantity()) {
            Cart cart = new Cart(userData, book, cartDTO.getQuantity());
            return cartRepository.save(cart);
        } else throw new CustomException("There are only " + book.getQuantity() + " Books in stock at this time, ");
    }


    public String deleteFromCart(int id) {
        if (cartRepository.findById(id).isPresent()) {
            cartRepository.deleteById(id);
            return "Book with ID: " + id + " is Deleted Successfully!!";
        } else throw new CustomException("No book matches with the given ID");
    }
    public Cart getById(int id) {
        return cartRepository.findById(id).orElseThrow(() -> new CustomException("Book  with id " + id + " does not exist in database..!"));

    }
}