package com.example.collegeDirectory.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class FacultyProfileUpdateDTO {
    @Id
    private int id;
    private String email;
    private String phone;
    private String office_hours;

    public FacultyProfileUpdateDTO(int id, String email, String phone, String office_hours) {
        this.id = id;
        this.email = email;
        this.phone = phone;
        this.office_hours = office_hours;
    }

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

    public String getOffice_hours() {
        return office_hours;
    }

    public void setOffice_hours(String office_hours) {
        this.office_hours = office_hours;
    }

}
