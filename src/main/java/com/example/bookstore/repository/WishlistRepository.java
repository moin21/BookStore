package com.example.bookstore.repository;

import com.example.bookstore.entity.UserData;
import com.example.bookstore.entity.Wishlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WishlistRepository extends JpaRepository<Wishlist, Integer> {
}
