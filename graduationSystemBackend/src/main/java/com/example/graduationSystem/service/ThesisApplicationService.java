package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateThesisApplicationDto;
import com.example.graduationSystem.dtos.ThesisApplicationDto;

public interface ThesisApplicationService {
    ThesisApplicationDto addThesisApplication(CreateThesisApplicationDto thesisApplication);
}
