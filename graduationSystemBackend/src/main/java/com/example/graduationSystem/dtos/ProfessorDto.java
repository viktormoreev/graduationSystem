package com.example.graduationSystem.dtos;

import com.example.graduationSystem.enums.Title;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProfessorDto {

    @NotNull(message = "User ID cannot be null")
    private String userID;

    @NotNull(message = "Name cannot be null")
    @Size(min = 1, message = "Name cannot be empty")
    private String name;

    @NotNull(message = "Title cannot be null")
    private Title title;

    private List<Long> supervisedThesesIds;

    @NotNull(message = "Department ID cannot be null")
    private Long departmentId;
}
