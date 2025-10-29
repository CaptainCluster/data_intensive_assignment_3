package org.example;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientInteractions;
import org.example.database.DatabaseManager;
import org.example.utils.MockdataUtils;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * I used the following source for running Spring from command line
 * https://www.geeksforgeeks.org/advance-java/spring-boot-commandlinerunner-and-applicationrunner/
 */
@Slf4j
@Component
public class CLIRunner implements CommandLineRunner {
    @Resource private ClientInteractions clientInteractions;
    @Resource private DatabaseManager databaseManager;
    @Resource private MockdataUtils mockdataUtils;

    @Override
    public void run(String[] args) {
        databaseManager.setUpDatabases();
        mockdataUtils.generateMock();
        clientInteractions.startClientInteractionLoop();
    }
}
