package com.example.springbootbackend.controller;
import com.example.springbootbackend.model.Problem;
import com.example.springbootbackend.service.ProblemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/problem")
public class ProblemController {

    private final ProblemService problemService;
    private final Logger logger = LogManager.getLogger(ProblemController.class); // Initialize logger
    @Autowired
    public ProblemController(ProblemService problemService) {
        logger.info("Get all problems==================||=====================================");

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

    @PostMapping("create")
    public Problem createProblem(@RequestBody Problem problem) {
        return problemService.createProblem(problem);
    }

    @PutMapping("{id}")
    public Problem updateProblem(@PathVariable Integer id,@RequestBody Problem updatedProblemDetails) {
        return problemService.updateProblem(id, updatedProblemDetails);
    }

    @PostMapping("{id}")
    public void deleteProblem(@PathVariable Integer id) {
        problemService.deleteProblem(id);
    }




}
