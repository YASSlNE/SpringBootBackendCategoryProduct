package com.example.springbootbackend.service;

import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.model.Product;
import com.example.springbootbackend.repository.ProductRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceTests {

    @Mock
    private ProductRepo productRepo;

    @Mock
    private CategoryService categoryService;

    @InjectMocks
    private ProductService productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllProducts() {
        List<Product> productList = new ArrayList<>();
        productList.add(new Product(1, "Product 1", 99399, "Description 1", new Category(1, "Category 1", "Description cat 1")));
        productList.add(new Product(2, "Product 2", 3999, "Description 2", new Category(2, "Category 2", "Description cat 2")));
        when(productRepo.findAll()).thenReturn(productList);
        List<Product> result = productService.getAllProducts();
        assertEquals(2, result.size());
    }

}
