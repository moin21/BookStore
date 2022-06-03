package com.example.bookstore.controller;

import com.example.bookstore.dto.BookDTO;
import com.example.bookstore.dto.ResponseDTO;
import com.example.bookstore.service.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/book")
@RestController
public class BookController {
    @Autowired
    IBookService iBookService;

    @PostMapping("/post/")
    public ResponseEntity<ResponseDTO> addBook(@RequestBody BookDTO bookDTO, @RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("Book Added Successfully", iBookService.addBook(token, bookDTO));
        return new ResponseEntity<>(responseDTO, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable int id) {
        ResponseDTO responseDTO = new ResponseDTO("Book Retrieved Successfully", iBookService.getById(id));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/get-all")
    public ResponseEntity<ResponseDTO> getById() {
        ResponseDTO responseDTO = new ResponseDTO("Book List Retrieved Successfully", iBookService.getBookList());
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ResponseDTO> deleteById(@PathVariable int id, @RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("Book Deleted Successfully", iBookService.deleteById(id, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ResponseDTO> updateById(@PathVariable int id, @RequestBody BookDTO bookDTO, @RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("Book Updated Successfully", iBookService.updateById(id, bookDTO, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/getByName/{name}")
    public ResponseEntity<ResponseDTO> getByName(@PathVariable String name) {
        ResponseDTO responseDTO = new ResponseDTO("Book Retrieved Successfully", iBookService.findBookByName(name));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @GetMapping("/sortBy/{field}")
    public ResponseEntity<ResponseDTO> sortByField(@PathVariable String field) {

        ResponseDTO responseDTO = new ResponseDTO("Sorted List of Books by" + field + " Retrieved Successfully", iBookService.sortBookList(field));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/updateQuantity/{id}/{quantity}")
    public ResponseEntity<ResponseDTO> updateQuantityById(@PathVariable int id, @PathVariable int quantity, @RequestParam String token) {
        ResponseDTO responseDTO = new ResponseDTO("Book Quantity Updated Successfully", iBookService.updateQuantityById(id, quantity, token));
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
