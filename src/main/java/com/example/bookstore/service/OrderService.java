package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.email.EmailService;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService implements IOrderService {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    IUserService iUserService;
    @Autowired
    IBookService iBookService;
@Autowired
    EmailService emailService;
    public OrderData addOrder(OrderDTO orderDTO) {
        UserData userData = iUserService.getUserById(orderDTO.getUserId());
        Book book = iBookService.getById(orderDTO.getBookId());
        float totalPrice = book.getQuantity()* book.getPrice();
        OrderData order = new OrderData(userData, book, orderDTO.address, totalPrice);
        emailService.sendEmail(userData.getEmail(), "Order Created Successfully!!", "Your order for book "+book.getName()+" is placed successfully. Total Price is "+totalPrice+".");
        return orderRepository.save(order);
    }

    public OrderData getById(int id) {
        return orderRepository.findById(id).orElseThrow(() -> new CustomException("Order  with id " + id + " does not exist in database..!"));

    }
    public List<OrderData> getAllOrders() {
        if (orderRepository.findAll().isEmpty()) {
            throw new CustomException("No Orders existing.");
        } else return orderRepository.findAll();
    }

    public OrderData cancelOrder(int id) {
        OrderData order = getById(id);
        order.isActive = false;
        return order;
    }

}