package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.UserData;

import java.util.List;

public interface IUserService {
    String loginUser(LoginDTO loginDTO);

    UserData getUserById(String token);

    List<UserData> getBookList();

    public String addUser(UserDTO userDTO);
}
