package com.example.collegeDirectory.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Table(name = "administratorprofile")
public class AdministratorProfile {
    @Id
    private int user_id;
    private int department_id;
    private String photo;

    public AdministratorProfile(int user_id, int department_id, String photo) {
        this.user_id = user_id;
        this.department_id = department_id;
        this.photo = photo;
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

}
