package com.example.bookstore.controller;

import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/wishlist")
public class WishlistController {
    @Autowired
    IWishlistService iWishlistService;

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addWishlist(@RequestBody WishlistDTO wishlistDTO) {
        ResponseDTO response = new ResponseDTO("Product Added To Wishlist ", iWishlistService.addWishlist(wishlistDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    ResponseEntity<ResponseDTO> getById(@PathVariable int id) {
        ResponseDTO response = new ResponseDTO("Product Added To Cart ", iWishlistService.getById(id));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
