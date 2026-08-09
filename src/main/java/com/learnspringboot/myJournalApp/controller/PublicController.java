package com.learnspringboot.myJournalApp.controller;

import com.learnspringboot.myJournalApp.entity.User;
import com.learnspringboot.myJournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/public")
public class PublicController {

    @Autowired
    private UserService userService;

    @GetMapping("/health-check")
    public String healthCheck() {
        return "OK";
    }

    @PostMapping
    public void createUser(@RequestBody User user){
        userService.saveEntry(user);
    }
}
