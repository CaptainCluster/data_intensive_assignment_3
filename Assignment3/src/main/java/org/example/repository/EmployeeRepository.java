package org.example.repository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.dto.EmployeeDTO;
import org.example.utils.DatabaseUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.Tables.EMPLOYEE;

@Slf4j
@Repository
public class EmployeeRepository {
    @Resource private ClientConnection clientConnection;
    @Resource private DatabaseUtils databaseUtils;

    public EmployeeDTO createEmployee(EmployeeDTO employeeDTO) {
        if (employeeDTO == null || !databaseUtils.isValidConnection()) {
            return null;
        }
        return clientConnection
                .getDslContext()
                .insertInto(EMPLOYEE)
                .columns(EMPLOYEE.NAME, EMPLOYEE.TITLE, EMPLOYEE.SALARY, EMPLOYEE.SHOPID)
                .values(employeeDTO.getName(), employeeDTO.getTitle(), employeeDTO.getSalary(), employeeDTO.getShopId())
                .returning(EMPLOYEE.ID, EMPLOYEE.NAME, EMPLOYEE.SALARY, EMPLOYEE.SHOPID)
                .fetchOneInto(EmployeeDTO.class);
    }

    public EmployeeDTO getEmployeeById(int employeeId) {
        if (!databaseUtils.isValidConnection()) {
            return null;
        }
        return clientConnection
                .getDslContext()
                .selectFrom(EMPLOYEE)
                .where(EMPLOYEE.ID.eq(employeeId))
                .fetchSingleInto(EmployeeDTO.class);
    }

    public List<EmployeeDTO> selectAllEmployees() {
        if (!databaseUtils.isValidConnection()) {
            return List.of();
        }
        return clientConnection
                .getDslContext()
                .selectFrom(EMPLOYEE)
                .fetchInto(EmployeeDTO.class);
    }

    public List<EmployeeDTO> selectVacantEmployees() {
        if (!databaseUtils.isValidConnection()) {
            return List.of();
        }
        return clientConnection
                .getDslContext()
                .selectFrom(EMPLOYEE)
                .where(EMPLOYEE.SHOPID.isNull())
                .fetchInto(EmployeeDTO.class);
    }

    public void setShopIdForEmployee(int employeeId, int shopId) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(EMPLOYEE)
                .set(EMPLOYEE.SHOPID, shopId)
                .where(EMPLOYEE.ID.eq(employeeId))
                .execute();
    }

    public void updateIsFiredByEmployeeId(int employeeId) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(EMPLOYEE)
                .set(EMPLOYEE.ISFIRED, Boolean.TRUE)
                .where(EMPLOYEE.ID.eq(employeeId))
                .execute();
    }
 }
