package com.example.ssecuritych11.data.repository;

import com.example.ssecuritych11.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, String> {

    Optional<User> findUserByUsername(String username);
}
