package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.service.CategoryService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CategoryControllerTests {
    CategoryService catservice;
    @Autowired

    public CategoryControllerTests(CategoryService catservice){
        this.catservice = catservice;
    }
    @Test
    void getAllCategoriesTest() {

        List<Category> l = catservice.getAllCategories();
        assertTrue(l.size()>0);
    }
}