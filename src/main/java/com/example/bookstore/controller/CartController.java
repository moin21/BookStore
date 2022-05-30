package com.example.bookstore.controller;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.dto.UserDTO;
import com.example.bookstore.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {
    @Autowired
    ICartService iCartService;
    @PostMapping("/post")
    public ResponseEntity<ResponseDTO> addCart(@RequestBody CartDTO cartDTO) {
        ResponseDTO responseDTO = new ResponseDTO("User Added Successfully", iCartService.addCart(cartDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

//    @GetMapping("/get/{id}")
//    public ResponseEntity<ResponseDTO> getById(@PathVariable int id) {
//        ResponseDTO responseDTO = new ResponseDTO("User Retrieved Successfully", iCartService.ge(id));
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
//    }
}
