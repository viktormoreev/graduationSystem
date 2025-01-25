package com.example.graduationSystem.repository;

import com.example.graduationSystem.entity.ThesisApplication;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisApplicationRepository extends JpaRepository<ThesisApplication, Long> {
}
