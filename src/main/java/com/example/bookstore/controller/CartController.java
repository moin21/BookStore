package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    public ICartService cartService;

    @Autowired
    CartRepository cartRepo;

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO) {
        Cart add = cartService.addToCart(cartDTO);
        ResponseDTO response = new ResponseDTO("Product Added To Cart ", add);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{cartId}")
    ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("cartId") int cartId) {
        cartService.deleteFromCart(cartId);
        ResponseDTO response = new ResponseDTO("Delete call success for item Removed From Cart ", "deleted id:" + cartId);
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }

    @DeleteMapping("/removeAll")
    ResponseEntity<ResponseDTO> removeAllFromCart() {
        cartService.deleteAll();
        ResponseDTO response = new ResponseDTO("All Items deleted from cart", "All Carts for all users deleted");
        return new ResponseEntity<ResponseDTO>(response, HttpStatus.OK);
    }


}