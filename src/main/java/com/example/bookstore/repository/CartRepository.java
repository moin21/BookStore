package com.example.bookstore.repository;

import com.example.bookstore.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Query(value = "SELECT * FROM cart c where c.user_Id = :userId", nativeQuery = true)
    Cart findCartByUserId(int userId);

    Cart getById(int cartId);
}
