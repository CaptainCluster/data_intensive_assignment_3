package org.example.repository;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.client.ClientConnection;
import org.example.dto.ShopDTO;
import org.example.utils.DatabaseUtils;
import org.springframework.stereotype.Repository;

import java.util.List;

import static assignment.Tables.SHOP;

@Slf4j
@Repository
public class ShopRepository {
    @Resource private ClientConnection clientConnection;
    @Resource private DatabaseUtils databaseUtils;

    public void createShop(ShopDTO shopDTO) {
        if (shopDTO == null || !databaseUtils.isValidConnection()) {
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
        if (!databaseUtils.isValidConnection()) {
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

    public ShopDTO fetchShopById(int shopId) {
        if (!databaseUtils.isValidConnection()) {
            return null;
        }
        try {
            return clientConnection
                    .getDslContext()
                    .selectFrom(SHOP)
                    .where(SHOP.ID.eq(shopId))
                    .fetchOneInto(ShopDTO.class);
        } catch (Exception e) {
            log.warn("Failed to fetch shops.");
            return null;
        }
    }

    public void updateName(int shopId, String shopName) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(SHOP)
                .set(SHOP.NAME, shopName)
                .where(SHOP.ID.eq(shopId))
                .execute();
    }

    public void updateLocation(int shopId, String location) {
        if (!databaseUtils.isValidConnection()) {
            return;
        }
        clientConnection
                .getDslContext()
                .update(SHOP)
                .set(SHOP.LOCATION, location)
                .where(SHOP.ID.eq(shopId))
                .execute();
    }
}
