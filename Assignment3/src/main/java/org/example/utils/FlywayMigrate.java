package org.example.utils;

import lombok.extern.slf4j.Slf4j;
import org.flywaydb.core.Flyway;

/**
 * Here are some sources I used to help me set up Flyway for multiple databases
 * https://codingtechroom.com/question/flyway-multiple-databases-configuration
 * https://stackoverflow.com/questions/37281229/multiple-datasources-migrations-using-flyway-in-a-spring-boot-application
 */
@Slf4j
public class FlywayMigrate {
    private FlywayMigrate() {}

    public static void migrate() {
        migrateIndividualFlyway(
                System.getProperty("DB_URL_1")
        );
        migrateIndividualFlyway(
                System.getProperty("DB_URL_2")
        );
        migrateIndividualFlyway(
                System.getProperty("DB_URL_3")
        );
    }

    private static void migrateIndividualFlyway(
            String url
    ) {
        try {
            Flyway flyway = Flyway.configure()
                    .dataSource(url, "postgres", "")
                    .cleanDisabled(Boolean.TRUE)
                    .load();
            flyway.migrate();
        } catch (Exception e) {
            log.error("Could not handle Flyway migrations for database with the following URL: {}", url, e);
            log.info("Exiting...");
            System.exit(1);
        }
    }
}