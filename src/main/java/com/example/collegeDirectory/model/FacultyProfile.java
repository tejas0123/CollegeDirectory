package com.example.collegeDirectory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "facultyprofile")
public class FacultyProfile {
    @Id
    private int user_id;
    private int department_id;
    private String photo;
    private String office_hours;

    public FacultyProfile(int user_id, int department_id, String photo, String office_hours) {
        this.user_id = user_id;
        this.department_id = department_id;
        this.photo = photo;
        this.office_hours = office_hours;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getOffice_hours() {
        return office_hours;
    }

    public void setOffice_hours(String office_hours) {
        this.office_hours = office_hours;
    }

}
