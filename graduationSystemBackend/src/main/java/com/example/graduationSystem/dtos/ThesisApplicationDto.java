package com.example.graduationSystem.dtos;

import com.example.graduationSystem.enums.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ThesisApplicationDto {
    private String title;
    private String objectives;
    private String tasks;
    private String technologies;
    private Long studentId;//should change
    private Long professorId;//should change
    private Long departmentId;
    private Status status;
    private LocalDateTime submissionDate;
}
