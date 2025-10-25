package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.repository.ShopRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class ShopService {
    @Resource private ShopRepository shopRepository;

    public void listAllShops() {
        log.info("Here are the shops:");
        shopRepository.fetchAllShops().forEach(
                shopDTO -> log.info("name - {} | location {}", shopDTO.getName(), shopDTO.getLocation())
        );
    }
}
