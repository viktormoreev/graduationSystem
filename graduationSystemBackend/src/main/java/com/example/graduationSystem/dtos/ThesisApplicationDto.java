package com.example.graduationSystem.dtos;

import com.example.graduationSystem.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ThesisApplicationDto {
    private String title;
    private String objectives;
    private String tasks;
    private String technologies;
    private Long studentId;//should change
    private Long professorId;//should change
    private Long departmentId;
    private Status status;
    private Date submissionDate;
}
