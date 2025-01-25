package com.example.graduationSystem.dtos;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class CreateThesisDefenseDto {
    @NotNull(message = "Thesis ID cannot be null")
    private Long thesisId;
    @NotNull(message = "Grade cannot be null")
    @DecimalMin(value = "2.0", message = "Grade must be at least 2.0")
    @DecimalMax(value = "6.0", message = "Grade must not exceed 6.0")
    private Double grade;
    @NotNull(message = "Professors List cannot be null")
    private Set<Long> professorIds;
}
