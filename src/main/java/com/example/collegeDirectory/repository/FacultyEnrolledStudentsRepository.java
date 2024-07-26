package com.example.collegeDirectory.repository;

import com.example.collegeDirectory.dto.EnrolledStudentsDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FacultyEnrolledStudentsRepository extends JpaRepository<EnrolledStudentsDTO, Integer> {
    @Query(value = """
            SELECT\s
                u.id AS id,
                u.name AS name,
                u.phone AS phone,
                u.email AS email
            FROM\s
                users u
            JOIN
                enrollment e ON u.id = e.student_id
            JOIN
                course c ON e.course_id = c.id
            WHERE\s
                c.faculty_id = ?1""", nativeQuery = true)
    public List<EnrolledStudentsDTO> getEnrolledStudents(int id);
}
