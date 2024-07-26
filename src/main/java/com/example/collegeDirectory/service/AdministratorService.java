package com.example.collegeDirectory.service;

import com.example.collegeDirectory.dto.APIResponse;
import org.springframework.http.ResponseEntity;

public interface AdministratorService {
    public ResponseEntity<APIResponse> getDeptWiseStudentsCount();
    public ResponseEntity<APIResponse> getUserCountByRole();
    public ResponseEntity<APIResponse> getFacultiesByDept();
}
