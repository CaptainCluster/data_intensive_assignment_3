package org.example.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.database.DatabaseManager;
import org.example.dto.ShopDTO;
import org.example.repository.IncidentRepository;
import org.example.repository.ShopRepository;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockdataUtils {

    @Resource private DatabaseManager databaseManager;
    @Resource private ClientConnection clientConnection;
    @Resource private IncidentRepository incidentRepository;
    @Resource private ShopRepository shopRepository;

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
     * Checks for the existence of a row of data in the shop table.
     */
    private boolean mockExists() {
        return !shopRepository.fetchAllShops().isEmpty();
    }

    private void generateDatabase1Mock() {
        databaseManager.establishConnectionForClient(0, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
        generateCommonMockData();
    }
    private void generateDatabase2Mock() {
        databaseManager.establishConnectionForClient(1, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
        generateCommonMockData();
    }
    private void generateDatabase3Mock() {
        databaseManager.establishConnectionForClient(2, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
        generateCommonMockData();
    }

    /**
     * Half of the total generated mock data. All databases share this.
     */
    private void generateCommonMockData() {
        // Eight shops (two unique shop left for each database)
        shopRepository.createShop(
                ShopDTO.builder().name("Mockshop").location("Mockville").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("Mock Generals").location("Mockburg").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("Mock Irons").location("Mockstreet").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("Mockery Store").location("Mocktown").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("S-Market Sammonlahti").location("lappeen Rannat").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("Prisma").location("lappeen Rannat").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("Abandoned Store").location("Kouvostoliitto").build()
        );
        shopRepository.createShop(
                ShopDTO.builder().name("Sale").location("YOLON lähellä").build()
        );
    }
}
