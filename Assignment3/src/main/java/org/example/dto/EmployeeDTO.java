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
public class EmployeeDTO {
    private int id;
    @NotNull private String name;
    private String title;
    @NotNull private Integer salary;
}
