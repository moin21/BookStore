package com.example.bookstore.entity;

import com.example.bookstore.dto.LoginDTO;
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
    private int userID;
    private String firstName;
    private String lastName;
    private String email;
    private String address;
    @Column(name = "login_id", nullable = false)
    private String loginID;
    private String password;


    public User(int id, UserDTO userDTO) {
        this.userID = id;
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.loginID = userDTO.getLoginID();
        this.password = userDTO.getPassword();
    }

    public User(User user) {
        this.userID = user.getUserID();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.address = user.getAddress();
        this.loginID = user.getLoginID();
        this.password = user.getPassword();
    }

    public User(UserDTO userDTO) {
        this.firstName = userDTO.getFirstName();
        this.lastName = userDTO.getLastName();
        this.email = userDTO.getEmail();
        this.address = userDTO.getAddress();
        this.loginID = userDTO.getLoginID();
        this.password = userDTO.getPassword();
    }

    public User(LoginDTO loginDTO) {
        this.loginID = loginDTO.getLoginID();
        this.password = loginDTO.getPassword();
    }
}
