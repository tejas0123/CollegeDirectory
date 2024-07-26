package com.example.collegeDirectory.service;

import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.model.CollegeDirectoryStatistics;
import com.example.collegeDirectory.repository.CollegeDirectoryStatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdministratorServiceImpl implements AdministratorService {

    @Autowired
    CollegeDirectoryStatsRepository directoryStatsRepository;
    @Override
    public ResponseEntity<APIResponse> getDeptWiseStudentsCount() {
        List<CollegeDirectoryStatistics> stats = directoryStatsRepository.getDeptWiseStudentCount();
        return new ResponseEntity<>(new APIResponse(
                true, "Stats fetch successful", stats, HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse> getUserCountByRole() {
        List<CollegeDirectoryStatistics> stats = directoryStatsRepository.getUsersCountByRole();
        return new ResponseEntity<>(new APIResponse(
                true, "Stats fetch successful", stats, HttpStatus.OK
        ), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<APIResponse> getFacultiesByDept() {
        List<CollegeDirectoryStatistics> stats = directoryStatsRepository.getDeptWiseFacultiesCount();
        return new ResponseEntity<>(new APIResponse(
                true, "Stats fetch successful", stats, HttpStatus.OK
        ), HttpStatus.OK);
    }
}
