package org.example.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.database.DatabaseManager;
import org.example.repository.IncidentRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockdataUtils {

    @Resource private DatabaseManager databaseManager;
    @Resource private ClientConnection clientConnection;
    @Resource private IncidentRepository incidentRepository;

    public void generateMock() {
        try {
            log.info("Generating mock data for the databases...");
            generateDatabase1Mock();
            generateDatabase2Mock();
            generateDatabase3Mock();
            log.info("Mock data generation complete.");
        } catch(Exception e) {
            log.error("Failed to generate mock data. The error: {}",
                    e.getMessage(),
                    e);
        }
    }

    /**
     * Checking for the existence of data from a db to see if mock is needed.
     * Checks for the existence of a row of data in the incident table.
     */
    private boolean mockExists() {
        return incidentRepository.fetchAllIncidents().isEmpty();
    }

    private void generateDatabase1Mock() {
        databaseManager.establishConnectionForClient(0, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
    }
    private void generateDatabase2Mock() {
        databaseManager.establishConnectionForClient(1, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
    }
    private void generateDatabase3Mock() {
        databaseManager.establishConnectionForClient(2, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
    }
}
