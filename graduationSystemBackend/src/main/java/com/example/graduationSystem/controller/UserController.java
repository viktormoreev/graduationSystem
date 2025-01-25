package com.example.graduationSystem.controller;


import com.example.graduationSystem.dtos.*;
import com.example.graduationSystem.service.implementation.KeycloakAdminClientServiceImpl;
import com.example.graduationSystem.service.implementation.UserEntityHandlingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("api/v1/auth")
public class UserController {

    private final KeycloakAdminClientServiceImpl keycloakAdminClientServiceImpl;

    private final UserEntityHandlingService userEntityHandlingService;

    public UserController(KeycloakAdminClientServiceImpl keycloakAdminClientServiceImpl, UserEntityHandlingService userEntityHandlingService) {
        this.keycloakAdminClientServiceImpl = keycloakAdminClientServiceImpl;
        this.userEntityHandlingService = userEntityHandlingService;
    }

    @PostMapping("/register-professor")
    public ResponseEntity<String> registerProfessor(@RequestBody ProfessorRegistrationRequest request) {
        ApiResponse<String> response = keycloakAdminClientServiceImpl.createUser(request.getUsername(), request.getPassword());

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }

        String userId = response.getData();
        ApiResponse<String> roleResponse = userEntityHandlingService.assignProfessorRole(userId, request.getName(), request.getTitle(), request.getDepartmentId());
        if (!roleResponse.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roleResponse.getMessage());
        }

        return ResponseEntity.ok("Professor registered successfully");
    }

    @PostMapping("/register-student")
    public ResponseEntity<String> registerStudent(@RequestBody StudentRegistrationRequest request) {
        ApiResponse<String> response = keycloakAdminClientServiceImpl.createUser(request.getUsername(), request.getPassword());

        if (!response.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }

        String userId = response.getData();
        ApiResponse<String> roleResponse = userEntityHandlingService.assignStudentRole(userId, request.getName());
        if (!roleResponse.isSuccess()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(roleResponse.getMessage());
        }

        return ResponseEntity.ok("Student registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody UserAuthorizationRequest userAuthorizationRequest) {
        ApiResponse<Map<String, Object>> response = keycloakAdminClientServiceImpl.loginUser(userAuthorizationRequest.getUsername(), userAuthorizationRequest.getPassword());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getData());
        }
    }

    @GetMapping("/info")
    public ResponseEntity<UserInfoResponse> getUserInfo(@AuthenticationPrincipal Jwt jwt) {
        ApiResponse<UserInfoResponse> response = keycloakAdminClientServiceImpl.getUserInfo(jwt);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
/*
    @Deprecated
    @PostMapping("/assign-role")
    public ResponseEntity<String> assignRoleStudent(@RequestBody UserRoleRequest userAssignRoleRequest) {
        ApiResponse<String> response = userEntityHandlingService.assignRole(userAssignRoleRequest.getUserID(), userAssignRoleRequest.getRole());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

    @Deprecated
    @PostMapping("/assign-role-username")
    public ResponseEntity<String> assignRoleByUsername(@RequestBody UserRoleUsernameRequest userAssignRoleUsernameRequest) {
        ApiResponse<String> response = userEntityHandlingService.assignRoleUsername(userAssignRoleUsernameRequest.getUsername(), userAssignRoleUsernameRequest.getRole());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

 */

    @DeleteMapping("/delete-user/uid={userId}")
    public ResponseEntity<String> deleteUser(@PathVariable String userId) {
        ApiResponse<String> response = userEntityHandlingService.deleteUser(userId);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

    @DeleteMapping("/delete-user/username={username}")
    public ResponseEntity<String> deleteUserByUsername(@PathVariable String username) {
        ApiResponse<String> response = userEntityHandlingService.deleteUserByUsername(username);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

    @PutMapping("/update-credentials")
    public ResponseEntity<String> updateUserCredentials(@RequestBody UserUpdateCredentialsRequest userUpdateCredentialsRequest) {
        ApiResponse<String> response = keycloakAdminClientServiceImpl.updateUserCredentials(userUpdateCredentialsRequest);
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

    @PostMapping("/remove-role")
    public ResponseEntity<String> removeRole(@RequestBody UserRoleRequest userRemoveRoleRequest) {
        ApiResponse<String> response = userEntityHandlingService.removeRole(userRemoveRoleRequest.getUserID(), userRemoveRoleRequest.getRole());
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

    @GetMapping("/token")
    public ResponseEntity<Jwt> getToken(@AuthenticationPrincipal Jwt jwt) {
        return ResponseEntity.ok(jwt);
    }

    @GetMapping("/users")
    public ResponseEntity<Map<String, String>> getUsers() {
        ApiResponse<Map<String, String>> response = keycloakAdminClientServiceImpl.getAllUsersAndRoles();
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getData());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }
/*
    @PatchMapping("/switch-role")
    public ResponseEntity<String> switchUserRole(@RequestBody SwitchUserRoleRequest switchUserRoleRequest) {
        ApiResponse<String> response = userEntityHandlingService.switchUserRole(
                switchUserRoleRequest.getUserID(),
                switchUserRoleRequest.getRole()
        );
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

    @PatchMapping("/switch-role-username")
    public ResponseEntity<String> switchUserRoleByUsername(@RequestBody SwitchUserUsernameRoleRequest switchUserRoleRequest) {
        ApiResponse<String> response = userEntityHandlingService.switchUserRoleByUsername(
                switchUserRoleRequest.getUsername(),
                switchUserRoleRequest.getRole()
        );
        if (response.isSuccess()) {
            return ResponseEntity.ok(response.getMessage());
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response.getMessage());
        }
    }

 */
}
