package com.example.graduationSystem.service.implementation;

import com.example.graduationSystem.dtos.ApiResponse;
import com.example.graduationSystem.dtos.UserInfoResponse;
import com.example.graduationSystem.dtos.UserUpdateCredentialsRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class KeycloakAdminClientServiceImpl {

    private static final Logger logger = LoggerFactory.getLogger(KeycloakAdminClientServiceImpl.class);

    @Value("${keycloak.auth-server-url}")
    public String keycloakAdminUrl;

    @Value("${keycloak.realm}")
    public String keycloakRealm;

    @Value("${keycloak.resource}")
    public String clientId;

    @Value("${keycloak.admin.username}")
    public String keycloakAdminUsername;

    @Value("${keycloak.admin.password}")
    public String keycloakAdminPassword;

    @Autowired
    public Keycloak keycloak;

    public static final List<String> VALID_ROLES = Arrays.asList("admin", "professor", "student");

    private final RestTemplate restTemplate;

    public KeycloakAdminClientServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ApiResponse<Map<String, Object>> loginUser(String username, String password) {
        logger.info("Logging in user: {}", username);

        try {
            String loginUrl = String.format("%s/realms/%s/protocol/openid-connect/token", keycloakAdminUrl, keycloakRealm);

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(org.springframework.http.MediaType.valueOf(MediaType.APPLICATION_FORM_URLENCODED));

            String body = String.format("grant_type=password&client_id=student-rest-api&username=%s&password=%s",
                    username, password);

            HttpEntity<String> requestEntity = new HttpEntity<>(body, headers);
            ResponseEntity<String> response = restTemplate.exchange(loginUrl, HttpMethod.POST, requestEntity, String.class);

            if (response.getStatusCode().is2xxSuccessful()) {
                String responseBody = response.getBody();
                String accessToken = extractAccessToken(responseBody);
                String role = stripRoles(extractRolesFromToken(accessToken));

                Map<String, Object> responseBodyMap = new HashMap<>();
                responseBodyMap.put("access_token", accessToken);
                responseBodyMap.put("role", role);

                return new ApiResponse<>(true, "User logged in successfully", responseBodyMap);
            } else {
                return new ApiResponse<>(false, "Failed to login user: " + response.getStatusCode(), null);
            }
        } catch (Exception e) {
            logger.error("Error logging in user: {}", e.getMessage(), e);
            throw new RuntimeException("Failed to login user", e);
        }
    }

    public UserRepresentation getUserFromUsername(String username) {
        return keycloak.realm(keycloakRealm).users().search(username).get(0);
    }

    public UserRepresentation getUserFromUserID(String userId) {
        try {
            return keycloak.realm(keycloakRealm).users().get(userId).toRepresentation();
        } catch (Exception e) {
            logger.error("Error fetching user with ID {}: {}", userId, e.getMessage(), e);
            return null;
        }
    }

    public ApiResponse<String> createUser(String username, String password) {
        logger.info("Creating user: {}", username);
        try {
            UserRepresentation user = new UserRepresentation();
            user.setUsername(username);
            user.setEnabled(true);

            CredentialRepresentation credential = new CredentialRepresentation();
            credential.setTemporary(false);
            credential.setType(CredentialRepresentation.PASSWORD);
            credential.setValue(password);

            user.setCredentials(Collections.singletonList(credential));

            Response response = keycloak.realm(keycloakRealm).users().create(user);
            if (response.getStatus() == 201) {
                String userId = extractUserIdFromLocationHeader(response.getLocation());
                return new ApiResponse<>(true, "User created successfully", userId);
            } else {
                return new ApiResponse<>(false, "Failed to create user: " + response.getStatusInfo().getReasonPhrase(), null);
            }
        } catch (Exception e) {
            logger.error("Error creating user: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to create user", null);
        }
    }

    public ApiResponse<String> updateUserCredentials(UserUpdateCredentialsRequest userUpdateCredentialsRequest) {
        try {
            UserRepresentation user = keycloak.realm(keycloakRealm).users().get(userUpdateCredentialsRequest.getUserID()).toRepresentation();

            if (userUpdateCredentialsRequest.getUsername() != null && !userUpdateCredentialsRequest.getUsername().isEmpty()) {
                user.setUsername(userUpdateCredentialsRequest.getUsername());
            }

            if (userUpdateCredentialsRequest.getPassword() != null && !userUpdateCredentialsRequest.getPassword().isEmpty()) {
                CredentialRepresentation credential = new CredentialRepresentation();
                credential.setTemporary(false);
                credential.setType(CredentialRepresentation.PASSWORD);
                credential.setValue(userUpdateCredentialsRequest.getPassword());
                user.setCredentials(Collections.singletonList(credential));
            }

            keycloak.realm(keycloakRealm).users().get(userUpdateCredentialsRequest.getUserID()).update(user);
            return new ApiResponse<>(true, "User credentials updated successfully", null);
        } catch (Exception e) {
            logger.error("Error updating user credentials: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to update user credentials", null);
        }
    }

    public ApiResponse<UserInfoResponse> getUserInfo(Jwt jwt) {
        String username = jwt.getClaimAsString("preferred_username");
        String userId = jwt.getClaimAsString("sub");
        Map<String, Object> realmAccess = jwt.getClaim("realm_access");
        List<String> roles = (List<String>) realmAccess.get("roles");
        String role = stripRoles(roles);

        UserInfoResponse userInfoResponse = new UserInfoResponse(username, userId, role);
        return new ApiResponse<>(true, "User info retrieved successfully", userInfoResponse);
    }

    public String stripRoles(List<String> rolesMap) {
        return rolesMap.stream()
                .filter(VALID_ROLES::contains)
                .min((r1, r2) -> Integer.compare(VALID_ROLES.indexOf(r1), VALID_ROLES.indexOf(r2)))
                .orElse("unknown");
    }

    public ApiResponse<Map<String, String>> getAllUsersAndRoles() {
        try {
            List<UserRepresentation> users = keycloak.realm(keycloakRealm).users().list();
            Map<String, String> userRolesMap = new HashMap<>();

            for (UserRepresentation user : users) {
                List<RoleRepresentation> roles = keycloak.realm(keycloakRealm)
                        .users()
                        .get(user.getId())
                        .roles()
                        .realmLevel()
                        .listAll();
                List<String> roleNames = new ArrayList<>();
                for (RoleRepresentation role : roles) {
                    roleNames.add(role.getName());
                }
                userRolesMap.put(user.getUsername(), stripRoles(roleNames));
            }
            return new ApiResponse<>(true, "Users and roles retrieved successfully", userRolesMap);
        } catch (Exception e) {
            logger.error("Error getting users and roles: {}", e.getMessage(), e);
            return new ApiResponse<>(false, "Failed to get users and roles", null);
        }
    }

    public List<UserRepresentation> getUsersFromIDs(List<String> userIds) {
        return userIds.parallelStream()
                .map(userId -> {
                    try {
                        return keycloak.realm(keycloakRealm).users().get(userId).toRepresentation();
                    } catch (Exception e) {
                        logger.error("Error fetching user with ID {}: {}", userId, e.getMessage(), e);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    private String extractUserIdFromLocationHeader(URI location) {
        if (location != null) {
            String path = location.getPath();
            return path.substring(path.lastIndexOf('/') + 1);
        }
        throw new RuntimeException("Location header is missing or invalid");
    }

    private String extractAccessToken(String responseBody) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> map = mapper.readValue(responseBody, Map.class);
            return (String) map.get("access_token");
        } catch (Exception e) {
            throw new RuntimeException("Failed to parse access token from response", e);
        }
    }

    private List<String> extractRolesFromToken(String token) {
        try {
            String[] parts = token.split("\\.");
            String payload = new String(Base64.getUrlDecoder().decode(parts[1]));
            ObjectMapper mapper = new ObjectMapper();
            Map<String, Object> payloadMap = mapper.readValue(payload, Map.class);
            Map<String, Object> realmAccess = (Map<String, Object>) payloadMap.get("realm_access");
            return (List<String>) realmAccess.get("roles");
        } catch (Exception e) {
            throw new RuntimeException("Failed to extract roles from token", e);
        }
    }

}
