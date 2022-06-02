package com.example.bookstore.service;

import com.example.bookstore.dto.WishlistDTO;
import com.example.bookstore.entity.Wishlist;

public interface IWishlistService {
    public Wishlist addWishlist(WishlistDTO wishlistDTO);
}
