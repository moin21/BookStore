package com.example.bookstore.entity;


import com.example.bookstore.dto.OrderDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    @Id
    @GeneratedValue
    public int orderId;

    @ElementCollection
    @CollectionTable(name = "books", joinColumns = @JoinColumn(name = "order_id"))
    public List<Integer> book;

    @ElementCollection
    @CollectionTable(name = "book_quantities", joinColumns = @JoinColumn(name = "order_id"))
    public List<Integer> quantity;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserData userData;

    public String address;
    public LocalDate orderDate = LocalDate.now();

    public boolean isActive = true;


    public OrderData(UserData userData, List<Integer> book, List<Integer> quantity, String address) {
        this.userData = userData;
        this.book = book;
        this.address = address;
        this.orderDate = getOrderDate();
        this.isActive = isActive();
    }

    public OrderData(UserData userData, List<Integer> book, List<Integer> quantity, String address, boolean isActive) {
        this.userData = userData;
        this.book = book;
        this.address = address;
        this.orderDate = getOrderDate();
        this.isActive = isActive;
    }

    public OrderData(String address, Cart cart) {
        this.userData = cart.getUserData();
        this.book = cart.getBook();
        this.address = address;
        this.orderDate = getOrderDate();
        this.isActive = isActive();
    }

}

