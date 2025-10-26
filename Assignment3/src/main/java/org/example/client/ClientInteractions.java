package org.example.client;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.database.DatabaseManager;
import org.example.dto.ShopDTO;
import org.example.repository.ShopRepository;
import org.example.service.EmployeeService;
import org.example.service.ProductService;
import org.example.service.ShopService;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Scanner;

@Slf4j
@Component
public class ClientInteractions {
    @Resource private DatabaseManager databaseManager;
    @Resource private ClientConnection clientConnection;
    @Resource private EmployeeService employeeService;
    @Resource private ShopService shopService;
    @Resource private ShopRepository shopRepository;
    @Resource private ProductService productService;

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
                case "STATUS" -> clientConnection.status();

                case "EMPLOYEE LIST" -> employeeService.listEmployees();
                case "EMPLOYEE HIRE" -> employeeService.hireEmployee();
                case "EMPLOYEE VACANT" -> employeeService.printVacantEmployees();
                case "EMPLOYEE ASSIGN" -> employeeService.assignEmployee();
                case "EMPLOYEE FIRE" -> employeeService.fireEmployee();

                case "PRODUCT LIST" -> productService.listProducts();
                case "PRODUCT RESTOCK" -> productService.restockProduct();
                case "PRODUCT PRICE" -> productService.changeProductPrice();

                case "SHOP LIST" -> shopService.listAllShops();
                case "SHOP RENAME" -> shopService.renameShop();
                case "SHOP RELOCATE" -> shopService.relocateShop();

                case "TEST" -> shopRepository.createShop(ShopDTO.builder().name("TEST_NAME").location("TEST_LOCATION").build());
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
                "STATUS", "Prints the status regarding current connection",
                "EXIT", "End program"
        );

        log.info("Here is a list of available commands:");
        commands.forEach((command, description) -> {
            log.info("{} - {}", command, description);
        });
    }
}
