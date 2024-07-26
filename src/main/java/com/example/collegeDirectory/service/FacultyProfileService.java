package com.example.collegeDirectory.service;

import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.dto.FacultyProfileUpdateDTO;
import org.springframework.http.ResponseEntity;

public interface FacultyProfileService {
    public ResponseEntity<APIResponse> getEnrolledStudents(int id);
    public ResponseEntity<APIResponse> updateFacultyDetails(FacultyProfileUpdateDTO facultyProfileDTO);
}
