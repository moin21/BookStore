package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.User;

import java.util.List;

public interface IUserService {
    public String loginUser(LoginDTO loginDTO);

    User getUserById(int id);

    List<User> getBookList();

    Object addUser(UserDTO userDTO);
}
