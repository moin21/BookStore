package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.repository.OrderRepository;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;
@Autowired
IUserService iUserService;
@Autowired
IBookService iBookService;

    public OrderData addOrder(OrderDTO orderDTO) {
        UserData userData = iUserService.getUserById(orderDTO.getUserId());
        Book book = iBookService.getById(orderDTO.getBookId());

        OrderData order = new OrderData(userData, book, orderDTO.address);
        return orderRepository.save(order);
    }
}