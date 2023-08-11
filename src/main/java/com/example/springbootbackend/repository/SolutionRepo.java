package com.example.springbootbackend.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootbackend.model.Solution;

public interface SolutionRepo extends JpaRepository<Solution, Integer> {
}
