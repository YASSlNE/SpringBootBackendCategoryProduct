package com.example.springbootbackend.service;
import com.example.springbootbackend.service.CategoryService;
import com.example.springbootbackend.exception.ResourceNotFoundException;
import com.example.springbootbackend.model.Category;
import com.example.springbootbackend.model.Product;
import com.example.springbootbackend.repository.CategoryRepo;
import com.example.springbootbackend.repository.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private CategoryService catService;
    private ProductRepo productRepo;

    @Autowired
    public ProductService(ProductRepo productRepo, CategoryService catService) {
        this.productRepo = productRepo;
        this.catService=catService;
    }

    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    public Product createProduct(Product product, Integer cat_id) {
        Category cat = catService.getCategoryById(cat_id);
        productRepo.save(product);
        Product p = productRepo.findById(product.getId()).orElseThrow(
                () -> new ResourceNotFoundException("Product " + product.getId() + " not found"));

        p.setId_cat(cat);
        return productRepo.save(product);
    }

    public Product getProductById(Integer id) {
        return productRepo.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Product " + id + " not found"));
    }

    public Product updateProduct(Integer id, Product prodDetails) {
        Product product = getProductById(id);

        product.setDescription(prodDetails.getDescription());
        product.setName(prodDetails.getName());
        product.setPrice(prodDetails.getPrice());
        product.setId_cat(prodDetails.getId_cat());

        return productRepo.save(product);
    }

    public void deleteProduct(Integer id) {
        Product product = getProductById(id);
        productRepo.delete(product);
    }
}
