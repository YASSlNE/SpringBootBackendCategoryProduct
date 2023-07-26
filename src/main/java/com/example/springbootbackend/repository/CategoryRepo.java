package com.example.springbootbackend.repository;

import com.example.springbootbackend.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepo extends JpaRepository<Category, Integer> {

}
