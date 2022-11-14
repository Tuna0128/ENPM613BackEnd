package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.UserRepository;
import com.example.demo.entity.User;

@Controller
public class UserController {
    @Autowired
    private UserRepository userRepo;
       
    @GetMapping("/users")
    public String listAll(Model model) {
        List<User> listUsers = userRepo.findAll();
        model.addAttribute("listUsers", listUsers);
           
        return "users";
    }
       
}