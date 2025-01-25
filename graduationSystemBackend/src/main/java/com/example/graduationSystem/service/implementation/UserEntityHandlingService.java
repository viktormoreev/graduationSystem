package com.example.graduationSystem.service.implementation;


import com.example.graduationSystem.dtos.ApiResponse;
import com.example.graduationSystem.dtos.UserIDRequest;
import com.example.graduationSystem.enums.Title;
import com.example.graduationSystem.exceptions.FailedToAssignRoleException;
import com.example.graduationSystem.exceptions.UserNotFoundException;
import com.example.graduationSystem.service.StudentService;
import com.example.graduationSystem.service.ProfessorService;
import org.keycloak.admin.client.resource.UserResource;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.management.relation.RoleNotFoundException;
import java.util.Collections;
import java.util.List;

import static com.example.graduationSystem.service.implementation.KeycloakAdminClientService.VALID_ROLES;

@Service
public class UserEntityHandlingService {

    @Autowired
    private static final Logger logger = LoggerFactory.getLogger(UserEntityHandlingService.class);

    @Autowired
    private KeycloakAdminClientService keycloakAdminClientService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private StudentService studentService;

 /*
    public ApiResponse<String> switchUserRoleByUsername(String username, String newRoleName) {
        if (username == null || username.isEmpty() || newRoleName == null || newRoleName.isEmpty()) {
            return new ApiResponse<>(false, "Username or new role name cannot be null or empty", null);
        }

        if (!VALID_ROLES.contains(newRoleName)) {
            return new ApiResponse<>(false, "Invalid new role name", null);
        }

        try {
            // Search for the user by username
            List<UserRepresentation> users = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().search(username);
            if (users == null || users.isEmpty()) {
                return new ApiResponse<>(false, "User not found", null);
            }

            UserRepresentation user = users.get(0);
            return switchUserRole(user.getId(), newRoleName);
        } catch (Exception e) {
            logger.error("Error switching role: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to switch role: " + e.getMessage(), null);
        }
    }


    public ApiResponse<String> switchUserRole(String userId, String newRoleName) {
        if (userId == null || userId.isEmpty() || newRoleName == null || newRoleName.isEmpty()) {
            return new ApiResponse<>(false, "User ID or new role name cannot be null or empty", null);
        }

        if (!VALID_ROLES.contains(newRoleName)) {
            return new ApiResponse<>(false, "Invalid new role name", null);
        }

        try {
            UserResource userResource = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().get(userId);
            List<RoleRepresentation> currentRoles = userResource.roles().realmLevel().listAll();
            List<RoleRepresentation> rolesToRemove = new ArrayList<>();

            for (RoleRepresentation role : currentRoles) {
                if (VALID_ROLES.contains(role.getName())) {
                    rolesToRemove.add(role);
                    deleteEntityForRole(userId, role.getName());
                }
            }

            if (!rolesToRemove.isEmpty()) {
                userResource.roles().realmLevel().remove(rolesToRemove);
            }

            RoleRepresentation newRole = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).roles().get(newRoleName).toRepresentation();
            userResource.roles().realmLevel().add(Collections.singletonList(newRole));

            createEntityForRole(userId, newRoleName);

            return new ApiResponse<>(true, "Role switched successfully", null);
        } catch (Exception e) {
            logger.error("Error switching role: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to switch role: " + e.getMessage(), null);
        }
    }

     */

    public ApiResponse<String> deleteUser(String userId) {
        try {
            UserResource userResource = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().get(userId);
            List<RoleRepresentation> currentRoles = userResource.roles().realmLevel().listAll();

            for (RoleRepresentation role : currentRoles) {

                logger.info("Role: {}", role.getName());
                if (VALID_ROLES.contains(role.getName())) {
                    logger.info("Deleting entity for role: {}", role.getName());
                    deleteEntityForRole(userId, role.getName());
                }
            }

            keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().delete(userId);

            return new ApiResponse<>(true, "User deleted successfully", null);
        } catch (Exception e) {
            logger.error("Error deleting user: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to delete user", null);
        }
    }

    public ApiResponse<String> deleteUserByUsername(String username) {
        try {
            UserRepresentation user = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().search(username).get(0);

            return deleteUser(user.getId());
        } catch (Exception e) {
            logger.error("Error deleting user by username: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to delete user by username", null);
        }
    }

    public ApiResponse<String> removeRole(String userId, String roleName) {
        try {
            RoleRepresentation role = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).roles().get(roleName).toRepresentation();
            keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().get(userId).roles().realmLevel().remove(Collections.singletonList(role));

            deleteEntityForRole(userId, roleName);

            return new ApiResponse<>(true, "Role removed successfully", null);
        } catch (Exception e) {
            logger.error("Error removing role: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to remove role", null);
        }
    }

    /*
    public ApiResponse<String> assignRoleUsername(String username, String roleName) {
        try {
            UserRepresentation user = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().search(username).get(0);
            return assignRole(user.getId(), roleName);
        } catch (Exception e) {
            logger.error("Error assigning role by username: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to assign role by username", null);
        }
    }

     */


    public ApiResponse<String> assignStudentRole(String userId,String name){
        try {
            RoleRepresentation role = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).roles().get("student").toRepresentation();
            if (role.equals(null)) {
                return new ApiResponse<>(false, "Role not found", null);
            }

            UserResource userResource = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().get(userId);
            if (userResource == null) {
                return new ApiResponse<>(false, "User not found", null);
            }

            userResource.roles().realmLevel().add(Collections.singletonList(role));

            // Create respective entity in the database
            createEntityForStudent(userId, name);

            return new ApiResponse<>(true, "Role assigned successfully", null);
        } catch (Exception e) {
            logger.error("Error assigning role: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to assign role: " + e.getMessage(), null);
        }
    }

    public ApiResponse<String> assignProfessorRole(String userId,String name, Title title, Long departmentId){
        try {
            RoleRepresentation role = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).roles().get("professor").toRepresentation();
            if (role.equals(null)) {
                throw new RoleNotFoundException("Role not found");
            }

            UserResource userResource = keycloakAdminClientService.keycloak.realm(keycloakAdminClientService.keycloakRealm).users().get(userId);
            if (userResource.equals(null)) {
                throw new UserNotFoundException("User not found");
            }

            userResource.roles().realmLevel().add(Collections.singletonList(role));

            // Create respective entity in the database
            createEntityForProfessor(userId, name, title, departmentId);

            return new ApiResponse<>(true, "Role assigned successfully", null);
        } catch (Exception e) {
            logger.error("Error assigning role: {}", e.getMessage(), e);
            throw new FailedToAssignRoleException("Failed to assign role: ", e.getMessage());
        }
    }

    private void createEntityForStudent(String userId, String name){
        studentService.addStudent(new UserIDRequest(userId), name);
    }

    private void createEntityForProfessor(String userId, String name, Title title, Long departmentId){
        professorService.addProfessor(new UserIDRequest(userId), name, title, departmentId);
    }

    private void deleteEntityForRole(String userId, String roleName) {
        switch (roleName.toLowerCase()) {
            case "professor":
                professorService.deleteProfessorUID(userId);
                break;
            case "student":
                studentService.deleteStudentByUID(userId);
                break;
            default:
        }
    }

}
