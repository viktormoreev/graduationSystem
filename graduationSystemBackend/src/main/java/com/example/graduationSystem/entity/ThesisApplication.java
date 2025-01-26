package com.example.graduationSystem.entity;

import com.example.graduationSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "thesis_applications")
public class ThesisApplication extends IdGenerator{
    @Column(nullable = false)
    private String title;

    @Column(nullable = false, length = 500)
    private String objectives;

    @Column(nullable = false, length = 500)
    private String tasks;

    @Column(nullable = false, length = 500)
    private String technologies;

    @ManyToOne
    @JoinColumn(name = "student_id", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    private Department department;

    @Column(nullable = false)
    private Status status;

    @Column(name = "submission_date", nullable = false)
    private LocalDateTime submissionDate;

    @OneToOne(mappedBy = "application", cascade = CascadeType.ALL)
    private Thesis thesis;

}
