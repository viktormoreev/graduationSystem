package com.example.graduationSystem.service;

import com.example.graduationSystem.dtos.CreateThesisDto;
import com.example.graduationSystem.dtos.ThesisDto;
import com.example.graduationSystem.entity.Thesis;

public interface ThesisService {
    ThesisDto addThesis(CreateThesisDto thesis);

    Thesis fetchThesisById(Long id);
}
