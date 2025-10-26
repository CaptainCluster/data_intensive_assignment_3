package org.example.repository;

import jakarta.annotation.Resource;
import org.example.client.ClientConnection;
import org.example.utils.DatabaseUtils;
import org.springframework.stereotype.Repository;

@Repository
public class WarehouseRepository {
    @Resource private ClientConnection clientConnection;
    @Resource private DatabaseUtils databaseUtils;


}
