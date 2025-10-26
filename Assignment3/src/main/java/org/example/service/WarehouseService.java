package org.example.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class WarehouseService {
    @Resource private WarehouseRepository warehouseRepository;
}
