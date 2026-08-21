package com.learnspringboot.myJournalApp.controller;

import com.learnspringboot.myJournalApp.api.response.WeatherResponse;
import com.learnspringboot.myJournalApp.entity.User;
import com.learnspringboot.myJournalApp.repository.UserRepository;
import com.learnspringboot.myJournalApp.service.UserService;
import com.learnspringboot.myJournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WeatherService weatherService;

// Below endpoint is removed since User creation and starting of Spring Security.
// Removed getAllUser because we want only admin to view user's not anyone unauthorized.
//    @GetMapping
//    public List<User> getAllUsers(){
//        return userService.getAll();
//    }


//    For making a New User no authorization is required, so below method is sent in "Public
//    Controller ~ which open for all without authorization"
//    @PostMapping
//    public void crateUser(@RequestBody User user){
//        userService.saveEntry(user);
//    }


    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); // this authenticates user itself and does need "userName" as path variable, Spring Security handles credentials matching.
        String userName = authentication.getName();
        User userInDb = userService.findByUserName(userName);
        userInDb.setUserName(user.getUserName());
        userInDb.setPassword(user.getPassword());
        userService.saveNewUser(userInDb);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping
    public ResponseEntity<?> deleteUserById(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        userRepository.deleteByUserName(authentication.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greetings(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        WeatherResponse weatherResponse = weatherService.getWeather("Patna");
        String greeting = "";

        if(weatherResponse!=null){
            greeting = ", Weather feels like " + weatherResponse.getCurrent().getFeelslike();
        }

        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }

}