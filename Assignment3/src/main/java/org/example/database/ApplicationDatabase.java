package org.example.database;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;


@Builder
@Getter
@AllArgsConstructor
public class ApplicationDatabase {
    private String name;
}
