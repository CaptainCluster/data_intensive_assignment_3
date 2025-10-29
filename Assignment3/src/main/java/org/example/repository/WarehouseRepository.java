package org.example.repository;

import jakarta.annotation.Resource;
import org.example.client.ClientConnection;
import org.example.dto.WarehouseDTO;
import org.example.utils.DatabaseUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.Tables.WAREHOUSE;

@Repository
public class WarehouseRepository {
    @Resource private ClientConnection clientConnection;
    @Resource private DatabaseUtils databaseUtils;

    public List<WarehouseDTO> fetchAllWarehouses() {
        if (!databaseUtils.isValidConnection()) {
            return List.of();
        }
        return clientConnection
                .getDslContext()
                .selectFrom(WAREHOUSE)
                .fetchInto(WarehouseDTO.class);
    }

    public WarehouseDTO fetchWarehouseById(int id) {
        if (!databaseUtils.isValidConnection()) {
            return null;
        }
        return clientConnection
                .getDslContext()
                .selectFrom(WAREHOUSE)
                .where(WAREHOUSE.ID.eq(id))
                .fetchOneInto(WarehouseDTO.class);
    }

    public void updateWarehouseQuantity(int id, int quantity) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(WAREHOUSE)
                .set(WAREHOUSE.QUANTITY, quantity)
                .where(WAREHOUSE.ID.eq(id))
                .execute();
    }

    public void updateWarehouseShopId(int warehouseId, int shopId) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(WAREHOUSE)
                .set(WAREHOUSE.SHOPID, shopId)
                .where(WAREHOUSE.ID.eq(warehouseId))
                .execute();
    }
}
