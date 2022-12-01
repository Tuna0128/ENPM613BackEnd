package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.DiscussionEntity;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.DiscussionPayload;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.service.AccountService;
import com.bookaholic.demo.service.BookService;
import com.bookaholic.demo.util.JwtUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.PrintWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/common")
public class CommonOperations {
    private final AccountService accountService;
    private final BookService bookService;
	@Autowired
	private JwtUtils jwtUtils;

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
    public ResponseEntity<Map<String, Object>> tokenLogin(@RequestHeader("token") String token){
    	
    	//System.out.println("login with token: " + token);
		Map<String, Object> userInfoMap = jwtUtils.tokenLogin(token);
		if (userInfoMap == null) {
			Map<String, Object> map = new HashMap<>();
			map.put("errorMsg", "token login failed");
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(map);
		} else {
			return ResponseEntity.ok(userInfoMap);
		}   	
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

    @GetMapping("/bookDetail/{bookId}/{userId}")
    public ResponseEntity<?> getBookDetail(@PathVariable UUID bookId, @PathVariable UUID userId){
        BookEntity book = bookService.queryBookById(bookId);
        UUID teacherId = book.getTeacherId();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("title", book.getTitle());
        map.put("year", book.getYear());
        map.put("author", book.getAuthor());
        map.put("categories", book.getCategories());
        map.put("coverLink", book.getCoverLink());
        map.put("description", book.getDescription());
        map.put("fileLink", book.getFileLink());
        map.put("isbn", book.getIsbn());
        map.put("pages", book.getPages());
        map.put("publisher", book.getPublisher());
        map.put("subTitle", book.getSubTitle());
        map.put("teacherId", teacherId);
        map.put("teacherName", accountService.queryUserByUserId(teacherId).getUsername());
        if (userId.equals(teacherId)){
            map.put("enrolled", 0);
        }else if (bookService.isEnrolled(userId, bookId)){
            map.put("enrolled", 1);
        } else{
            map.put("enrolled", 2);
        }
        map.put("enrolledAmount", bookService.enrolledAmount(bookId));
        return ResponseEntity.ok(map);
    }

}
