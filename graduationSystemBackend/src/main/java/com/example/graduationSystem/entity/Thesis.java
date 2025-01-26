package com.example.graduationSystem.entity;

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
@Table(name = "thesis")
public class Thesis extends IdGenerator{

    @Column(name = "thesis_text", nullable = false)
    private String thesisText;

    @Column(name = "uploaded_date", nullable = false)
    private LocalDateTime uploadedDate;

    @OneToOne(mappedBy = "thesis", cascade = CascadeType.ALL)
    private Review review;

    @ManyToOne
    @JoinColumn(name = "supervisor_id") // Ensure this matches the DB column name
    private Professor supervisor;

    @OneToOne
    @JoinColumn(name = "application_id")
    private ThesisApplication application;

    @OneToOne(mappedBy = "thesis", cascade = CascadeType.ALL)
    private ThesisDefense defense;

}
