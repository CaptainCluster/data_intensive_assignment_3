package org.example.utils;

import io.github.cdimascio.dotenv.Dotenv;
import lombok.extern.slf4j.Slf4j;
import java.util.Objects;

@Slf4j
public class SystemVariablePlacer {
    private SystemVariablePlacer() {}

    public static void placeSystemVariables() {
        Dotenv dotenv = Dotenv.load();
        try {
            System.setProperty("DB_URL", Objects.requireNonNull(dotenv.get("DB_URL")));
            System.setProperty("DB_USERNAME", Objects.requireNonNull(dotenv.get("DB_USERNAME")));
            System.setProperty("DB_PASSWORD", Objects.requireNonNull(dotenv.get("DB_PASSWORD")));
        } catch (NullPointerException e) {
            log.error("Could not receive env variables. Make sure they exist!");
            System.exit(2);
        }
    }
}