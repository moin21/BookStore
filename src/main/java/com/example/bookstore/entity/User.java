package com.example.bookstore.entity;

import com.example.bookstore.dto.UserDTO;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@RequiredArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id", nullable = false)
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String password;
    @OneToOne
    Cart cart;

    public User(int id, UserDTO userDTO) {
        this.id = id;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }
    public User(User user){
        this.id = id;
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.password = user.getPassword();
    }

    public User(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.password = userDTO.getPassword();
    }
}
