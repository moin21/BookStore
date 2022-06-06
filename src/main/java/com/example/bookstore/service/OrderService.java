package com.example.bookstore.service;

import com.example.bookstore.dto.OrderDTO;
import com.example.bookstore.email.EmailService;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.BookRepository;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.OrderRepository;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    @Autowired
    TokenUtility tokenUtility;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    CartRepository cartRepository;

    public OrderData addOrder(String token, OrderDTO orderDTO) {

        ArrayList<Book> bookList = new ArrayList<>();
        UserData userData = iUserService.getUserById(token);
        List<Integer> bookIdList = orderDTO.bookId;
        List<Integer> quantities = orderDTO.quantity;
        float totalPrice = 0;
        for (int i = 0; i < bookIdList.size(); i++) {
            if (quantities.get(i) <= iBookService.getById(bookIdList.get(i)).getQuantity()) {
                bookList.add(iBookService.getById(bookIdList.get(i)));
                totalPrice += iBookService.getById(bookIdList.get(i)).getPrice() * (quantities.get(i));
                iBookService.updateQuantityById(bookIdList.get(i), iBookService.getById(bookIdList.get(i)).getQuantity() - (quantities.get(i)), token);
            } else
                throw new CustomException("Please select a small quantity to order as stocks are limited: Current stock for book id: " + bookIdList.get(i) + " is " + iBookService.getById(bookIdList.get(i)).getQuantity() + ".");
        }
        List<String> nameList = bookList.stream().map(Book::getName).toList();
        OrderData order = new OrderData(userData, orderDTO.getBookId(), orderDTO.getQuantity(), orderDTO.address);

        emailService.sendEmail(userData.getEmail(), "Order Created Successfully on ", "Order placed on" + orderDTO.getOrderDate() + " for books" + nameList + ". Total price is " + totalPrice);
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