package org.example.database;


import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.jooq.impl.DSL;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Slf4j
@Component
public class DatabaseManager {
    @Resource private ClientConnection clientConnection;

    private List<ApplicationDatabase> databases;
    private final Scanner scanner = new Scanner(System.in);

    private final static String DB_ALPHA = "Alpha";
    private final static String DB_BRAVO = "Bravo";
    private final static String DB_CHARLIE = "Charlie";

    /**
     * Creates three databases: Alpha, Bravo, Charlie
     */
    public void setUpDatabases() {
        log.info("Getting the databases ready...");
        databases = List.of(
                ApplicationDatabase.builder().name(DB_ALPHA).url(System.getProperty("DB_URL_1")).build(),
                ApplicationDatabase.builder().name(DB_BRAVO).url(System.getProperty("DB_URL_2")).build(),
                ApplicationDatabase.builder().name(DB_CHARLIE).url(System.getProperty("DB_URL_3")).build()
        );
        databases.forEach(ApplicationDatabase::establishConnection);
        log.info("The databases are on.");
    }

    public void handleClientDatabaseSelection() {
        listDatabasesByName();

        log.info("Which database would you like to select?");
        log.info("\nDatabase number: ");
        String clientInput = scanner.nextLine();

        int parsedInput;
        try {
            parsedInput = Integer.parseInt(clientInput);
        } catch (Exception e) {
            log.warn("Invalid input. Ensure you enter a number when selecting a database!");
            return;
        }

        if (parsedInput > databases.size() || parsedInput < 1) {
            log.warn("A database of number {} does not exist. Connection failed.", parsedInput);
        }
        establishConnectionForClient(parsedInput-1, Boolean.FALSE); // Minus 1 to convert into an index for list
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

    public void establishConnectionForClient(int databaseIndex, boolean isUsedForMock) {
        try {
            if (databaseIndex > databases.size()-1 || databaseIndex < 0) {
                log.warn("The given number does not match a valid database number!");
                return;
            }
            clientConnection.disconnect();
            clientConnection.setDatabase(databases.get(databaseIndex));
            if (!isUsedForMock) {
                log.info(clientConnection.getDatabase().getUrl());
            }
            clientConnection.setDslContext(DSL.using(
                    clientConnection.getDatabase().getConnection()
            ));
            if (!isUsedForMock) {
                log.info("Connection has been established to database {}.", clientConnection.getDatabase().getName());
            }
        } catch (Exception e) {  // Mainly to catch issues due to handling via index
            clientConnection.disconnect();
            log.warn("Encountered an error when attempting to establish a connection to database: ", e);
        }
    }
}
