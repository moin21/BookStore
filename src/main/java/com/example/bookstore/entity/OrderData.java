package com.example.bookstore.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    @Id
    @GeneratedValue
    public int orderId;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    public Book book;

    @JsonIgnoreProperties(value = {"applications", "hibernateLazyInitializer"})
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public UserData userData;

    public String address;
    public LocalDate orderDate = LocalDate.now();

    public boolean isActive = true;

    public float totalPrice;

    public OrderData(UserData userData, Book book, String address, float totalPrice) {
        this.userData = userData;
        this.book = book;
        this.address = address;
        this.orderDate = getOrderDate();
        this.isActive = isActive();
        this.totalPrice = totalPrice;
    }
    public OrderData(UserData userData, Book book, String address, boolean isActive) {
        this.userData = userData;
        this.book = book;
        this.address = address;
        this.orderDate = getOrderDate();
        this.isActive = isActive;
        this.totalPrice = getTotalPrice();
    }
}

