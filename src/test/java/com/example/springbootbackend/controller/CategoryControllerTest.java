package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.repository.CategoryRepo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CategoryControllerTest {
    CategoryRepo catRepo;
    @Test
    void getAllCategoriesTest() {

        List<Category> l = catRepo.findAll();
        assertTrue(l.size()>0);
    }
}