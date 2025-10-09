package org.example.client;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.database.DatabaseManager;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Slf4j
@Component
public class ClientInteractions {
    @Resource private DatabaseManager databaseManager;
    private final Scanner scanner = new Scanner(System.in);

    public void startClientInteractionLoop() {

        log.info("Welcome, friendo! Please start by selecting a database!");

        while(Boolean.TRUE) {
            log.info("Your input: ");
            String clientInput = scanner.nextLine();

            switch (clientInput.toUpperCase()) {
                case "SELECT" -> log.info("wee");
                case "LIST" -> databaseManager.listDatabasesByName();
                case "EXIT" -> System.exit(0);
                default -> log.warn("Invalid input detected!");
            }
        }
    }
}
