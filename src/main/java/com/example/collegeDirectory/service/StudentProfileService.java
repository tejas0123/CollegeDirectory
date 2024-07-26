package com.example.collegeDirectory.service;

import com.example.collegeDirectory.dto.APIResponse;
import org.springframework.http.ResponseEntity;

public interface StudentProfileService {
    public ResponseEntity<APIResponse> getStudentProfile(int id);
    public ResponseEntity<APIResponse> getStudentDetails(String name, String year, String department);
    public ResponseEntity<APIResponse> getFacultyMembers(int id);
}
