package com.example.ssecuritych11.data.repository;

import com.example.ssecuritych11.data.entity.Otp;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, String> {
    Optional<Otp> findOtpByUsername(String username);

}
