package com.example.collegeDirectory.service;

import com.example.collegeDirectory.exceptions.UserNotFoundException;
import com.example.collegeDirectory.model.Users;
import com.example.collegeDirectory.dto.APIResponse;
import com.example.collegeDirectory.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService{

    @Autowired
    UsersRepository usersRepository;
    @Override
    public ResponseEntity<APIResponse> getUserById(int id) {
        Optional<Users> userOptional = usersRepository.findById(id);
        return userOptional.map(users -> new ResponseEntity<>(
                new APIResponse(true, "User found", users, HttpStatus.OK),
                HttpStatus.OK
        )).orElseGet(() -> new ResponseEntity<>(new APIResponse(false,
                "User not found", HttpStatus.OK), HttpStatus.OK));
    }

    @Override
    public ResponseEntity<APIResponse> addUser(Users user) {
        int lastInsertedId = usersRepository.findLastInsertedId();
        lastInsertedId++;
        user.setUsername("USER" + lastInsertedId);
        usersRepository.save(user);
        return new ResponseEntity<>(
                new APIResponse(true, "User added", HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<APIResponse> deleteUser(int id) {
        boolean exists = usersRepository.existsById(id);
        if(!exists){
            return new ResponseEntity<>(
                    new APIResponse(false, "User not found", HttpStatus.BAD_REQUEST),
                    HttpStatus.BAD_REQUEST
            );
        }
        usersRepository.deleteById(id);
        return new ResponseEntity<>(
                new APIResponse(true, "User deleted", HttpStatus.OK),
                HttpStatus.OK
        );
    }

    @Override
    public ResponseEntity<APIResponse> updateUser(int id, Users updatedUser) {
        Optional<Users> optionalUser = usersRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setRole(updatedUser.getRole());
            user.setName(updatedUser.getName());
            user.setEmail(updatedUser.getEmail());
            user.setPhone(updatedUser.getPhone());
            usersRepository.save(user);
            return new ResponseEntity<>(
                    new APIResponse(true, "User details updated", HttpStatus.OK),
                    HttpStatus.ACCEPTED
            );
        } else {
            throw new UserNotFoundException("User with ID " + id + " not found");
        }
    }
}
