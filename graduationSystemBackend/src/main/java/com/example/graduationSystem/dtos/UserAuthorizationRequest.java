package com.example.graduationSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class UserAuthorizationRequest {

    private String username;
    private String password;
}
