package org.example.client;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.example.database.ApplicationDatabase;
import org.jooq.DSLContext;
import org.springframework.stereotype.Component;

@Setter
@Getter
@Slf4j
@Component
public class ClientConnection {
    private ApplicationDatabase database;
    private DSLContext dslContext;

    public void disconnect() {
        if (database == null || database.getName() == null) {
            setDslContext(null);
            return;
        }
        String databaseName = database.getName();
        setDatabase(null);
        setDslContext(null);
        log.info("Disconnected from database {}.", databaseName);
    }

    public void status() {
        if (database == null) {
            log.info("You are not connected to a database.");
            return;
        }
        log.info("Your are connected to database {}", database.getName());
    }
}
