package com.example.springbootbackend.service;
import com.example.springbootbackend.controller.ProblemController;
import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.ProblemRepo;
import com.example.springbootbackend.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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


    public User getUserByProblem(Integer id) {
        logger.info("==========================Getting user by problem {}=======================================================", id);
        Problem problem = problemRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Problem " + id + " not found"));
        return userRepository.findById(problem.getUser().getId()).orElseThrow(
                () -> new RuntimeException("User " + problem.getUser().getId() + " not found"));
    }



    public Problem createProblem(Problem problem) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> getUser =  userRepository.findByUsername(auth.getName());

        logger.info("==========================Creating problem for user {}=======================================================", getUser.get().getUsername());
        User user = userRepository.findByUsername(getUser.get().getUsername()).orElseThrow(
                () -> new RuntimeException("User " + getUser.get().getUsername() + " not found"));
        problem.setUser(user);
        problem.setDeleted(false);
        return problemRepository.save(problem);
    }




    public List<Problem> getAllProblems() {
        List<Problem> all =  problemRepository.findAll();
        return all.stream()
                .filter(problem -> !problem.isDeleted())
                .collect(Collectors.toList());
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
    public boolean deleteProblem(Integer id) {
        Problem problem = getProblemById(id);
        if (problem != null) {
//            problemRepository.delete(problem);
            problem.setDeleted(true);
            problemRepository.save(problem);
            return true;
        } else {
            return false;
        }
    }


    public List<Problem> getProblemsByUser(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new RuntimeException("User " + username + " not found"));
        return problemRepository.findByUser(user);
    }
}
