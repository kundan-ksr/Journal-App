package com.learnspringboot.myJournalApp.service;

import com.learnspringboot.myJournalApp.entity.User;
import com.learnspringboot.myJournalApp.repository.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.bson.assertions.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName(){
        User user = userRepository.findByUserName("ram");
        assertNotNull(!user.getJournalEntries().isEmpty());
    }

    @Disabled // Disables the below function.
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "10,2,12",
            "20,6,28"
    })
    public void test(int a, int b, int expected){
        assertEquals(expected, a + b);
    }
}
