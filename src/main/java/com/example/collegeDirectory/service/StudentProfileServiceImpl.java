package com.example.collegeDirectory.service;

import com.example.collegeDirectory.exceptions.UserNotFoundException;
import com.example.collegeDirectory.dto.UserDetailsDTO;
import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.repository.UserDetailsRepository;
import com.example.collegeDirectory.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentProfileServiceImpl implements StudentProfileService{

    @Autowired
    StudentProfileRepository studentProfileRepository;

    @Autowired
    UserDetailsRepository userDetailsRepository;

    @Override
    public ResponseEntity<APIResponse> getStudentProfile(int id){
        Optional<UserDetailsDTO> studentProfileOptional = userDetailsRepository.getUserProfile(id);
        if(studentProfileOptional.isEmpty()){
            throw new UserNotFoundException("Student with id " + id + " not found");
        } else{
            return new ResponseEntity<>(
                    new APIResponse(
                            true,
                            "Fetched Student Profile successfully",
                            studentProfileOptional.get(),
                            HttpStatus.OK
                    ),
                    HttpStatus.OK
            );
        }
    }

    @Override
    public ResponseEntity<APIResponse> getStudentDetails(String name, String year, String department) {
        List<UserDetailsDTO> userDetailsDTO = userDetailsRepository.getStudentDetails(name, year, department);
        if(userDetailsDTO.isEmpty()){
            throw new UserNotFoundException("Student details not found");
        } else{

            System.out.println(name + " " + department + " " + year);
            return new ResponseEntity<>(
                    new APIResponse(
                            true,
                            "Fetched Student details successfully",
                            userDetailsDTO,
                            HttpStatus.OK
                    ),
                    HttpStatus.OK
            );
        }
    }

    @Override
    public ResponseEntity<APIResponse> getFacultyMembers(int id) {
        List<UserDetailsDTO> facultyMembers = userDetailsRepository.getFacultyMembers(id);
        if(facultyMembers.isEmpty()){
            throw new UserNotFoundException("Faculty details not found");
        } else{

            return new ResponseEntity<>(
                    new APIResponse(
                            true,
                            "Fetched Faculty details successfully",
                            facultyMembers,
                            HttpStatus.OK
                    ),
                    HttpStatus.OK
            );
        }
    }
}
