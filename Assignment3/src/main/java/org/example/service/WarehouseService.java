package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.dto.ShopDTO;
import org.example.dto.WarehouseDTO;
import org.example.repository.ShopRepository;
import org.example.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
public class WarehouseService {
    @Resource private WarehouseRepository warehouseRepository;
    @Resource private ShopRepository shopRepository;

    private final Scanner scanner = new Scanner(System.in);
    private static final int WAREHOUSE_EXPAND_INCREMENTATION = 20;

    public void listWarehouses() {
        log.info("Here is a list of all the warehouses: ");
        warehouseRepository.fetchAllWarehouses()
                .forEach(warehouseDTO -> {
                    log.info(
                            "ID - {} | Quantity - {} | Full - {}",
                            warehouseDTO.getId(),
                            warehouseDTO.getQuantity(),
                            warehouseDTO.isFull() ? "FULL" : "NOT NULL"
                    );
        });
    }

    public void assignWarehouseToShop() {
        Integer warehouseId = askWarehouseId();
        Integer shopId = askShopId();
        if (warehouseId == null || shopId == null) {
            return;
        }
        warehouseRepository.updateWarehouseShopId(warehouseId, shopId);

        ShopDTO shopDTO = shopRepository.fetchShopById(shopId);
        log.info("The warehouse with ID {} is now a part of the following shop: {}",
                warehouseId,
                shopDTO.getName());
    }

    public void expandWarehouse() {
        Integer warehouseId = askWarehouseId();
        if (warehouseId == null) {
            return;
        }
        WarehouseDTO warehouseDTO = warehouseRepository.fetchWarehouseById(warehouseId);
        warehouseRepository.updateWarehouseQuantity(warehouseId, warehouseDTO.getQuantity() + WAREHOUSE_EXPAND_INCREMENTATION);
        log.info("Warehouse has been expanded to fit {} more items.", WAREHOUSE_EXPAND_INCREMENTATION);
    }

    private Integer askWarehouseId() {
        log.info("What is the ID of the warehouse: ");
        String idString = scanner.nextLine();
        try {
            return Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Failed to parse the input: {}", idString);
            return null;
        }
    }

    private Integer askShopId() {
        log.info("What is the ID of the shop: ");
        String idString = scanner.nextLine();
        try {
            return Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Failed to parse the input: {}", idString);
            return null;
        }
    }
}
