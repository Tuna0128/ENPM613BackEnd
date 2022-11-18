package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.model.BookPayload;
import com.bookaholic.demo.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/teacher")
public class TeacherOperations {
    private final BookService bookService;

    public TeacherOperations(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping("/postbook")
    public ResponseEntity<?> saveABook(@RequestBody BookPayload book){
        // TODO: missing validation
        if(bookService.saveBook(new BookEntity(book))){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
