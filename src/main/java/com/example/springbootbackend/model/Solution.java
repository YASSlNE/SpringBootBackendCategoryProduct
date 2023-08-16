package com.example.springbootbackend.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "solution")
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String code;
    private String description;
    private String language;
    private boolean isDeleted;
    private int score=0;
    @ManyToOne
    @JsonIgnoreProperties("solutions")
    @JoinColumn(name = "problem_id")
    private Problem problem;

    @JsonIgnoreProperties("upvotedSolutions")
    @ManyToMany
    @JoinTable(name = "user_upvoted_solutions",
            joinColumns = @JoinColumn(name = "solution_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"user_id", "solution_id"})) // Adding a unique constraint
    private List<User> upvotingUsers;

}
