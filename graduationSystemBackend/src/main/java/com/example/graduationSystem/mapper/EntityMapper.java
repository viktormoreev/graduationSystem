package com.example.graduationSystem.mapper;

import com.example.graduationSystem.dtos.StudentDto;
import com.example.graduationSystem.entity.Student;
import com.example.graduationSystem.service.implementation.KeycloakAdminClientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class EntityMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private KeycloakAdminClientService keycloakAdminClientService;

    public StudentDto mapToStudentDto(Student student) {
        StudentDto studentDto = new StudentDto();
        studentDto.setUserID(student.getUserID());
        if (!student.getThesis().isEmpty()) {
            studentDto.setThesis(student.getThesis()
                    .stream()
                    .map(thesis -> thesis.getId())
                    .collect(Collectors.toList()));
        }
        return studentDto;
    }

}
