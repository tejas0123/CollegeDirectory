package com.example.collegeDirectory.advice;

import com.example.collegeDirectory.exceptions.UserNotFoundException;
import com.example.collegeDirectory.dto.APIResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class UserExceptions {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<APIResponse> handleEmployeeNotFoundException(UserNotFoundException employeeNotFoundException){
        return new ResponseEntity<>(
                    new APIResponse(false, employeeNotFoundException.getMessage(), HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST
                );
    }
}
