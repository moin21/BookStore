package com.example.bookstore.service;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.email.EmailService;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    EmailService emailService;

    public String addUser(UserDTO userDTO) {

        UserData userData = new UserData(userDTO);
        userRepository.save(userData);
        String token = tokenUtility.createToken(userData.getUserID());
        emailService.sendEmail(userDTO.getEmail(), "Account Sign-up Successful", "Hello " + userData.getFirstName() + "Your account has been created. Your Token is " + token + " .Keep this token safe to access your account in future.");
        return token;
    }

    public String loginUser(LoginDTO loginDTO) {
        Optional<UserData> existing = userRepository.findByLoginId(loginDTO.getLoginID());
        if (existing.isPresent()) {
            if (existing.get().getPassword().equals(loginDTO.getPassword())) {
                System.out.println("Login Successful");
                return "Login Successful";
            }
        }
        return "Login Credential Wrong";
    }


    public UserData getUserById(String token) {
        int id = tokenUtility.decodeToken(token);
        return userRepository.findById(id).orElseThrow(() -> new CustomException("User  with id " + id + " does not exist in database..!"));

    }

    public List<UserData> getUserList(String token) {
        userRepository.findAll();
        if (getUserById(token).isAdmin()) {
            return userRepository.findAll();

        } else throw new CustomException("No Users in the list.");
    }


}
