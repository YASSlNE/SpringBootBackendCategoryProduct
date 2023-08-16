package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Solution;
import com.example.springbootbackend.model.User;
import com.example.springbootbackend.service.SolutionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/solution")
public class SolutionController {
    final private SolutionService solutionService;

    @Autowired
    public SolutionController(SolutionService solutionService) {
        this.solutionService = solutionService;
    }

    @GetMapping
    public List<Solution> getALlSolutions() {
        return solutionService.getAllSolutions();
    }

    @GetMapping("{id}")
    public Solution getSolutionById(@PathVariable Integer id) {
        return solutionService.getSolutionById(id);
    }

    @GetMapping("problem/{id}")
    public List<Solution> getSolutionsByProblemId(@PathVariable Integer id){
        return solutionService.getSolutionsByProblemId(id);
    }

    @PutMapping("{id}")
    public Solution updateSolution(@PathVariable Integer id,@RequestBody Solution updatedSolutionDetails) {
        return solutionService.updateSolution(id, updatedSolutionDetails);
    }

    @DeleteMapping("{id}")
    public void deleteSolution(@PathVariable Integer id) {
        solutionService.deleteSolution(id);
    }

    @PostMapping("affectToProblem/{id}")
    public Solution affectToProblem(@PathVariable Integer id, @RequestBody Solution solution) {
        return solutionService.createSolution(id, solution);
    }

    @PutMapping("upVote/{id}")
    public void upVote(@PathVariable Integer id) {
        solutionService.upVote(id);
    }

    @PutMapping("downVote/{id}")
    public void downVote(@PathVariable Integer id){
        solutionService.downVote(id);
    }

}
