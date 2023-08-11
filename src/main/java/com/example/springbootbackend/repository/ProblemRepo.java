package com.example.springbootbackend.repository;
import com.example.springbootbackend.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProblemRepo extends JpaRepository<Problem, Integer> {
}
