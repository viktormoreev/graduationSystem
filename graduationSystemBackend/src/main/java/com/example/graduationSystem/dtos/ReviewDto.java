package com.example.graduationSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDto {
    private Long professorId;//should change
    private String reviewText;
    private Boolean isApproved;
    private Date uploadedDate;
    private Long thesisId;
}
