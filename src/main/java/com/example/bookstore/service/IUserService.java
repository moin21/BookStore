package com.example.bookstore.service;

import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.User;

import java.util.List;

public interface IUserService {
    User addUser(UserDTO userDTO);

    User getUserById(int id);

    List<User> getBookList();
}
