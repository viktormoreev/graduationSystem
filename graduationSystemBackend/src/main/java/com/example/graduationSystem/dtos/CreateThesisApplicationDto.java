package com.example.graduationSystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateThesisApplicationDto {
    @NotNull(message = "Title cannot be null")
    @Size(min = 1, message = "Title cannot be empty")
    private String title;
    @NotNull(message = "Objectives cannot be null")
    @Size(min = 1, message = "Objectives cannot be empty")
    private String objectives;
    @NotNull(message = "Tasks cannot be null")
    @Size(min = 1, message = "Tasks cannot be empty")
    private String tasks;
    @NotNull(message = "Technologies cannot be null")
    @Size(min = 1, message = "Technologies cannot be empty")
    private String technologies;
    @NotNull(message = "Student Id cannot be null")
    private Long studentId;
    @NotNull(message = "Professor Id cannot be null")
    private Long professorId;
    @NotNull(message = "Department Id cannot be null")
    private Long departmentId;
}
