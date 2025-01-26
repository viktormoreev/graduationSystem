package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateThesisDefenseDto;
import com.example.graduationSystem.dtos.ThesisDefenseDto;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.entity.Thesis;
import com.example.graduationSystem.entity.ThesisDefense;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ThesisDefenseRepository;
import com.example.graduationSystem.service.ProfessorService;
import com.example.graduationSystem.service.ThesisDefenseService;
import com.example.graduationSystem.service.ThesisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;

@Service
public class ThesisDefenseServiceImpl implements ThesisDefenseService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ThesisDefenseRepository thesisDefenseRepository;
    @Autowired
    private ThesisService thesisService;
    @Autowired
    private ProfessorService professorService;
    @Override
    public ThesisDefenseDto addThesisDefense(CreateThesisDefenseDto thesisDefense) {
        Thesis thesis = thesisService.fetchThesisById(thesisDefense.getThesisId());
        Set<Professor> professors = professorService.fetchProfessorsSet(thesisDefense.getProfessorIds());
        return entityMapper.mapToThesisDefenseDto(ThesisDefense
                .builder()
                .thesis(thesis)
                .grade(thesisDefense.getGrade())
                .committeeMembers(professors)
                .defenseDate(LocalDateTime.now())
                .build());
    }
}
