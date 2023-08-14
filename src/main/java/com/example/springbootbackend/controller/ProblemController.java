package com.example.springbootbackend.controller;
import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.repository.UserRepository;
import com.example.springbootbackend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    private final UserRepository userRepository;
    private final ProblemService problemService;
    private final Logger logger = LogManager.getLogger(ProblemController.class); // Initialize logger
    @Autowired
    public ProblemController(ProblemService problemService, UserRepository userRepository) {
        logger.info("Get all problems==================||=====================================");
        this.userRepository = userRepository;
        this.problemService = problemService;
    }

    @GetMapping
    public List<Problem> getAllProblems() {
        logger.info("Get all problems=======================================================");
        return problemService.getAllProblems();
    }
    @GetMapping("{id}")
    public Problem getProblemById(@PathVariable Integer id) {
        return problemService.getProblemById(id);
    }
    @GetMapping("user/{username}")
    public List<Problem> getProblemsByUser(@PathVariable String username) {
        logger.info("==================getProblemsByUser {}=====================================", username);
        return problemService.getProblemsByUser(username);
    }

    @GetMapping("id/{id}")
    public User getUsernameByProblem(@PathVariable Integer id) {
        logger.info("==================getUserByProblem {}=====================================", id);
        return problemService.getUserByProblem(id);
    }




    @PostMapping("create")
    public Problem createProblem(@RequestBody Problem problem) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Optional<User> user =  userRepository.findByUsername(auth.getName());
        return problemService.createProblem(user.get().getUsername(), problem);
    }

    @PutMapping("update/{id}")
    public Problem updateProblem(@PathVariable Integer id,@RequestBody Problem updatedProblemDetails) {
        return problemService.updateProblem(id, updatedProblemDetails);
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteProblem(@PathVariable Integer id) {
        logger.info("=====================================deleting problem number {}", id);
        boolean isDeleted = problemService.deleteProblem(id);

        if (isDeleted) {
            return ResponseEntity.ok("{\"message\": \"Problem with ID " + id + " has been successfully deleted.\"}");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Problem with ID " + id + " not found.");
        }
    }





}
