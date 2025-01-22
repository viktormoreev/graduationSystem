package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.StudentDto;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.entity.Student;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.StudentRepository;
import com.example.graduationSystem.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class StudentServiceImpl implements StudentService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private KeycloakAdminClientService keycloakAdminClientService;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public StudentDto addStudent(UserIDRequest userIDRequest) {
        Student student = Student.builder()
                .userID(userIDRequest.getUserID())
                .build();
        studentRepository.save(student);
        return entityMapper.mapToStudentDto(student);
    }

    @Override
    public boolean deleteStudentUID(String userId) {
        Optional<Student> student = studentRepository.findByUserID(userId);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteStudentID(Long ID) {
        if (studentRepository.existsById(ID)) {
            studentRepository.deleteById(ID);
            return true;
        }
        return false;
    }
}
