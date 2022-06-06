package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
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

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addToCart(@RequestBody CartDTO cartDTO, @RequestParam String token) {
        ResponseDTO response = new ResponseDTO("Product Added To Cart ", cartService.addToCart(cartDTO, token));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/remove/{cartId}")
    ResponseEntity<ResponseDTO> removeFromCart(@PathVariable("cartId") int cartId) {

        ResponseDTO response = new ResponseDTO("Cart with id " + cartId + " deleted successfully", cartService.deleteFromCart(cartId));
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/get/{cartId}")
    ResponseEntity<ResponseDTO> getById(@PathVariable("cartId") int cartId) {
        ResponseDTO response = new ResponseDTO("Product Added To Cart ", cartService.getById(cartId));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}