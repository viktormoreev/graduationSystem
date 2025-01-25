package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateThesisDto;
import com.example.graduationSystem.dtos.ThesisDto;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ThesisServiceRepository;
import com.example.graduationSystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ThesisServiceRepository thesisServiceRepository;
    @Override
    public ThesisDto addThesis(CreateThesisDto thesis) {
        return null;
    }
}
