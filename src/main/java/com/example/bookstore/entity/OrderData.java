package com.example.bookstore.entity;


import com.example.bookstore.dto.OrderDTO;
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

    public OrderData(UserData userData, Book book, String address) {
        this.userData = userData;
        this.book = book;
        this.address = address;
        this.orderDate = getOrderDate();
    }
}

