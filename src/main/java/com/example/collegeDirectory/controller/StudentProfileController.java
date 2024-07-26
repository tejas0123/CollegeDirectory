package com.example.collegeDirectory.controller;

import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.service.StudentProfileServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/student")
public class StudentProfileController {

    @Autowired
    StudentProfileServiceImpl studentProfileService;

    @GetMapping("/profile/{id}")
    public ResponseEntity<APIResponse> getStudentProfile(@PathVariable("id") int id){
        return studentProfileService.getStudentProfile(id);
    }

    @GetMapping
    public ResponseEntity<APIResponse> getStudentDetails(@RequestParam(required = false) String name,
                                                         @RequestParam(required = false) String department,
                                                         @RequestParam(required = false) String year){
        return studentProfileService.getStudentDetails(name, year, department);
    }

    @GetMapping("/faculties/{id}")
    public ResponseEntity<APIResponse> getFacultyMembers(@PathVariable("id") int id){
        return studentProfileService.getFacultyMembers(id);
    }

}
