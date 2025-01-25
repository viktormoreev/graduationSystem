package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.ProfessorDto;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.enums.Title;

public interface ProfessorService {
    ProfessorDto addProfessor(UserIDRequest userIDRequest, String name, Title title, Long departmentId) ;

    void deleteProfessorUID(String userId);

    void deleteProfessorID(Long ID);
}
