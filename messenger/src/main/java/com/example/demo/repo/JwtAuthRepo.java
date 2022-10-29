package com.example.demo.repo;

import com.example.demo.model.JwtAuth;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JwtAuthRepo extends JpaRepository<JwtAuth,Long> {
}
