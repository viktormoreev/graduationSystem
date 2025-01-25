package com.example.graduationSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class StudentRegistrationRequest {
    private String username;
    private String password;
    private String name;
}
