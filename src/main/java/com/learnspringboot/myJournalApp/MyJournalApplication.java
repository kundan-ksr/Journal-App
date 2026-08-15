package com.learnspringboot.myJournalApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.MongoTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement // <--- For enabling transaction feature (Rollback if not complete, i.e, atomic) across desired method in whole application.
public class MyJournalApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyJournalApplication.class, args);

//        ConfigurableApplicationContext context = SpringApplication.run(MyJournalApplication.class, args);
//        System.out.println(context.getEnvironment()); // this line prints which Profile is running i.e- dev / prod
    }

    /* Below methods helps achieve Atomic operation using @Transactional annotation, see "SaveEntry" method of JornalEntryService class.
    For details see SaveEntry method of JournalEntryService class.

            @Transactional
            public void saveEntry(JournalEntry journalEntry, String userName)
                {
                    ...
                }
    */

    @Bean
    public PlatformTransactionManager method_name(MongoDatabaseFactory dbFactory) {
        return new MongoTransactionManager(dbFactory);
    }

}
