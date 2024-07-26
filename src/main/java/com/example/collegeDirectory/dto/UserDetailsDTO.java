package com.example.collegeDirectory.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class UserDetailsDTO {
        @Id
        int id;
        private String name;
        private String email;
        private String department;
        private String year;
        private String phone;

        public String getPhone() {
                return phone;
        }

        public void setPhone(String phone) {
                this.phone = phone;
        }

        public UserDetailsDTO(int id, String name, String email, String department, String year, String phone){
                this.id = id;
                this.name = name;
                this.email = email;
                this.department = department;
                this.year = year;
                this.phone = phone;
        }

        public UserDetailsDTO(int id, String name, String email, String phone){
                this.id = id;
                this.name = name;
                this.email = email;
                this.phone = phone;
        }

        public int getId() {
                return id;
        }

        public void setId(int id) {
                this.id = id;
        }

        public String getName() {
                return name;
        }

        public void setName(String name) {
                this.name = name;
        }

        public String getEmail() {
                return email;
        }

        public void setEmail(String email) {
                this.email = email;
        }

        public String getDepartment() {
                return department;
        }

        public void setDepartment(String department) {
                this.department = department;
        }

        public String getYear() {
                return year;
        }

        public void setYear(String year) {
                this.year = year;
        }

}
