package com.learnspringboot.myJournalApp.controller;

import com.learnspringboot.myJournalApp.entity.User;
import com.learnspringboot.myJournalApp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin") // after adding this mapping we shall give admin right to user from database [add "ADMIN" in roles in user Document in database explicitly].
public class AdminController {

    @Autowired
    private UserService userService;

    //This endpoint extracts list of all users in db.
    @GetMapping("/all-users")
    public ResponseEntity<?> getAllUsers(){
        List<User> all = userService.getAll();
        if(all != null && !all.isEmpty()){
            return new ResponseEntity<>(all, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

//    1st Admin will be created manually from database by adding "ADMIN" in roles, and then multiple Admin can be added by below endpoint, since only Admin should have access to add new Admin.
    @PostMapping("/create-admin-user")
    public void createUser(@RequestBody User user){
        userService.saveAdmin(user);
    }

}
