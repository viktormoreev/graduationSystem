package com.example.graduationSystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CreateDepartmentDto {
    @Size(min = 1, message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;
}
