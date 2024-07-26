package com.example.collegeDirectory.service;

import com.example.collegeDirectory.model.Users;
import com.example.collegeDirectory.dto.APIResponse;
import org.springframework.http.ResponseEntity;

public interface UsersService {
    public ResponseEntity<APIResponse> getUserById(int id);
    public ResponseEntity<APIResponse> addUser(Users user);
    public ResponseEntity<APIResponse> deleteUser(int id);
    public ResponseEntity<APIResponse> updateUser(int id, Users user);

}
