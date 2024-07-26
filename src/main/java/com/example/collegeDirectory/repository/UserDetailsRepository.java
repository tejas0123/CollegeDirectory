package com.example.collegeDirectory.repository;

import com.example.collegeDirectory.dto.UserDetailsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserDetailsRepository extends JpaRepository<UserDetailsDTO, Integer> {
    @Query(value = """
            SELECT\s
                u.id as id,
                u.name AS name,
                u.email AS email,
                d.name AS department,
                sp.year AS year,
                u.phone AS phone
            FROM\s
                Users u
            JOIN\s
                StudentProfile sp ON u.id = sp.user_id
            JOIN\s
                Department d ON sp.department_id = d.id
            WHERE\s
                u.name like ?1% or sp.year=?2 or d.name=?3""", nativeQuery = true)
    List<UserDetailsDTO> getStudentDetails(
            String name,
            String year,
            String department);


    @Query(value = """
            SELECT\s
            \tfp.user_id as id,
            \tsp.year as year,
                u.name AS name,
                u.email AS email,
            \tu.phone as phone,
            \td.name as department
            \t
            FROM\s
                Users u
            JOIN\s
                FacultyProfile fp ON u.id = fp.user_id
            JOIN\s
                StudentProfile sp ON sp.department_id = fp.department_id
            JOIN Department d ON d.id = fp.department_id
            WHERE\s
                sp.user_id = ?1""", nativeQuery = true)
    public List<UserDetailsDTO> getFacultyMembers(int id);

    @Query(value = """
            SELECT\s
                u.id as id,
                u.name AS name,
                u.email AS email,
            \tu.phone AS phone,
                d.name AS department,
                sp.year AS year
            FROM\s
                Users u
            JOIN\s
                StudentProfile sp ON u.id = sp.user_id
            JOIN\s
                Department d ON sp.department_id = d.id
            WHERE\s
                u.id = ?1""", nativeQuery = true)
    public Optional<UserDetailsDTO> getUserProfile(int id);


}
