package com.example.graduationSystem.dtos;

import com.example.graduationSystem.entity.Professor;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DepartmentDto {
    private String name;
    private List<ProfessorDto> professors;
}
