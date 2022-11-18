package com.bookaholic.demo.controller;

import com.bookaholic.demo.entity.BookEntity;
import com.bookaholic.demo.entity.UserEntity;
import com.bookaholic.demo.model.UserPayload;
import com.bookaholic.demo.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/common")
public class CommonOperations {
    private final AccountService accountService;

    public CommonOperations(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> saveABook(@RequestBody UserPayload userPayload){
        // TODO: missing validation
        if(accountService.queryUserByUsername(userPayload.getUsername()) == null){
            accountService.saveUser(new UserEntity(userPayload));
            return new ResponseEntity<>(HttpStatus.OK);
        } else{
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

}
