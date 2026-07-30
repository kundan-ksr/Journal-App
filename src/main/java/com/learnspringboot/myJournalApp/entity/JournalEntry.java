package com.learnspringboot.myJournalApp.entity;


import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

//Below written "@Getter & @Setter"
// annotation internally generates Getter & setter for below class variable's
// using project lombok plugin/dependency.
    //  @Getter
    //  @Setter

@Data   // Instead of "@Getter & Setter" we can call @Data which include both of these and also other necessary things.
@Document(collection = "journal_entries") //In mongodb/non-relational db collection = table
                        // Below type of class are called POJO Class - Plain Old Java Object
@NoArgsConstructor
public class JournalEntry {


    @Id
    private ObjectId id;
    @NonNull
    private String title;
    private String content;
    private LocalDateTime date;

}
