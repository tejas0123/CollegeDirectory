package com.example.collegeDirectory.controller;

import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/stats")
public class AdministratorController {

    @Autowired
    AdministratorService administratorService;
    @GetMapping("/getDeptWiseStudents")
    public ResponseEntity<APIResponse> getDeptWiseStudentsCount(){
        return administratorService.getDeptWiseStudentsCount();
    }

    @GetMapping("/getUsersByRole")
    public ResponseEntity<APIResponse> getUsersByRole(){
        return administratorService.getUserCountByRole();
    }

    @GetMapping("/getDeptWiseFaculties")
    public ResponseEntity<APIResponse> getDeptWiseFacultiesCount(){
        return administratorService.getFacultiesByDept();
    }
}
