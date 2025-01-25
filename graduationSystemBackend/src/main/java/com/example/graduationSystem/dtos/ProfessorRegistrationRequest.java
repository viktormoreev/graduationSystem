package com.example.graduationSystem.dtos;

import com.example.graduationSystem.enums.Title;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ProfessorRegistrationRequest {
    @NotNull(message = "Username cannot be null")
    @Size(min = 1, message = "Username cannot be empty")
    private String username;
    @Size(min = 1, message = "Password cannot be empty")
    @NotNull(message = "Password cannot be null")
    private String password;
    @Size(min = 1, message = "Name cannot be empty")
    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Title cannot be null")
    private Title title;
    @NotNull(message = "Department ID cannot be null")
    private Long departmentId;
}
