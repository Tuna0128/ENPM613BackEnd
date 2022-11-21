package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.DiscussionEntity;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.DiscussionPayload;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.service.AccountService;
import com.bookaholic.demo.service.BookService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonOperations {
    private final AccountService accountService;
    private final BookService bookService;

    public CommonOperations(AccountService accountService, BookService bookService) {
        this.accountService = accountService;
		this.bookService = bookService;
    }

    @GetMapping("/login")
    public ResponseEntity<?> login(@RequestParam("username") String username, 
    		@RequestParam("password") String password){
        Boolean res = accountService.loginAuthenticationByInput(username, password);
        if(res) {
        	UUID userId = accountService.getUserIdByUsername(username);
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("userId", userId);
        	return ResponseEntity.ok(map);
        } else {
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserPayload userPayload){
        if(accountService.queryUserByUsername(userPayload.getUsername()) == null){
            Boolean res = accountService.saveUser(new UserEntity(userPayload));
            if(res)          	
            	return new ResponseEntity<>(HttpStatus.CREATED);
            else
            	return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    
    @PostMapping("/discussion/add")
    public ResponseEntity<?> addDiscussion(@RequestBody DiscussionPayload discussionPayload){
    	Boolean res = bookService.saveDiscussion(new DiscussionEntity(discussionPayload));
    	if(res)
    		return new ResponseEntity<>(HttpStatus.CREATED);
    	else
    		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    }
    
    @GetMapping("/discussion/list")
    public ResponseEntity<List<DiscussionEntity>> listDiscussion(@RequestParam("bookId") UUID bookId){
    	List<DiscussionEntity> discussionList = bookService.queryDiscussionByBookId(bookId);
    	return ResponseEntity.ok(discussionList);
    }

}
