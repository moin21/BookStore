package com.example.bookstore.controller;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/add")
    ResponseEntity<ResponseDTO> addToCart(@RequestBody OrderDTO orderDTO) {
        ResponseDTO response = new ResponseDTO("Product Added To Cart ", orderService.addOrder(orderDTO));
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
