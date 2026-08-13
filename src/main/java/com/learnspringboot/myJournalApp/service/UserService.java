package com.learnspringboot.myJournalApp.service;

import com.learnspringboot.myJournalApp.entity.User;
import com.learnspringboot.myJournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component  // Creates Object of below class as bean,
// so it can be used directly without initializing
// the object of below class.
@Slf4j
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //Create service
    public void saveNewUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        userRepository.save(user);
    }
    //Create Admin
    public void saveAdmin(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER", "ADMIN"));
        userRepository.save(user);
    }


    public void saveUser(User user) { userRepository.save(user); }
    //Read All service
    public List<User> getAll() {
        return userRepository.findAll();
    }

    //Read by id service
    public Optional<User> findById(ObjectId id) {
        return userRepository.findById(id);
    }

    public void deleteEntry(ObjectId id) {
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        return userRepository.findByUserName(userName);
    }
}
