package com.example.graduationSystem.exceptions;

public class FailedToAssignRoleException extends RuntimeException{
    public FailedToAssignRoleException(String message, String exceptionMessage) {
        super(message + exceptionMessage);
    }
}
