package com.example.graduationSystem.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserRoleUsernameRequest {
    String username;
    String role;
}
