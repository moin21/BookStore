package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.OrderData;

import java.util.List;

public interface IOrderService {
    OrderData addOrder(String token, OrderDTO orderDTO);
    public OrderData cancelOrder(int id);
    public List<OrderData> getAllOrders();
    public OrderData getById(int id);
}
