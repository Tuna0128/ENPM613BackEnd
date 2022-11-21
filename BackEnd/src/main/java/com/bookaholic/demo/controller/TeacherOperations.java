package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.model.BookPayload;
import com.bookaholic.demo.service.BookService;
import com.bookaholic.demo.util.ValidateBook;

import java.util.UUID;
import java.util.Map;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    	
    	String validation = ValidateBook.validate(book);
    	if(validation!="Success") {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", validation);
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}	
    	
        if(bookService.saveBook(new BookEntity(book))){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    @PostMapping("/editbook")
    public ResponseEntity<?> editABook(@RequestParam("oldBook") UUID uuid, @RequestBody BookPayload book){
    	if(uuid==null) {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", "no uuid of old book given");
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}
    	BookEntity oldBook = bookService.queryBookById(uuid);
    	if(oldBook==null || oldBook.getBookId()==null) {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", "no book found with given uuid");
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}
    	String validation = ValidateBook.validate(book);
    	if(validation!="Success") {
    		Map<String, String> body = new HashMap<String, String>();
    		body.put("message", validation);
    		return new ResponseEntity<>(body, HttpStatus.NOT_ACCEPTABLE);
    	}
    	bookService.deleteBook(oldBook);
    	if(bookService.saveBook(new BookEntity(book))){
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
}
