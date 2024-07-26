package com.example.collegeDirectory.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class EnrolledStudentsDTO {
    @Id
    private int id;
    private String email;

    public EnrolledStudentsDTO(int id, String email, String phone, String name) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.name = name;
    }

    private String phone;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



}
