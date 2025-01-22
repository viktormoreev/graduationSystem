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
@Table(name = "students")
public class Student extends IdGenerator{

    @Column(name = "user_id", nullable = false, unique = true)
    private String userID;

    @Column(name = "name", nullable = false)
    private String name;

    @OneToMany(mappedBy = "student")
    private List<ThesisApplication> thesis;

}
