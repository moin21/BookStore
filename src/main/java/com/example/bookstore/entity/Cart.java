package com.example.bookstore.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Cart")
@Data
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int cartId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserData userData;

    @ElementCollection
    @CollectionTable(name = "cart_books",joinColumns = @JoinColumn(name = "cart_id"))
    public List<Integer> book;

    @ElementCollection
    @CollectionTable(name = "cart_book_quantities",joinColumns = @JoinColumn(name = "cart_id"))
    public List<Integer> quantity;


    public Cart(UserData userData, List<Integer> book, List<Integer> quantity) {
        this.userData = userData;
        this.book = book;
        this.quantity = quantity;
    }
    public Cart(int cartId, UserData userData, List<Integer> book, List<Integer> quantity) {
        this.cartId = cartId;
        this.userData = userData;
        this.book = book;
        this.quantity = quantity;
    }

    public Cart() {

    }
}

