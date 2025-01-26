package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.CreateThesisApplicationDto;
import com.example.graduationSystem.dtos.ThesisApplicationDto;
import com.example.graduationSystem.entity.Department;
import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.entity.Student;
import com.example.graduationSystem.entity.ThesisApplication;
import com.example.graduationSystem.mapper.EntityMapper;
import com.example.graduationSystem.repository.ThesisApplicationRepository;
import com.example.graduationSystem.service.DepartmentService;
import com.example.graduationSystem.service.ProfessorService;
import com.example.graduationSystem.service.StudentService;
import com.example.graduationSystem.service.ThesisApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ThesisApplicationServiceImpl implements ThesisApplicationService {

    @Autowired
    private EntityMapper entityMapper;
    @Autowired
    private ThesisApplicationRepository thesisApplicationRepository;
    @Autowired
    private StudentService studentService;
    @Autowired
    private ProfessorService professorService;
    @Autowired
    private DepartmentService departmentService;

    @Override
    public ThesisApplicationDto addThesisApplication(CreateThesisApplicationDto thesisApplication) {
        Student student = studentService.fetchStudentById(thesisApplication.getStudentId());
        Professor professor = professorService.fetchProfessorById(thesisApplication.getProfessorId());
        Department department = departmentService.fetchDepartmentById(thesisApplication.getDepartmentId());

        return entityMapper.mapToThesisApplicationDto(thesisApplicationRepository.save(
                ThesisApplication.builder()
                        .title(thesisApplication.getTitle())
                        .tasks(thesisApplication.getTasks())
                        .objectives(thesisApplication.getObjectives())
                        .technologies(thesisApplication.getTechnologies())
                        .student(student)
                        .professor(professor)
                        .department(department)
                        .build())
        );
    }
}
