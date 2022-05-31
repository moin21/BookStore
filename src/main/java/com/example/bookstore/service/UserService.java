package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.entity.User;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;

    public User addUser(UserDTO userDTO) {

        User user = new User(userDTO);
        return userRepository.save(user);
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<User> existing = userRepository.findByLoginId(loginDTO.getLoginID());
        if (existing.isPresent()) {
            if (existing.get().getPassword().equals(loginDTO.getPassword())) {
                System.out.println("Login Successful");
                return "Login Successful";
            }
        }
        return "Login Credential Wrong";
    }


    public User getUserById(int id) {
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User  with id " + id + " does not exist in database..!"));

    }

    public List<User> getBookList() {
        if (userRepository.findAll().isEmpty()) {
            throw new CustomException("No Users in the list.");
        } else return userRepository.findAll();
    }


}
