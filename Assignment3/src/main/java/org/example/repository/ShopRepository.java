package org.example.repository;

import jakarta.annotation.Resource;
import org.example.client.ClientConnection;
import org.example.dto.ShopDTO;
import org.springframework.stereotype.Repository;

import static assignment.Tables.SHOP;

@Repository
public class ShopRepository {
    @Resource private ClientConnection clientConnection;

    public void createShop(ShopDTO shopDTO) {
        if (shopDTO == null || clientConnection.getDslContext() == null) {
            return;
        }
        clientConnection
                .getDslContext()
                .insertInto(SHOP)
                .columns(SHOP.NAME, SHOP.LOCATION)
                .values(shopDTO.getName(), shopDTO.getLocation())
                .execute();
    }
}
