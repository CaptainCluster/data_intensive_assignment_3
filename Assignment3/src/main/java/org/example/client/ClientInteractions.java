package org.example.client;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.database.DatabaseManager;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Slf4j
@Component
public class ClientInteractions {
    @Resource private DatabaseManager databaseManager;
    private final Scanner scanner = new Scanner(System.in);

    public void startClientInteractionLoop() {
        log.info("Welcome, friendo! Please start by selecting a database!");
        databaseManager.handleClientDatabaseSelection();

        while(Boolean.TRUE) {
            log.info("\nYour input: ");
            String clientInput = scanner.nextLine();

            switch (clientInput.toUpperCase()) {
                case "CONNECT" -> databaseManager.handleClientDatabaseSelection();
                case "DATABASES" -> databaseManager.listDatabasesByName();
                case "HELP" -> listCommands();
                case "EXIT" -> System.exit(0);
                default -> log.warn("Invalid input detected!");
            }
        }
    }

    private void listCommands() {
        Map<String, String> commands = Map.of(
                "CONNECT", "Connect to a database",
                "DATABASES", "List databases",
                "HELP", "List available commands",
                "EXIT", "End program"
        );

        log.info("Here is a list of available commands:");
        commands.forEach((command, description) -> {
            log.info("{} - {}", command, description);
        });
    }
}
