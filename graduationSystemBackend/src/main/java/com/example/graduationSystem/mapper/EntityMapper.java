package com.example.graduationSystem.mapper;

import com.example.graduationSystem.dtos.DepartmentDto;
import com.example.graduationSystem.dtos.ProfessorDto;
import com.example.graduationSystem.dtos.StudentDto;
import com.example.graduationSystem.entity.Department;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.entity.Student;
import com.example.graduationSystem.service.implementation.KeycloakAdminClientServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EntityMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private KeycloakAdminClientServiceImpl keycloakAdminClientServiceImpl;

    public StudentDto mapToStudentDto(Student student) {
        return StudentDto.builder()
                .userID(student.getUserID())
                .name(student.getName())
                .thesis(Optional.ofNullable(student.getThesis()) // Handle null case safely
                        .orElse(Collections.emptyList()) // Use empty list if null
                        .stream()
                        .map(thesis -> thesis.getId())
                        .collect(Collectors.toList()))
                .build();
    }

    public ProfessorDto mapToProfessorDto(Professor professor) {
        // Handling null safely with Optional and using the builder pattern
        return ProfessorDto.builder()
                .userID(professor.getUserID())
                .name(professor.getName())
                .title(professor.getTitle())
                .supervisedThesesIds(Optional.ofNullable(professor.getSupervisedTheses()) // Safely handle null supervisedTheses
                        .orElse(Collections.emptyList()) // Use empty list if null
                        .stream()
                        .map(thesis -> thesis.getId())
                        .collect(Collectors.toList()))
                .departmentId(professor.getDepartment() != null ? professor.getDepartment().getId() : null) // Safely handle null department
                .build();
    }

    public List<ProfessorDto> mapToProfessorDtoList(List<Professor> professors){
        return professors.stream().map(this::mapToProfessorDto).collect(Collectors.toList());
    }

    public DepartmentDto mapToDepartmentDto(Department department) {
        return DepartmentDto
                .builder()
                .professors(mapToProfessorDtoList(department.getProfessors()))
                .name(department.getName())
                .build();
    }
}
