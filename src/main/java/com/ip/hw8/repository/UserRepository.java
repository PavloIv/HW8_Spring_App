package com.ip.hw8.repository;

import com.ip.hw8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("SELECT DISTINCT u FROM User u LEFT JOIN FETCH u.roles WHERE u.email = ?1")
    User findByEmailFetchRole(String email);
    @Query("SELECT u FROM User u WHERE u.email = ?1")
    User findByEmailUser(String email);
}
