package com.doctorsoffice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.doctorsoffice.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
