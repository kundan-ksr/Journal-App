package com.learnspringboot.myJournalApp.entity;


import lombok.Data;
import lombok.NonNull;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

//Below written "@Getter & @Setter"
// annotation internally generates Getter & setter for below class variable's
// using project lombok plugin/dependency.
    //  @Getter
    //  @Setter

@Data   // Instead of "@Getter & Setter" we can call @Data which include both of these and also other necessary things.
@Document(collection = "users") //In mongodb/non-relational db collection = table
// Below type of class are called POJO Class - Plain Old Java Object
public class User {


    @Id
    private ObjectId id;

    @Indexed(unique = true) // Indexing is not done by default even after writing this line,
                            // it can be done manually in mongodb OR it can be done by writing
                            // a command in "application properties" file  ------> [spring.data.mongodb.auto-index-creation=true]
    @NonNull
    private String userName;

    @NonNull
    private String password;

    @DBRef // this creates a reference of journal entries which is stored in user's database as a reference.
            // for detail see video 16 of playlist (Engineering Digest).
    private List<JournalEntry> journalEntries = new ArrayList<>();
}
