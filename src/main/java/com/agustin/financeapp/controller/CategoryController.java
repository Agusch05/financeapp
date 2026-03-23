package com.agustin.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.agustin.financeapp.model.Category;
import com.agustin.financeapp.service.CategoryService;

@RestController
@RequestMapping("/category")
@CrossOrigin(origins = "*")
public class CategoryController {
    private final CategoryService categoryService;
    
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    
    @GetMapping
    public List<Category> getAllCategories(){
        return categoryService.getAllCategories();
    }
    
    @PostMapping
    public Category createCategory(@RequestBody Category category) {
        return categoryService.addCategory(category);
    }
}