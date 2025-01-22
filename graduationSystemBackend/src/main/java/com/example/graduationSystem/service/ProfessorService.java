package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.ProfessorDto;
import com.example.graduationSystem.dtos.UserIDRequest;

public interface ProfessorService {
    ProfessorDto addProfessor(UserIDRequest userIDRequest);

    boolean deleteProfessorUID(String userId);
}
