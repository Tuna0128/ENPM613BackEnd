package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.DiscussionEntity;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.DiscussionPayload;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.service.AccountService;
import com.bookaholic.demo.service.BookService;
import com.bookaholic.demo.util.JwtUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
        	Integer role = accountService.getUserRoleByUsername(username);
        	// Create token
        	String token = JwtUtils.createToken(userId, username);
        	Map<String, Object> map = new HashMap<String, Object>();
        	map.put("userId", userId);
        	map.put("username", username);
        	map.put("role", role);
        	map.put("token", token);
        	return ResponseEntity.ok(map);
        } else {
        	return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
    
    @GetMapping("/tokenlogin")
    public ResponseEntity<?> tokenLogin(){
    	return new ResponseEntity<>(HttpStatus.OK);
    }
    
    
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserPayload userPayload){
    	// User payload validation
    	if(userPayload.getUsername() == null || userPayload.getUsername().length() == 0 || 
    			userPayload.getPassword() == null || userPayload.getPassword().length() ==0 || 
    			(userPayload.getRole() != 0 && userPayload.getRole() != 1))
    		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
    	
    	// Save user if user name is not duplicate
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
    public ResponseEntity<List<Map<String, Object>>> listDiscussion(@RequestParam("bookId") UUID bookId){
    	List<Map<String, Object>> discussionList = bookService.queryDiscussionByBookId(bookId);
    	return ResponseEntity.ok(discussionList);
    }

    @GetMapping("/bookDetail")
    public ResponseEntity<?> getBookDetail(@RequestParam("bookId") UUID bookId){
        return new ResponseEntity<>(bookService.queryBookById(bookId),HttpStatus.OK);
    }

}
