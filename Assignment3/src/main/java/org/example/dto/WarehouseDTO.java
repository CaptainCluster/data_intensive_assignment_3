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
public class WarehouseDTO {
    private int id;
    private Integer shopId;
    @NotNull private Integer quantity;
    private boolean isFull;
}
