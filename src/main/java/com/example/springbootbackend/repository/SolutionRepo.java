package com.example.springbootbackend.repository;
import com.example.springbootbackend.model.Problem;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.springbootbackend.model.Solution;

import java.util.List;

public interface SolutionRepo extends JpaRepository<Solution, Integer> {
    List<Solution> findByProblem(Problem problem);

}
