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

// Below instance is used when we don't annotate class with "@slf4j", if we annotate, we don't need below class, also instead of logger we can use "log.error(); of slf4j"
    // private static final Logger logger = LoggerFactory.getLogger(UserService.class); //This helps in storing the logs of mentioned class. (here UserService class is the identifier, it must be changed for correct logs acc. to required class.).

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    //Create service
    public boolean saveNewUser(User user) {
        try{
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRoles(Arrays.asList("USER"));
            userRepository.save(user);
            return true;
        } catch (Exception e) {

            log.error("Error occured"); // This line contains the message which will be stored in logs. it has various types as written below.
            log.error("Error occured for {} :", user.getUserName(), e); // here we have used placeholder {}, also without string concatenation i.e (without '+' symbol ).

//  logger is used when we use instance approach, with @slf4j approach we use "log.____()";

//            logger.warn("Error is here"); // All these gets printed in terminal by default.
//            logger.info("Error is here");
//            logger.debug("Error is here"); // Debug & Trace are not enabled by default, it needs customization to be enabled.
//            logger.trace("Error is here");
            return false;}
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
