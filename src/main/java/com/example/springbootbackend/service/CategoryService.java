package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.repository.CategoryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Service
public class CategoryService {

    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);


    private final CategoryRepo catRepo;

    @Autowired
    public CategoryService(CategoryRepo catRepo) {
        this.catRepo = catRepo;
    }

    public List<Category> getAllCategories() {
        return catRepo.findAll();
    }

    public Category createCategory(Category cat) {
        return catRepo.save(cat);
    }

    public Category getCategoryById(Integer id) {


//        logger.debug("debug message.");
//        logger.info("info message.");
//        logger.warn("warning message.");
//        logger.error("error message.");

        return catRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Category " + id + " not found"));
    }

    public Category updateCategory(Integer id, Category updatedCatDetails) {
        Category cat = getCategoryById(id);
        cat.setName(updatedCatDetails.getName());
        cat.setDescription(updatedCatDetails.getDescription());
        return catRepo.save(cat);
    }

    public void deleteCategory(Integer id) {
        Category cat = getCategoryById(id);
        catRepo.delete(cat);
    }
}
