package com.example.graduationSystem.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@Table(name = "reviews")
public class Review extends IdGenerator {
    @ManyToOne
    @JoinColumn(name = "professor_id", nullable = false)
    private Professor professor;

    @Column(name = "review_text", nullable = false)
    private String reviewText;

    @Column(name = "is_approved", nullable = false)
    private Boolean isApproved;

    @Column(name = "uploaded_date", nullable = false)
    private LocalDateTime uploadedDate;

    @OneToOne
    @JoinColumn(name = "thesis_id")
    private Thesis thesis;

}
