package com.example.bookstore.controller;

import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.service.IWishlistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
