package com.example.collegeDirectory.repository;

import com.example.collegeDirectory.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UsersRepository extends JpaRepository<Users, Integer> {
    @Query(value = "SELECT max(id) FROM users", nativeQuery = true)
    public int findLastInsertedId();
}
