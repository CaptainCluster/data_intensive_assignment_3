package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.EmployeeDTO;
import org.example.dto.ShopDTO;
import org.example.repository.EmployeeRepository;
import org.example.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
public class EmployeeService {
    @Resource private EmployeeRepository employeeRepository;
    @Resource private ShopRepository shopRepository;
    private final Scanner scanner = new Scanner(System.in);

    public void hireEmployee() {

        log.info("What is the name of the employee: ");
        String name = scanner.nextLine();

        log.info("What will be their title: ");
        String title = scanner.nextLine();

        log.info("What will be their salary: ");

        int salary;
        try {
            String salaryString = scanner.nextLine();
            salary = Integer.parseInt(salaryString);
        } catch (Exception e) {
            log.warn("Error when parsing the salary. Cancelling the hiring process.");
            return;
        }

        employeeRepository.createEmployee(
                EmployeeDTO
                        .builder()
                        .name(name)
                        .title(title)
                        .salary(salary)
                        .build()
        );
    }

    public void fireEmployee() {
        log.info("What is the ID of the employee to be fired: ");
        String idString = scanner.nextLine();
        int employeeId;
        try {
            employeeId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Failed to parse employee ID. Cancelling the firing process.");
            return;
        }
        EmployeeDTO employeeDTO = employeeRepository.getEmployeeById(employeeId);
        if (employeeDTO == null) {
            log.warn("Failed to fetch employee.");
            return;
        }
        employeeRepository.updateIsFiredByEmployeeId(employeeId);
        log.info("{} has been FIRED!!!", employeeDTO.getName());
    }

    public void listEmployees() {
        log.info("Here are all the employees: ");
        employeeRepository.selectAllEmployees()
                .forEach((employeeDTO) -> {
                    ShopDTO shopDTO = shopRepository.fetchShopById(employeeDTO.getShopId());
                    String shopName = shopDTO != null ? shopDTO.getName() : "None (vacant)";
                    log.info(
                            "id - {} | name - {} | title - {} | salary - {} | shop - {}",
                            employeeDTO.getId(),
                            employeeDTO.getName(),
                            employeeDTO.getTitle(),
                            employeeDTO.getSalary(),
                            shopName
                    );
                });
    }

    public void assignEmployee() {
        log.info("The ID of the employee you would like to assign: ");
        String idString = scanner.nextLine();
        int employeeId;
        try {
            employeeId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Failed to parse the input. Cancelling the process.");
            return;
        }
        EmployeeDTO employeeDTO = employeeRepository.getEmployeeById(employeeId);

        log.info("Employee {} fetched. To shop of which ID shall they be assigned to: ", employeeDTO.getName());
        idString = scanner.nextLine();

        int shopId;
        try {
            shopId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Failed to parse the input. Cancelling the process.");
            return;
        }
        employeeRepository.setShopIdForEmployee(employeeId, shopId);

        employeeDTO = employeeRepository.getEmployeeById(employeeId);
        ShopDTO shopDTO = shopRepository.fetchShopById(employeeDTO.getShopId());

        if (shopDTO == null) {
            log.warn("Failed to assign shop to employee.");
            return;
        }
        log.info("Successfully assigned employee to shop {}.", shopDTO.getName());
    }

    /**
     * With vacant, I mean employees not assigned to a store
     */
    public void printVacantEmployees() {
        log.info("Here are all the vacant employees: ");
        employeeRepository.selectVacantEmployees()
                .forEach(employeeDTO -> log.info(
                        "id - {} | name - {} | title - {} | salary - {}",
                        employeeDTO.getId(),
                        employeeDTO.getName(),
                        employeeDTO.getTitle(),
                        employeeDTO.getSalary()
                ));
    }
}
