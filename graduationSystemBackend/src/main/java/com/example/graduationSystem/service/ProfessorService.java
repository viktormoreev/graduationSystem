package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.ProfessorDto;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.enums.Title;

import java.util.List;
import java.util.Set;

public interface ProfessorService {
    ProfessorDto addProfessor(UserIDRequest userIDRequest, String name, Title title, Long departmentId) ;

    void deleteProfessorByUID(String userId);

    void deleteProfessorByID(Long ID);

    Professor fetchProfessorById(Long id);

    Set<Professor> fetchProfessorsSet(Set<Long> professorIds);

    List<ProfessorDto> fetchProfessorsByDepartmentId(Long departmentId);
    Professor fetchProfessorByUserId(String userId);
}
