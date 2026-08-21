package com.learnspringboot.myJournalApp.service;

import com.learnspringboot.myJournalApp.entity.JournalEntry;
import com.learnspringboot.myJournalApp.entity.User;
import com.learnspringboot.myJournalApp.repository.JournalEntryRepository;
import com.learnspringboot.myJournalApp.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

// @Component  // Creates Object of below class as bean,
// so it can be used directly without initializing
// the object of below class.

@Service // It also Creates Object of below class as bean, but just for user understanding that it is a service class, we can use @Service in abc_Service classes instead of @Component
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    //Create service
    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName) {
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());
            JournalEntry saved = journalEntryRepository.save(journalEntry);
            user.getJournalEntries().add(saved);
            //here if we add this line "user.setUserName(null);" then below line will not execute, but above lines already got executed and JournalEntry data got saved in journal_entries, but it didn't get updated in User,
            // hence there is no atomicity and data may not be correct, so we will make it as Transaction(Follows Atomicity property of ACID)
            //To resolve this problem we use @Transactional above this.
            // user.setUserName(null);
            userService.saveUser(user);
        } catch (Exception e) {
            log.error("Exception", e);
            throw new RuntimeException("An error occurred while saving the entry.", e);
        }
    }

    //Update service - without userName parameter, to just update the content & not interfere with user details/table.
    public void saveEntry(JournalEntry journalEntry) {
        try {
            journalEntryRepository.save(journalEntry);
        } catch (Exception e) {
            log.error("Exception", e);
        }
    }

    //Read All service
    public List<JournalEntry> getAll() {
        return journalEntryRepository.findAll();
    }

    //Read by id service
    public Optional<JournalEntry> findById(ObjectId id) {
        return journalEntryRepository.findById(id);
    }    //    public Optional<JournalEntry> findById(ObjectId id){
    //        return journalEntryRepository.findById(id).orElse(null);
    //    }

    // Delete service
    @Transactional
    public boolean deleteById(ObjectId id, String userName) {
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed) {
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
            return removed;
        } catch (Exception e) {
            log.error("Error: ", e);
            throw new RuntimeException("An error occurred while deleting the entry.", e);
        }
    }
}
