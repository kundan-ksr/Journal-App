package com.learnspringboot.myJournalApp.service;

import com.learnspringboot.myJournalApp.entity.JournalEntry;
import com.learnspringboot.myJournalApp.repository.JournalEntryRepository;
import lombok.extern.slf4j.Slf4j;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component  // Creates Object of below class as bean,
            // so it can be used directly without initializing
            // the object of below class.
@Slf4j
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

//Create service
    public void saveEntry(JournalEntry journalEntry){
        try {
            journalEntry.setDate(LocalDateTime.now());
            journalEntryRepository.save(journalEntry);
        } catch (Exception e){
            log.error("Exception", e);
        }
    }

//Read All service
    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

//Read by id service
public Optional<JournalEntry> findById(ObjectId id) {
    return journalEntryRepository.findById(id);
}    //    public Optional<JournalEntry> findById(ObjectId id){
            //        return journalEntryRepository.findById(id).orElse(null);
            //    }

    public void deleteEntry(ObjectId id){
        journalEntryRepository.deleteById(id);
    }
}
