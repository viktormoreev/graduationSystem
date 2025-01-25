package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateThesisDto;
import com.example.graduationSystem.dtos.ThesisDto;

public interface ThesisService {
    ThesisDto addThesis(CreateThesisDto thesis);
}
