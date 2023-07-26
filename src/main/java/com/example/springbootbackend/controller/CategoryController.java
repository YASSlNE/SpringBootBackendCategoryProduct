package com.example.springbootbackend.controller;

import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    final private CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping()
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @PostMapping
    public Category createCategory(@RequestBody Category cat) {
        return categoryService.createCategory(cat);
    }

    @GetMapping("{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Integer id) {
        Category cat = categoryService.getCategoryById(id);
        return ResponseEntity.ok(cat);
    }

    @PutMapping("{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Integer id, @RequestBody Category updatedCatDetails) {
        Category cat = categoryService.updateCategory(id, updatedCatDetails);
        return ResponseEntity.ok(cat);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Integer id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
