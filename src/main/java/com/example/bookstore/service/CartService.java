package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.UserData;
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

        Cart cart = new Cart(userData, book, cartDTO.quantity);
        return cartRepository.save(cart);
    }


    public void deleteFromCart(int cartId) {
        cartRepository.deleteById(cartId);
    }


    public void deleteAll() {
        cartRepository.deleteAll();
    }


}