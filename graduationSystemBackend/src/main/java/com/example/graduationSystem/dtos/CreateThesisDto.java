package com.example.graduationSystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateThesisDto {
    @NotNull(message = "Thesis cannot be null")
    @Size(min = 1, message = "Thesis cannot be empty")
    private String thesisText;
}
