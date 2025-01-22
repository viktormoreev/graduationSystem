package com.example.graduationSystem.entity;

import com.example.graduationSystem.enums.Title;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "professors")
public class Professor extends IdGenerator {

    @Column(name = "user_id", nullable = false, unique = true)
    private String userID;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "title", nullable = false)
    private Title title;

    @OneToMany(mappedBy = "supervisor")
    private List<Thesis> supervisedTheses;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

}
