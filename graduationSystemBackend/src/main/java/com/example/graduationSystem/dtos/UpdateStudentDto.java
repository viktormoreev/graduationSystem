package com.example.graduationSystem.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UpdateStudentDto {
    @NotNull
    private String username;
    @NotNull
    private String password;
    @NotNull
    private String name;
}
