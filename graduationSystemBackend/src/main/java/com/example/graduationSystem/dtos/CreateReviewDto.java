package com.example.graduationSystem.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateReviewDto {

    @NotNull(message = "Professor ID cannot be null")
    private Long professorId;

    @Size(min = 1, message = "Review text cannot be empty")
    @NotNull(message = "Review text be null")
    private String reviewText;

    @NotNull
    private Boolean isApproved;

    @NotNull(message = "Thesis ID cannot be null")
    private Long thesisId;
}
