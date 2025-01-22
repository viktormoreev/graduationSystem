package com.example.graduationSystem.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "department")
public class Department extends IdGenerator {
    @Column(nullable = false, length = 100)
    private String name;

    @OneToMany(mappedBy = "department")
    private List<Professor> professors;
}
