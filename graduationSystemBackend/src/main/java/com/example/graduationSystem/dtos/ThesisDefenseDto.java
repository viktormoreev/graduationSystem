package com.example.graduationSystem.dtos;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThesisDefenseDto {
    private Long thesisId;
    private LocalDateTime defenseDate;
    private Double grade;
    private Set<Long> committeeMembers;//should change
}
