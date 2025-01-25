package com.example.graduationSystem.repository;

import com.example.graduationSystem.service.ThesisService;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisServiceRepository extends JpaRepository<ThesisService, Long> {
}
