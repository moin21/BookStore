package com.example.bookstore.entity;

import com.example.bookstore.dto.CartDTO;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
public class Cart {
    @ManyToOne(optional = true)
    Book book;
    @OneToOne
    User user;
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    public Cart(CartDTO cartDTO) {
        this.book = cartDTO.book;
        this.user = cartDTO.user;
    }

    public Cart(CartDTO cartDTO, int id) {
        this.book = getBook();
        this.user = getUser();
    }

    public Cart() {

    }
}
