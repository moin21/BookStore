package com.example.bookstore.controller;

import com.example.bookstore.dto.LoginDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/user")
@RestController
public class UserController {
    @Autowired
    IUserService iUserService;


    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> addUser(@RequestBody UserDTO userDTO) {
        ResponseDTO responseDTO = new ResponseDTO("UserData Added Successfully", iUserService.addUser(userDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get")
    public ResponseEntity<ResponseDTO> getById(@RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("UserData Retrieved Successfully", iUserService.getUserById(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getUserList(@RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("UserData List Retrieved Successfully", iUserService.getUserList(token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/login")
    public ResponseEntity<ResponseDTO> loginUser(@RequestBody LoginDTO loginDTO) {
        ResponseDTO responseDTO = new ResponseDTO("UserData Retrieved Successfully", iUserService.loginUser(loginDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}

