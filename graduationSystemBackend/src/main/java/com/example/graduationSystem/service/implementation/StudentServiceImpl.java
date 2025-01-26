package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.StudentDto;
import com.example.graduationSystem.dtos.UpdateStudentDto;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.entity.Student;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.StudentRepository;
import com.example.graduationSystem.service.StudentService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private KeycloakAdminClientServiceImpl keycloakAdminClientServiceImpl;

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        return null;
    }

    @Override
    public StudentDto getStudentById(Long id) {
        return null;
    }

    @Override
    public StudentDto getStudentByUId(String uid) {
        return null;
    }

    @Override
    public StudentDto addStudent(UserIDRequest userIDRequest, String name) {
        final Student student = Student.builder()
                .userID(userIDRequest.getUserID())
                .name(name)
                .build();
        studentRepository.save(student);
        return entityMapper.mapToStudentDto(student);
    }

    @Override
    public void deleteStudentByUID(String userId) {
        final Optional<Student> student = studentRepository.findByUserID(userId);
        if (student.isPresent()) {
            studentRepository.delete(student.get());
        }
        throw new EntityNotFoundException("Student with ID " + userId + " not found");
    }

    @Override
    public void deleteStudentByID(Long ID) {
        if (studentRepository.existsById(ID)) {
            studentRepository.deleteById(ID);
        }
        throw new EntityNotFoundException("Student with ID " + ID + " not found");
    }

    @Override
    public StudentDto updateStudent(UpdateStudentDto updateStudentDto) {
        return null;
    }

    @Override
    public Student fetchStudentById(Long id) {
        Optional<Student> studentOptional = studentRepository.findById(id);
        if(!studentOptional.isPresent()){
            throw new EntityNotFoundException("Student with id " + id + " not found");
        }
        return studentOptional.get();
    }

}
