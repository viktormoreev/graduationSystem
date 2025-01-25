package com.example.graduationSystem.repository;

import com.example.graduationSystem.entity.Professor;
import com.example.graduationSystem.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProfessorRepository extends JpaRepository<Professor, Long> {
    Optional<Professor> findByUserID(String userID);
}
