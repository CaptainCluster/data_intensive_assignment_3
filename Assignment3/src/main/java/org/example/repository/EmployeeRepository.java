package org.example.repository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.dto.EmployeeDTO;
import org.springframework.stereotype.Repository;

import static assignment.Tables.EMPLOYEE;

@Slf4j
@Repository
public class EmployeeRepository {
    @Resource private ClientConnection clientConnection;

    public void createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null || clientConnection == null || clientConnection.getDslContext() == null) {
            return;
        }
        try {
            clientConnection
                    .getDslContext()
                    .insertInto(EMPLOYEE)
                    .columns(EMPLOYEE.NAME, EMPLOYEE.TITLE, EMPLOYEE.SALARY)
                    .values(employeeDTO.getName(), employeeDTO.getTitle(), employeeDTO.getSalary())
                    .execute();
        } catch(Exception e) {
            log.warn("Failed to create employee.");
        }
    }
}
