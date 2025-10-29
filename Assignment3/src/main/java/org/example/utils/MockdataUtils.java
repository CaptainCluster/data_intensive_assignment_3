package org.example.utils;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.database.DatabaseManager;
import org.example.dto.*;
import org.example.repository.*;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class MockdataUtils {

    @Resource private DatabaseManager databaseManager;
    @Resource private ClientConnection clientConnection;
    @Resource private IncidentRepository incidentRepository;
    @Resource private ShopRepository shopRepository;
    @Resource private ProductRepository productRepository;
    @Resource private WarehouseRepository warehouseRepository;
    @Resource private EmployeeRepository employeeRepository;

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
     * Contains a total of 25 data entries
     */
    private void generateCommonMockData() {
        // Eight shops (two unique shop left for each database)
        ShopDTO shopDTO1 = shopRepository.createShop(
                ShopDTO.builder().name("Mockshop").location("Mockville").build()
        );
        ShopDTO shopDTO2 = shopRepository.createShop(
                ShopDTO.builder().name("Mock Generals").location("Mockburg").build()
        );
        ShopDTO shopDTO3 = shopRepository.createShop(
                ShopDTO.builder().name("Mock Irons").location("Mockstreet").build()
        );
        ShopDTO shopDTO4 = shopRepository.createShop(
                ShopDTO.builder().name("Mockery Store").location("Mocktown").build()
        );
        ShopDTO shopDTO5 = shopRepository.createShop(
                ShopDTO.builder().name("S-Market Sammonlahti").location("lappeen Rannat").build()
        );
        ShopDTO shopDTO6 = shopRepository.createShop(
                ShopDTO.builder().name("Prisma").location("lappeen Rannat").build()
        );
        ShopDTO shopDTO7 = shopRepository.createShop(
                ShopDTO.builder().name("Abandoned Store").location("Kouvostoliitto").build()
        );
        ShopDTO shopDTO8 = shopRepository.createShop(
                ShopDTO.builder().name("Sale").location("YOLON lähellä").build()
        );

        // Five products
        productRepository.createProduct(
                ProductDTO.builder().name("Cucumber").price(1).quantity(20).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Electric Guitar").price(300).quantity(2).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("IKEA Shelf").price(150).quantity(5).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Teddy Bear").price(10).quantity(200).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Mystery Box").price(9999).quantity(1).build()
        );

        // Six warehouses
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO6.getId()).isFull(Boolean.TRUE)
                        .quantity(30).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.FALSE)
                        .quantity(20).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO2.getId()).isFull(Boolean.TRUE)
                        .quantity(40).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO3.getId()).isFull(Boolean.FALSE)
                        .quantity(10).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO4.getId()).isFull(Boolean.FALSE)
                        .quantity(33).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO5.getId()).isFull(Boolean.FALSE)
                        .quantity(20).build());

        // Four employees
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2000).title("Janitor").name("Mister Siivooja").isFired(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3000).title("Cashier").name("Fired Frankie").isFired(Boolean.TRUE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(5000).title("Manager").name("Manager Moe").isFired(Boolean.FALSE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2500).title("Cashier").name("John Doe").isFired(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );

        // Two incidents
        incidentRepository.createIncident(
                IncidentDTO.builder().title("STOLEN ITEM!!!").description("SOMEONE STOLE A BANANA!").isHandled(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("The building has disappeared!").description("Tsunami hit Skinnarila last night! The store, along the university campus, has been washed away!").isHandled(Boolean.FALSE).shopId(shopDTO8.getId()).build()
        );
    }
}
