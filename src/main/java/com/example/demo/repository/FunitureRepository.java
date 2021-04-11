package com.example.demo.repository;

import com.example.demo.model.FunitureCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FunitureRepository extends JpaRepository<FunitureCategory, Integer> {
}
