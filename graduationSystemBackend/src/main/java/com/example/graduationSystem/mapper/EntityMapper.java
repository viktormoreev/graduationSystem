package com.example.graduationSystem.mapper;

import com.example.graduationSystem.dtos.*;
import com.example.graduationSystem.entity.*;
import com.example.graduationSystem.service.implementation.KeycloakAdminClientServiceImpl;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class EntityMapper {
    private final ModelMapper modelMapper = new ModelMapper();

    @Autowired
    private KeycloakAdminClientServiceImpl keycloakAdminClientServiceImpl;

    public StudentDto mapToStudentDto(Student student) {
        return StudentDto.builder()
                .userID(student.getUserID())
                .name(student.getName())
                .thesis(Optional.ofNullable(student.getThesis()) // Handle null case safely
                        .orElse(Collections.emptyList()) // Use empty list if null
                        .stream()
                        .map(thesis -> thesis.getId())
                        .collect(Collectors.toList()))
                .build();
    }

    public ProfessorDto mapToProfessorDto(Professor professor) {
        // Handling null safely with Optional and using the builder pattern
        return ProfessorDto.builder()
                .userID(professor.getUserID())
                .name(professor.getName())
                .title(professor.getTitle())
                .supervisedThesesIds(Optional.ofNullable(professor.getSupervisedTheses()) // Safely handle null supervisedTheses
                        .orElse(Collections.emptyList()) // Use empty list if null
                        .stream()
                        .map(thesis -> thesis.getId())
                        .collect(Collectors.toList()))
                .departmentId(professor.getDepartment() != null ? professor.getDepartment().getId() : null) // Safely handle null department
                .build();
    }

    public List<ProfessorDto> mapToProfessorDtoList(List<Professor> professors){
        return professors.stream().map(this::mapToProfessorDto).collect(Collectors.toList());
    }

    public DepartmentDto mapToDepartmentDto(Department department) {
        return DepartmentDto
                .builder()
                .professors(mapToProfessorDtoList(department.getProfessors()))
                .name(department.getName())
                .build();
    }

    public ReviewDto mapToReviewDto(Review review) {
        return ReviewDto
                .builder()
                .professorId(review.getProfessor().getId())
                .reviewText(review.getReviewText())
                .isApproved(review.getIsApproved())
                .uploadedDate(review.getUploadedDate())
                .thesisId(review.getThesis().getId())
                .build();
    }

    public ThesisApplicationDto mapToThesisApplicationDto(ThesisApplication thesisApplication) {
        return ThesisApplicationDto.builder()
                .title(thesisApplication.getTitle())
                .objectives(thesisApplication.getObjectives())
                .tasks(thesisApplication.getTasks())
                .technologies(thesisApplication.getTechnologies())
                .studentId(thesisApplication.getStudent() != null ? thesisApplication.getStudent().getId() : null)
                .professorId(thesisApplication.getProfessor() != null ? thesisApplication.getProfessor().getId() : null)
                .departmentId(thesisApplication.getDepartment() != null ? thesisApplication.getDepartment().getId() : null)
                .status(thesisApplication.getStatus())
                .submissionDate(thesisApplication.getSubmissionDate())
                .build();
    }

    public ThesisDefenseDto mapToThesisDefenseDto(ThesisDefense thesisDefense) {
        Set<Long> committeeMemberIds = thesisDefense.getCommitteeMembers() != null ?
                thesisDefense.getCommitteeMembers().stream()
                        .map(Professor::getId)
                        .collect(Collectors.toSet()) : null;

        return ThesisDefenseDto.builder()
                .thesisId(thesisDefense.getThesis() != null ? thesisDefense.getThesis().getId() : null)
                .defenseDate(thesisDefense.getDefenseDate())
                .grade(thesisDefense.getGrade())
                .committeeMembers(committeeMemberIds)
                .build();
    }

}
