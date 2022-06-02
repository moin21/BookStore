package com.example.bookstore.service;

import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.entity.Wishlist;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.WishlistRepository;
import com.example.bookstore.util.TokenUtility;
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
    @Autowired
    TokenUtility tokenUtility;

    public Wishlist addWishlist(WishlistDTO wishlistDTO) {
        UserData userData = iUserService.getUserById(tokenUtility.createToken(wishlistDTO.getUserId()));
        Book book = iBookService.getById(wishlistDTO.getBookId());
        Wishlist wishlist = new Wishlist(userData, book);
        return wishlistRepository.save(wishlist);
    }

    public Wishlist getById(int id) {
        return wishlistRepository.findById(id).orElseThrow(() -> new CustomException("Wishlist  with id " + id + " does not exist in database..!"));

    }
}
