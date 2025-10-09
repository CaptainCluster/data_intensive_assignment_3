package org.example.database;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@Component
public class DatabaseManager {
    private List<ApplicationDatabase> databases;

    private final static String DB_ALPHA = "Alpha";
    private final static String DB_BRAVO = "Bravo";
    private final static String DB_CHARLIE = "Charlie";


    /**
     * Creates three databases: Alpha, Bravo, Charlie
     */
    public void createDatabases() {
        log.info("Creating the databases...");
        databases = List.of(
                ApplicationDatabase.builder().name(DB_ALPHA).build(),
                ApplicationDatabase.builder().name(DB_BRAVO).build(),
                ApplicationDatabase.builder().name(DB_CHARLIE).build()
        );

        log.info("THE FOLLOWING DATABASES HAVE BEEN CREATED...");
        listDatabasesByName();
    }

    public void listDatabasesByName() {
        if (databases.isEmpty()) {
            log.warn("No databases were found.");
            return;
        }

        for (int i = 0; i < databases.size(); i++) {
            log.info("Database {} - {}", i+1, databases.get(i).getName());
        }
    }
}
