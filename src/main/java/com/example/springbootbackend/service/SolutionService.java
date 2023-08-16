package com.example.springbootbackend.service;

import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.model.Solution;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.ProblemRepo;
import com.example.springbootbackend.repository.SolutionRepo;
import com.example.springbootbackend.repository.UserRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SolutionService {
    private final SolutionRepo solutionRepo;
    private final ProblemRepo problemRepo;
    private final UserRepository userRepository;
    private final Logger logger = LogManager.getLogger(SolutionService.class);

    @Autowired
    public SolutionService(SolutionRepo solutionRepo, ProblemRepo problemRepo, UserRepository userRepository){
        this.solutionRepo = solutionRepo;
        this.problemRepo = problemRepo;
        this.userRepository = userRepository;
    }

    public Solution createSolution(Integer id, Solution solution) {
        Problem problem = problemRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Problem " + id + " not found"));
        solution.setProblem(problem);
        solution.setDeleted(false);
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
//        solutionRepo.delete(solution);
        solution.setDeleted(true);
        solutionRepo.save(solution);
    }


    public List<Solution> getSolutionsByProblemId(Integer id) {
        Problem problem = problemRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Problem " + id + " not found"));
        return solutionRepo.findByProblem(problem);
    }


    public void upVote(Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user =  userRepository.findByUsername(auth.getName());
        logger.info("==========================Upvoting solution {}=======================================================", id);
        Solution solution = solutionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Solution "+id+ " not found"));
        List<User> upvotingUsers = solution.getUpvotingUsers();
        upvotingUsers.add(user.get());
        solution.setScore(solution.getScore()+1);
        solutionRepo.save(solution);
    }

    public void downVote(Integer id){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user = userRepository.findByUsername(auth.getName());
        logger.info("==========================Downvoting solution {}=======================================================", id);
        Solution solution = solutionRepo.findById(id).orElseThrow(
                () -> new RuntimeException("Solution "+id+ " not found"));
        List<User> upvotingUsers = solution.getUpvotingUsers();
        upvotingUsers.remove(user.get());
        solution.setScore(solution.getScore()-1);
        solutionRepo.save(solution);
    }
}
