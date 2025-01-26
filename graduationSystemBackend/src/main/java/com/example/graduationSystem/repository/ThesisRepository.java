package com.example.graduationSystem.repository;

import com.example.graduationSystem.entity.Thesis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ThesisRepository extends JpaRepository<Thesis, Long> {
}
