package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartService implements ICartService {
    @Autowired
    CartRepository cartRepository;

    public Cart addCart(CartDTO cartDTO) {
        Cart cart = new Cart(cartDTO);
        return cartRepository.save(cart);
    }
}
