package com.rbc.security.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.rbc.security.model.User;
 
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}