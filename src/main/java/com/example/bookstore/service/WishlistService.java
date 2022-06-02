package com.example.bookstore.service;

import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.entity.Wishlist;
import com.example.bookstore.repository.WishlistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService implements IWishlistService {
    @Autowired
    IUserService iUserService;
    @Autowired
    IBookService iBookService;
    @Autowired
    WishlistRepository wishlistRepository;

    public Wishlist addWishlist(WishlistDTO wishlistDTO) {
        UserData userData = iUserService.getUserById(wishlistDTO.getUserId());
        Book book = iBookService.getById(wishlistDTO.getBookId());
        Wishlist wishlist = new Wishlist(userData, book);
        return wishlistRepository.save(wishlist);
    }
}
