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

        // Five shops
        ShopDTO shopDTO1 = shopRepository.createShop(
                ShopDTO.builder().name("Kauppa").location("New York City, USA").build()
        );
        ShopDTO shopDTO2 = shopRepository.createShop(
                ShopDTO.builder().name("R-Kioski").location("Rovaniemi, Finlandia").build()
        );
        ShopDTO shopDTO3 = shopRepository.createShop(
                ShopDTO.builder().name("Prisma").location("lappeen Rannat").build()
        );
        ShopDTO shopDTO4 = shopRepository.createShop(
                ShopDTO.builder().name("Abandoned Store").location("Kouvostoliitto").build()
        );
        ShopDTO shopDTO5 = shopRepository.createShop(
                ShopDTO.builder().name("Sale").location("YOLON lähellä").build()
        );

        // Five products
        productRepository.createProduct(
                ProductDTO.builder().name("Pumpkin").price(2).quantity(24).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Villager Minecraft plushy toy").price(20).quantity(6).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Teekkarilakki").price(999999).quantity(0).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Spoon bundle").price(15).quantity(5).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Pea soup").price(1).quantity(35).build()
        );

        // Five warehouses
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO5.getId()).isFull(Boolean.TRUE)
                        .quantity(100).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.FALSE)
                        .quantity(120).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO2.getId()).isFull(Boolean.TRUE)
                        .quantity(140).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO3.getId()).isFull(Boolean.FALSE)
                        .quantity(110).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO4.getId()).isFull(Boolean.FALSE)
                        .quantity(133).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO5.getId()).isFull(Boolean.FALSE)
                        .quantity(110).build());

        // Five employees
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2000).title("Cashier").name("Garrison Word").isFired(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(600).title("Cashier").name("James Jameson").isFired(Boolean.TRUE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(5).title("Cashier").name("Manuel Manuelson").isFired(Boolean.FALSE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3000).title("Security").name("Solid Snake").isFired(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2560).title("Manager").name("Bored Yeltsin").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2222).title("Manager").name("Leonardo Pretzel").isFired(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );

        // Five incidents
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Grief attempt").description("Someone tried to dynamite-pressure plate the store. Glad it was made out of bedrock.").isHandled(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("SPAM SPAM SPAM").description("Fake incident. Complete spam!").isHandled(Boolean.TRUE).shopId(shopDTO2.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Bored Yeltsin incident").description("Bored Yeltsin arrived drunk at work yet again! This is against company policy. He has been fired.").isHandled(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Bored Yeltsin strikes back").description("Bored Yeltsin, after getting fired, has invaded the store! The security team has saved the day!").isHandled(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Leonardo Pretzel missing").description("Updated soon...").isHandled(Boolean.FALSE).shopId(shopDTO5.getId()).build()
        );
    }

    private void generateDatabase2Mock() {
        databaseManager.establishConnectionForClient(1, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
        generateCommonMockData();

        // Five shops
        ShopDTO shopDTO1 = shopRepository.createShop(
                ShopDTO.builder().name("People's Republic of Book Store").location("Moscow, Russia").build()
        );
        ShopDTO shopDTO2 = shopRepository.createShop(
                ShopDTO.builder().name("Raccoon City Convenience Store").location("Raccoon City, USA").build()
        );
        ShopDTO shopDTO3 = shopRepository.createShop(
                ShopDTO.builder().name("Osaka Manga Store").location("Osaka, Japan").build()
        );
        ShopDTO shopDTO4 = shopRepository.createShop(
                ShopDTO.builder().name("Kwik-E Mart").location("Springfield").build()
        );
        ShopDTO shopDTO5 = shopRepository.createShop(
                ShopDTO.builder().name("Sale Niinistön kauppa").location("YOLON lähellä").build()
        );

        // Five products
        productRepository.createProduct(
                ProductDTO.builder().name("Pineapple pizza").price(5).quantity(22).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Microphone").price(66).quantity(6).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Napkins").price(2).quantity(11).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Winter coat").price(112).quantity(4).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Comic book").price(4).quantity(25).build()
        );

        // Five warehouses
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.TRUE)
                        .quantity(200).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.TRUE)
                        .quantity(220).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.TRUE)
                        .quantity(240).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO3.getId()).isFull(Boolean.TRUE)
                        .quantity(210).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO3.getId()).isFull(Boolean.TRUE)
                        .quantity(233).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO4.getId()).isFull(Boolean.TRUE)
                        .quantity(210).build());

        // Five employees
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2600).title("Cashier").name("Richard Richardson").isFired(Boolean.TRUE).shopId(shopDTO1.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3000).title("Security").name("Mario Manson").isFired(Boolean.TRUE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(5000).title("Manager").name("Michael Michelle").isFired(Boolean.TRUE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3000).title("Cashier").name("Adam Adamson").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3560).title("Security").name("Charlie Charlie").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3333).title("Cashier").name("William Williamson").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );

        // Five incidents
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Everyone fired!?").description("Why's everyone suddenly fired!?").isHandled(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Please hire people!").description("Staff shortage! No cashiers").isHandled(Boolean.FALSE).shopId(shopDTO2.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Items being stolen!").description("With no security, the store is being infested by thieves.").isHandled(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Bigfoot sighting").description("Even bigfoot has started stealing items from the store!").isHandled(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Alien invasion").description("Now even aliens are stealing our items! WILL SOMEONE SAVE THE STORE!?").isHandled(Boolean.FALSE).shopId(shopDTO5.getId()).build()
        );
    }
    private void generateDatabase3Mock() {
        databaseManager.establishConnectionForClient(2, Boolean.TRUE);
        if (mockExists()) {
            return;
        }
        generateCommonMockData();

        // Five shops
        ShopDTO shopDTO1 = shopRepository.createShop(
                ShopDTO.builder().name("Swiss Ski Store").location("Bern, Switzerland").build()
        );
        ShopDTO shopDTO2 = shopRepository.createShop(
                ShopDTO.builder().name("Brussels Bakery").location("Brussels, Belgium").build()
        );
        ShopDTO shopDTO3 = shopRepository.createShop(
                ShopDTO.builder().name("Amsterdam Hamsterdom").location("Amsterdam, Netherlands").build()
        );
        ShopDTO shopDTO4 = shopRepository.createShop(
                ShopDTO.builder().name("Hokkaido Sushi").location("Hokkaido, Japan").build()
        );
        ShopDTO shopDTO5 = shopRepository.createShop(
                ShopDTO.builder().name("Brainrot Burgers").location("Pyongyang, North Korea").build()
        );

        // Five products
        productRepository.createProduct(
                ProductDTO.builder().name("Pepperoni pizza").price(6).quantity(32).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Acoustic Guitar").price(46).quantity(2).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Band merch - T-Shirt").price(23).quantity(0).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Data-Intensive Systems learning material bundle").price(200000).quantity(2).build()
        );
        productRepository.createProduct(
                ProductDTO.builder().name("Plushy toy").price(22).quantity(2).build()
        );

        // Five warehouses
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.TRUE)
                        .quantity(300).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.FALSE)
                        .quantity(320).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO1.getId()).isFull(Boolean.TRUE)
                        .quantity(340).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO3.getId()).isFull(Boolean.FALSE)
                        .quantity(310).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO3.getId()).isFull(Boolean.TRUE)
                        .quantity(333).build());
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO4.getId()).isFull(Boolean.FALSE)
                        .quantity(310).build());

        // Five employees
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(3600).title("Security").name("Porky Pig").isFired(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(4000).title("Security").name("Wario Warson").isFired(Boolean.FALSE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(4321).title("Webmaster").name("Jonathan Jones").isFired(Boolean.FALSE).shopId(shopDTO2.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2000).title("Cashier").name("Kenny from South Park").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2222).title("Manager").name("Gustavo Gambino").isFired(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(5555).title("Cashier").name("Henry Harris").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );

        // Five incidents
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Kenny fired!?").description("Oh my god! They fired Kenny!").isHandled(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Coffee spilled on company laptop").description("Keyboard smells and doesn't work. Device needs replacing?").isHandled(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Jonathan Jones manic laughter").description("Jonathan Jones can be heard laughing and it creeps out customers. Please ask him what's wrong!").isHandled(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Bigfoot arrested").description("Bigfoot has struck our store. He did not get away with it this time!").isHandled(Boolean.TRUE).shopId(shopDTO1.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("People sleeping in the building.").description("We need better security to stop people from sleeping in our store.").isHandled(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
    }

    /**
     * Half of the total generated mock data. All databases share this.
     * Contains a total of 25 data entries, 5 per table
     */
    private void generateCommonMockData() {
        // Five shops (two unique shop left for each database)
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

        // Five warehouses
        warehouseRepository.createWarehouse(
                WarehouseDTO.builder().shopId(shopDTO5.getId()).isFull(Boolean.TRUE)
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
                        .quantity(10).build());

        // Five employees
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
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(2560).title("Janitor").name("Janitor Jared").isFired(Boolean.FALSE).shopId(shopDTO3.getId()).build()
        );
        employeeRepository.createEmployee(
                EmployeeDTO.builder().salary(4763).title("Cashier").name("Johnny Doe").isFired(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );

        // Five incidents
        incidentRepository.createIncident(
                IncidentDTO.builder().title("STOLEN ITEM!!!").description("SOMEONE STOLE A BANANA!").isHandled(Boolean.FALSE).shopId(shopDTO1.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("The building has disappeared!").description("Tsunami hit Skinnarila last night! The store, along the university campus, has been washed away!").isHandled(Boolean.FALSE).shopId(shopDTO4.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Cash register malfunction").description("A cash register won't work and queues have stretched out. Please fix this! Edit: The cash register has been replaced.").isHandled(Boolean.TRUE).shopId(shopDTO2.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Aggressive customer").description("Customer lashed out against a cashier. The security escorted him out of the building.").isHandled(Boolean.TRUE).shopId(shopDTO3.getId()).build()
        );
        incidentRepository.createIncident(
                IncidentDTO.builder().title("Fuksivirhe").description("A customer made a fuksivirhe and needs help. Come ASAP!").isHandled(Boolean.FALSE).shopId(shopDTO5.getId()).build()
        );
    }
}
