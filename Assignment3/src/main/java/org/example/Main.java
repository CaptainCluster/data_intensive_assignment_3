package org.example;

import lombok.extern.slf4j.Slf4j;
import org.example.utils.FlywayMigrate;
import org.example.utils.SystemVariablePlacer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@Slf4j
@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        SystemVariablePlacer.placeSystemVariables();
        FlywayMigrate.migrate();
        SpringApplication.run(Main.class, args);
    }
}