package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.UserData;

import java.util.List;

public interface IUserService {
    public String loginUser(LoginDTO loginDTO);

    UserData getUserById(int id);

    List<UserData> getBookList();

    Object addUser(UserDTO userDTO);
}
