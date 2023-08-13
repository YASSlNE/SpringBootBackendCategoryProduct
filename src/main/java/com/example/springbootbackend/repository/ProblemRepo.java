package com.example.springbootbackend.repository;
import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProblemRepo extends JpaRepository<Problem, Integer> {
    List<Problem> findByUser(User user);
}
