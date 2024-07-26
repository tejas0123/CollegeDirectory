package com.example.collegeDirectory.controller;

import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.dto.FacultyProfileUpdateDTO;
import com.example.collegeDirectory.service.FacultyProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/faculty")
public class FacultyProfileController {

    @Autowired
    FacultyProfileServiceImpl facultyProfileService;

    @GetMapping("/enrolled-students/{id}")
    public ResponseEntity<APIResponse> getEnrolledStudents(@PathVariable("id") int id){
        return facultyProfileService.getEnrolledStudents(id);
    }

    @PutMapping
    public ResponseEntity<APIResponse> updateFacultyProfile(@RequestBody FacultyProfileUpdateDTO facultyProfileUpdateDTO){
        return facultyProfileService.updateFacultyDetails(facultyProfileUpdateDTO);
    }
}
