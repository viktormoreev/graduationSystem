package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateThesisDto;
import com.example.graduationSystem.dtos.ThesisDto;
import com.example.graduationSystem.entity.Thesis;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ThesisRepository;
import com.example.graduationSystem.service.ThesisService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ThesisServiceImpl implements ThesisService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ThesisRepository thesisRepository;
    @Override
    public ThesisDto addThesis(CreateThesisDto thesis) {
        return null;
    }

    @Override
    public Thesis fetchThesisById(Long id) {
        Optional<Thesis> thesisOptional = thesisRepository.findById(id);
        if(!thesisOptional.isPresent()){
            throw new EntityNotFoundException("Thesis with Id " + id + " is not found");
        }
        return thesisOptional.get();
    }
}
