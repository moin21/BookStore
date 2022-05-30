package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.Cart;

public interface ICartService {
    public Cart addCart(CartDTO cartDTO);
}
