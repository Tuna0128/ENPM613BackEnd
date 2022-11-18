package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.model.BookPayload;
import com.bookaholic.demo.service.AccountService;
import com.bookaholic.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentOperations {
    private final AccountService accountService;
    private final BookService bookService;

    public StudentOperations(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }

}
