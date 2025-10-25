package org.example.repository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.dto.ShopDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.Tables.SHOP;

@Slf4j
@Repository
public class ShopRepository {
    @Resource private ClientConnection clientConnection;

    public void createShop(ShopDTO shopDTO) {
        if (shopDTO == null || clientConnection == null || clientConnection.getDslContext() == null) {
            return;
        }
        try {
            clientConnection
                    .getDslContext()
                    .insertInto(SHOP)
                    .columns(SHOP.NAME, SHOP.LOCATION)
                    .values(shopDTO.getName(), shopDTO.getLocation())
                    .execute();
        } catch (Exception e) {
            log.warn("Failed to create shop.");
        }
    }

    public List<ShopDTO> fetchAllShops() {
        if (clientConnection == null || clientConnection.getDslContext() == null) {
            return List.of();
        }
        try {
            return clientConnection
                    .getDslContext()
                    .selectFrom(SHOP)
                    .fetchInto(ShopDTO.class);
        } catch (Exception e) {
            log.warn("Failed to fetch shops.");
            return List.of();
        }
    }
}
