package org.example.utils;


import jakarta.annotation.Resource;
import org.example.client.ClientConnection;
import org.springframework.stereotype.Component;

@Component
public class DatabaseUtils {
    @Resource private ClientConnection clientConnection;

    public boolean isValidConnection() {
        return clientConnection != null && clientConnection.getDslContext() != null;
    }
}
