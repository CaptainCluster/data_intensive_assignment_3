package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.repository.ShopRepository;
import org.springframework.stereotype.Service;

import java.util.Scanner;

@Slf4j
@Service
public class ShopService {
    @Resource private ShopRepository shopRepository;
    private final Scanner scanner = new Scanner(System.in);

    public void listAllShops() {
        log.info("Here are the shops:");
        shopRepository.fetchAllShops().forEach(
                shopDTO -> log.info("ID - {} | name - {} | location - {}",
                        shopDTO.getId(), shopDTO.getName(), shopDTO.getLocation())
        );
    }

    public void renameShop() {
        log.info("Give the ID of the shop to be renamed: ");

        int shopId;
        try {
            String idString = scanner.nextLine();
             shopId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Error when parsing the id. Cancelling the renaming process.");
            return;
        }
        log.info("What will be the new name of the shop: ");
        String shopName = scanner.nextLine();
        shopRepository.updateName(shopId, shopName);
        log.info("The shop has been renamed to {}.", shopName);
    }

    public void relocateShop() {
        log.info("Give the ID of the shop to relocate: ");

        int shopId;
        try {
            String idString = scanner.nextLine();
            shopId = Integer.parseInt(idString);
        } catch (Exception e) {
            log.warn("Error when parsing the id. Cancelling the relocation process.");
            return;
        }
        log.info("What will be the new location of the shop: ");
        String shopLocation = scanner.nextLine();
        shopRepository.updateLocation(shopId, shopLocation);
        log.info("The shop has been relocated to {}", shopLocation);
    }
}
