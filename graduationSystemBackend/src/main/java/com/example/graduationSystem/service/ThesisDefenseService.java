package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateThesisDefenseDto;
import com.example.graduationSystem.dtos.ThesisDefenseDto;

public interface ThesisDefenseService {
    ThesisDefenseDto addThesisDefense(CreateThesisDefenseDto thesisDefense);
}
