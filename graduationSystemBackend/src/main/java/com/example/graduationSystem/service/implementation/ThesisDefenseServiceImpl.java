package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateThesisDefenseDto;
import com.example.graduationSystem.dtos.ThesisDefenseDto;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ThesisDefenseRepository;
import com.example.graduationSystem.service.ThesisDefenseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ThesisDefenseRepository thesisDefenseRepository;
    @Override
    public ThesisDefenseDto addThesisDefense(CreateThesisDefenseDto thesisDefense) {
        return null;
    }
}
