package com.example.collegeDirectory.service;

import com.example.collegeDirectory.dto.EnrolledStudentsDTO;
import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.dto.FacultyProfileUpdateDTO;
import com.example.collegeDirectory.exceptions.UserNotFoundException;
import com.example.collegeDirectory.model.FacultyProfile;
import com.example.collegeDirectory.model.Users;
import com.example.collegeDirectory.repository.FacultyEnrolledStudentsRepository;
import com.example.collegeDirectory.repository.FacultyProfileRepository;
import com.example.collegeDirectory.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacultyProfileServiceImpl implements FacultyProfileService{

    @Autowired
    FacultyEnrolledStudentsRepository facultyEnrolledStudentsRepository;
    @Autowired
    UsersService usersService;
    @Autowired
    UsersRepository usersRepository;
    @Autowired
    FacultyProfileRepository facultyProfileRepository;

    @Override
    public ResponseEntity<APIResponse> getEnrolledStudents(int id) {
        List<EnrolledStudentsDTO> enrolledStudents = facultyEnrolledStudentsRepository.getEnrolledStudents(id);
        if(enrolledStudents.isEmpty()){
            return new ResponseEntity<>(
                    new APIResponse(true,
                            "No students enrolled yet",
                            HttpStatus.OK
                    ),
                    HttpStatus.OK
            );
        } else {
            return new ResponseEntity<>(
                    new APIResponse(
                        true,
                        "Enrolled Students' details fetched successfully",
                        enrolledStudents,
                        HttpStatus.OK
                    ), HttpStatus.OK
            );
        }
    }

    @Override
    public ResponseEntity<APIResponse> updateFacultyDetails(FacultyProfileUpdateDTO facultyProfileDTO) {
        Optional<Users> currentFacultyProfile = usersRepository.findById(facultyProfileDTO.getId());
        Optional<FacultyProfile> facultyProfileOptional = facultyProfileRepository.findById(facultyProfileDTO.getId());
        if(currentFacultyProfile.isEmpty() || facultyProfileOptional.isEmpty()){
            throw new UserNotFoundException("Faculty with id " + facultyProfileDTO.getId() + " not found");
        }
        Users faculty = currentFacultyProfile.get();
        FacultyProfile facultyProfile = facultyProfileOptional.get();
        facultyProfile.setOffice_hours(facultyProfileDTO.getOffice_hours());
        facultyProfileRepository.save(facultyProfile);
        faculty.setEmail(facultyProfileDTO.getEmail());
        faculty.setPhone(facultyProfileDTO.getPhone());
        usersRepository.save(faculty);
        return new ResponseEntity<>(
                new APIResponse(true, "Faculty details updated", HttpStatus.OK),
                HttpStatus.ACCEPTED
        );
    }
}
