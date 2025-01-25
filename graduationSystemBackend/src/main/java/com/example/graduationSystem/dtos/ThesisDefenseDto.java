package com.example.graduationSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ThesisDefenseDto {
    private Long thesisId;
    private Date defenseDate;
    private Double grade;
    private Set<Long> committeeMembers;//should change
}
