package com.example.graduationSystem.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ReviewDto {
    private Long professorId;//should change
    private String reviewText;
    private Boolean isApproved;
    private LocalDateTime uploadedDate;
    private Long thesisId;
}
