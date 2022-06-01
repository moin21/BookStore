package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.entity.OrderData;

public interface IOrderService {
    public OrderData addOrder(OrderDTO orderDTO);
}
