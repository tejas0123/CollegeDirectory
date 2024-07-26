package com.example.collegeDirectory.model;

import jakarta.persistence.Entity;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class CollegeDirectoryStatistics {
    int id;
    private String parameter;
    private int count;

    public CollegeDirectoryStatistics(int id, String parameter, int count) {
        this.parameter = parameter;
        this.count = count;
        this.id = id;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
