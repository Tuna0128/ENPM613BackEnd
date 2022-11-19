package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.service.AccountService;

import java.util.HashMap;
import java.util.Map;

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

    public CommonOperations(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/login")
    public Map login(@RequestParam("username") String username, 
    		@RequestParam("password") String password){
        Boolean res = accountService.loginAuthenticationByInput(username, password);
        Map<String, Boolean> map=new HashMap<String, Boolean>();
        return map;
    }
    
    @PostMapping("/signup")
    public ResponseEntity<?> signUp(@RequestBody UserPayload userPayload){
        if(accountService.queryUserByUsername(userPayload.getUsername()) == null){
            accountService.saveUser(new UserEntity(userPayload));
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
