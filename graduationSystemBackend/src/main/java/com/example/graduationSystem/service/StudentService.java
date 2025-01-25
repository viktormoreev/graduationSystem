package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.StudentDto;
import com.example.graduationSystem.dtos.UpdateStudentDto;
import com.example.graduationSystem.dtos.UserIDRequest;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();

    StudentDto getStudentById(Long id);

    StudentDto getStudentByUId(String uid);

    StudentDto addStudent(UserIDRequest userIDRequest, String name);

    void deleteStudentByUID(String userId);

    void deleteStudentByID(Long ID);

    StudentDto updateStudent(UpdateStudentDto updateStudentDto);

}
