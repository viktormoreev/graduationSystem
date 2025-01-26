package com.example.graduationSystem.repository;

import com.example.graduationSystem.entity.Department;
import com.example.graduationSystem.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUserID(String userID);

    List<Professor> findListByDepartment(Department department);
}
