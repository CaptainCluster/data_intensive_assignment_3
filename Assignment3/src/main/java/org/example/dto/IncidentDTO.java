package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.jetbrains.annotations.NotNull;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class IncidentDTO {
    private int id;
    @NotNull private String title;
    private String description;
    @NotNull private Integer shopId;
}
