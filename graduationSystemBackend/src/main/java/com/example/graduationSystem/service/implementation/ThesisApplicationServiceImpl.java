package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateThesisApplicationDto;
import com.example.graduationSystem.dtos.ThesisApplicationDto;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ThesisApplicationRepository;
import com.example.graduationSystem.service.ThesisApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ThesisApplicationRepository thesisApplicationRepository;
    @Override
    public ThesisApplicationDto addThesisApplication(CreateThesisApplicationDto thesisApplication) {
        return null;
    }
}
