package com.example.collegeDirectory.repository;

import com.example.collegeDirectory.model.CollegeDirectoryStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;

public interface CollegeDirectoryStatsRepository extends JpaRepository<CollegeDirectoryStatistics, Integer> {
    //Get students count department-wise
    @Query(value = "select d.name as Department_Name, " +
            "count(*) as Student_Count " +
            "from studentprofile s " +
            "join " +
            "department d " +
            "on s.department_id = d.id " +
            "group by d.name",
            nativeQuery = true)
    public List<CollegeDirectoryStatistics> getDeptWiseStudentCount();

    //get users count role-wise
    @Query(value = "select role as user_role," +
            " count(*) as user_count " +
            "from users " +
            "group by role;", nativeQuery = true)
    public List<CollegeDirectoryStatistics> getUsersCountByRole();

    //get faculties count department-wise
    @Query(value = "select d.name as department_name, " +
            "count(*) as Faculties_Count " +
            "from facultyprofile fp " +
            "join " +
            "department d " +
            "on fp.department_id = d.id " +
            "group by d.name;", nativeQuery = true)
    public List<CollegeDirectoryStatistics> getDeptWiseFacultiesCount();
}
