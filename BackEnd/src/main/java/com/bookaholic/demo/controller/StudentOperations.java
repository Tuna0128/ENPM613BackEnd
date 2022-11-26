package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.EnrollmentEntity;
import com.bookaholic.demo.model.BookPayload;
import com.bookaholic.demo.model.EnrollmentPayload;
import com.bookaholic.demo.service.AccountService;
import com.bookaholic.demo.service.BookService;
import com.bookaholic.demo.service.enrollmentService;
import com.bookaholic.demo.util.ValidateEnrollment;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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

    @GetMapping("/allbooks")
    public ResponseEntity<?> getAllBooks(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }
    
    @GetMapping("/books/enroll")
    public ResponseEntity<?> enrollBook(EnrollmentPayload enrollment){
    	String validation = ValidateEnrollment.validate(enrollment);
    	if (validation!="Success") {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", validation);
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}
        if(enrollmentService.saveEnrollment(new EnrollmentEntity(enrollment))){
        	Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Successfully enrolled!");
            return new ResponseEntity<>(body, HttpStatus.OK);
        } else {
        	Map<String, String> body = new HashMap<String, String>();
        	body.put("message", "Oops!Something went wrong.");
            return new ResponseEntity<>(body, HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    //TODO
    @GetMapping("/books/unenrollbook")
    public ResponseEntity<?> unenrollBook(UUID bookId){
    	BookEntity bookentity = bookService.queryBookById(bookId);
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }
    
    //TODO
    @GetMapping("/books/unenrollbooks")
    public ResponseEntity<?> unenrollBooks(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }
    
    //TODO
    @GetMapping("/books/viewenrolled")
    public ResponseEntity<?> viewEnrolledBook(){
        return new ResponseEntity<>(bookService.queryAllBooks(),HttpStatus.OK);
    }

}
