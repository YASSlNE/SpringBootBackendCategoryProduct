package com.example.springbootbackend.service;
import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.repository.ProblemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepo problemRepository;

    @Autowired
    public ProblemService(ProblemRepo problemRepository) {
        this.problemRepository = problemRepository;
    }

    public Problem createProblem(Problem problem) {
        return problemRepository.save(problem);
    }
    public List<Problem> getAllProblems() {
        return problemRepository.findAll();
    }
    public Problem getProblemById(Integer id) {
        return problemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Problem " + id + " not found"));
    }
    public Problem updateProblem(Integer id, Problem updatedProblemDetails) {
        Problem problem = getProblemById(id);
        problem.setDescription(updatedProblemDetails.getDescription());
        return problemRepository.save(problem);
    }
    public void deleteProblem(Integer id) {
        Problem problem = getProblemById(id);
        problemRepository.delete(problem);
    }

}
