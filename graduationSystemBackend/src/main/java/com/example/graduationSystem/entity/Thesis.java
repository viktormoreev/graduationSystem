package com.example.graduationSystem.entity;

import com.example.graduationSystem.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "thesis")
public class Thesis extends IdGenerator{

    @Column(name = "thesis_text", nullable = false)
    private String thesisText;

    @Column(name = "uploaded_date", nullable = false)
    private Date uploadedDate;

    @OneToOne(mappedBy = "review", cascade = CascadeType.ALL)
    private Review review;

    @OneToOne
    @JoinColumn(name = "application_id")
    private ThesisApplication application;

}
