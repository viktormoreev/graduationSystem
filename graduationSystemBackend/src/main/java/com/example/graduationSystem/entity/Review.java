package com.example.graduationSystem.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reviews")
public class Review extends IdGenerator {
    @Column(name = "professor")
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
