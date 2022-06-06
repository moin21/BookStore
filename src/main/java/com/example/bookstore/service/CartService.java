package com.example.bookstore.service;

import com.example.bookstore.dto.CartDTO;
import com.example.bookstore.entity.Book;
import com.example.bookstore.entity.Cart;
import com.example.bookstore.entity.OrderData;
import com.example.bookstore.entity.UserData;
import com.example.bookstore.exception.CustomException;
import com.example.bookstore.repository.CartRepository;
import com.example.bookstore.repository.UserRepository;
import com.example.bookstore.util.TokenUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService {

    @Autowired
    IUserService iUserService;

    @Autowired
    IBookService iBookService;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenUtility tokenUtility;
    public Cart addToCart(CartDTO cartDTO, String token) {
        ArrayList<Book> bookList = new ArrayList<>();
        UserData userData = iUserService.getUserById(token);
        int userId = userData.getUserID();
        int cartId= 0;
        Cart cart = new Cart();
        if(cartRepository.findCartByUserId(userId)==null) {
            List<Integer> bookIdList = cartDTO.bookId;
            List<Integer> quantities = cartDTO.quantity;
            float totalPrice = 0;
            for (int i = 0; i < bookIdList.size(); i++) {
                if (quantities.get(i) <= iBookService.getById(bookIdList.get(i)).getQuantity()) {
                    bookList.add(iBookService.getById(bookIdList.get(i)));
                    totalPrice += iBookService.getById(bookIdList.get(i)).getPrice() * (quantities.get(i));

                } else
                    throw new CustomException("Please select a small quantity to order as stocks are limited: Current stock for book id: " + bookIdList.get(i) + " is " + iBookService.getById(bookIdList.get(i)).getQuantity() + ".");
            }
            cart = new Cart(userData, cartDTO.getBookId(), cartDTO.getQuantity());

        } else {
            cartId = cartRepository.findCartByUserId(userId).getCartId();
            cart = new Cart(cartId, userData, cartDTO.getBookId(), cartDTO.getQuantity());
        }
        return cartRepository.save(cart);
    }


    public String deleteFromCart(int id) {
        if (cartRepository.findById(id).isPresent()) {
            cartRepository.deleteById(id);
            return "Book with ID: " + id + " is Deleted Successfully!!";
        } else throw new CustomException("No book matches with the given ID");
    }
    public Cart getById(int id) {
        return cartRepository.findById(id).orElseThrow(() -> new CustomException("Book  with id " + id + " does not exist in database..!"));

    }
}