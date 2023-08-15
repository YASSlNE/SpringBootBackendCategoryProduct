package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.model.Solution;
import com.example.springbootbackend.repository.ProblemRepo;
import com.example.springbootbackend.repository.SolutionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SolutionService {
    private final SolutionRepo solutionRepo;
    private final ProblemRepo problemRepo;
    @Autowired
    public SolutionService(SolutionRepo solutionRepo, ProblemRepo problemRepo) {
        this.solutionRepo = solutionRepo;
        this.problemRepo = problemRepo;
    }

    public Solution createSolution(Integer id, Solution solution) {
        Problem problem = problemRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Problem " + id + " not found"));
        solution.setProblem(problem);
        return solutionRepo.save(solution);
    }

    public List<Solution> getAllSolutions(){
        return solutionRepo.findAll();
    }

    public Solution getSolutionById(Integer id) {
        return solutionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Solution " + id + " not found"));
    }
    public Solution updateSolution(Integer id, Solution updatedSolutionDetails) {
        Solution solution = getSolutionById(id);
        solution.setCode(updatedSolutionDetails.getCode());
        solution.setLanguage(updatedSolutionDetails.getLanguage());
        solution.setDescription(updatedSolutionDetails.getDescription());
        return solutionRepo.save(solution);
    }

    public void deleteSolution(Integer id){
        Solution solution = getSolutionById(id);
        solutionRepo.delete(solution);
    }


    public List<Solution> getSolutionsByProblemId(Integer id) {
        Problem problem = problemRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Problem " + id + " not found"));
        return solutionRepo.findByProblem(problem);
    }
}
