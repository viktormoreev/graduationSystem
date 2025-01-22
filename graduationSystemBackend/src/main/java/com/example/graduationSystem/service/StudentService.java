package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.StudentDto;
import com.example.graduationSystem.dtos.UserIDRequest;

public interface StudentService {
    StudentDto addStudent(UserIDRequest userIDRequest);

    boolean deleteStudentUID(String userId);

    boolean deleteStudentID(Long ID);
}
