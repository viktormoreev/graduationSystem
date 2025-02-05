package com.example.graduationSystem.dtos;

import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentDto {
    private String userID;
    private String name;
    private List<Long> thesis;
}
