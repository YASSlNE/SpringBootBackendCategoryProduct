package com.example.springbootbackend.service;
import com.example.springbootbackend.controller.ProblemController;
import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.ProblemRepo;
import com.example.springbootbackend.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProblemService {

    private final ProblemRepo problemRepository;
    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger(ProblemService.class);
    @Autowired
    public ProblemService(ProblemRepo problemRepository, UserRepository userRepository) {
        this.problemRepository = problemRepository;
        this.userRepository = userRepository;
    }

    public Problem createProblem(String username, Problem problem) {
        logger.info("==========================Creating problem for user {}=======================================================", username);
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User " + username + " not found"));
        problem.setUser(user);
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

    public List<Problem> getProblemsByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User " + username + " not found"));
        return problemRepository.findByUser(user);
    }
}
