package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.ProfessorDto;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.entity.Department;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.enums.Title;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.DepartmentRepository;
import com.example.graduationSystem.repository.ProfessorRepository;
import com.example.graduationSystem.service.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private KeycloakAdminClientService keycloakAdminClientService;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public ProfessorDto addProfessor(UserIDRequest userIDRequest, String name, Title title, Long departmentId) {
        Optional<Department> department = departmentRepository.findById(departmentId);
        if (!department.isPresent()) {
            throw new EntityNotFoundException("Department with ID" + departmentId + " not found");
        }
        final Professor professor = Professor.builder()
                .userID(userIDRequest.getUserID())
                .name(name)
                .title(title)
                .build();
        professorRepository.save(professor);
        return entityMapper.mapToProfessorDto(professor);
    }

    @Override
    public void deleteProfessorUID(String userId) {
        final Optional<Professor> professor = professorRepository.findByUserID(userId);
        if (professor.isPresent()) {
            professorRepository.delete(professor.get());
        }
        throw new EntityNotFoundException("Professor with ID " + userId + " not found");
    }

    @Override
    public void deleteProfessorID(Long ID) {
        if (professorRepository.existsById(ID)) {
            professorRepository.deleteById(ID);
        }
        throw new EntityNotFoundException("Professor with ID " + ID + " not found");
    }
}
