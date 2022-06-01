package com.example.bookstore.service;

//import com.example.bookstore.dto.CartDTO;


import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.Cart;

import java.util.List;

public interface ICartService {

    public Cart addToCart(CartDTO cartDTO);

    public String deleteFromCart(int id);
    public Cart getById(int id);

}
