package com.example.collegeDirectory.controller;

import com.example.collegeDirectory.model.Users;
import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.service.UsersServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/user")
public class UsersController {

    @Autowired
    UsersServiceImpl usersService;

    @GetMapping("/{id}")
    public ResponseEntity<APIResponse> getUserById(@PathVariable("id") int id){
        return usersService.getUserById(id);
    }

    @PostMapping
    public ResponseEntity<APIResponse> addUser(@RequestBody Users user){
        return usersService.addUser(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<APIResponse> deleteUserById(@PathVariable("id") int id){
        return usersService.deleteUser(id);
    }

    @PutMapping("/{id}")
    public ResponseEntity<APIResponse> updateUser(@PathVariable int id, @RequestBody Users user) {
        return usersService.updateUser(id,user);
    }
}
