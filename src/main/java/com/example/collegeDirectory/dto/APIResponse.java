package com.example.collegeDirectory.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.http.HttpStatus;

@JsonInclude(JsonInclude.Include.NON_NULL)
public record APIResponse(
        boolean isOperationSuccessful,
        String message,
        Object responseEntity,
        HttpStatus status
){
    public APIResponse(boolean isOperationSuccessful, String message, HttpStatus status) {
        this(isOperationSuccessful, message, null, status);
    }
 }
