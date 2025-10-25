package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.EmployeeDTO;
import org.example.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
public class EmployeeService {
    @Resource private EmployeeRepository employeeRepository;
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
}
