package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.ProfessorDto;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.entity.Department;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.enums.Title;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ProfessorRepository;
import com.example.graduationSystem.service.DepartmentService;
import com.example.graduationSystem.service.ProfessorService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorServiceImpl implements ProfessorService {

    @Autowired
    private EntityMapper entityMapper;

    @Autowired
    private KeycloakAdminClientServiceImpl keycloakAdminClientServiceImpl;

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private DepartmentService departmentService;

    @Override
    public ProfessorDto addProfessor(UserIDRequest userIDRequest, String name, Title title, Long departmentId) {
        Department department = departmentService.fetchDepartmentById(departmentId);
        final Professor professor = Professor.builder()
                .userID(userIDRequest.getUserID())
                .name(name)
                .title(title)
                .department(department)
                .build();
        professorRepository.save(professor);
        return entityMapper.mapToProfessorDto(professor);
    }

    @Override
    public void deleteProfessorByUID(String userId) {
        Professor professor = fetchProfessorByUserId(userId);
        professorRepository.delete(professor);
    }

    @Override
    public void deleteProfessorByID(Long id) {
        Professor professor = fetchProfessorById(id);
        professorRepository.delete(professor);
    }

    @Override
    public Professor fetchProfessorById(Long id) {
        Optional<Professor> professorOptional = professorRepository.findById(id);
        if(!professorOptional.isPresent()){
            throw new EntityNotFoundException("Professor with Id " + id + " is not found");
        }
        return professorOptional.get();
    }

    @Override
    public Professor fetchProfessorByUserId(String userId) {
        Optional<Professor> professorOptional = professorRepository.findByUserID(userId);
        if(!professorOptional.isPresent()){
            throw new EntityNotFoundException("Professor with userId " + userId + " is not found");
        }
        return professorOptional.get();
    }

    @Override
    public Set<Professor> fetchProfessorsSet(Set<Long> professorIds) {
        return professorIds.stream().map(professorId -> fetchProfessorById(professorId)).collect(Collectors.toSet());
    }

    @Override
    public List<ProfessorDto> fetchProfessorsByDepartmentId(Long departmentId) {
        Department department = departmentService.fetchDepartmentById(departmentId);
        return entityMapper.mapToProfessorDtoList(professorRepository.findListByDepartment(department));
    }
}
