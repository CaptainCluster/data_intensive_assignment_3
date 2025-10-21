package org.example.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;


@Slf4j
@Builder
@Getter
@AllArgsConstructor
public class ApplicationDatabase {
    private Connection connection;
    private String name;
    private String url;

    private final String username = System.getProperty("DB_USERNAME");
    private final String password = System.getProperty("DB_PASSWORD");

    /**
     * Database connection source that I used
     * https://javanexus.com/blog/mastering-multi-db-connections-java
     */
    public void establishConnection() {
        try {
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error("ERROR! Failed to establish connection to database {}. The error was: ", name, e);
        }
    }
}
