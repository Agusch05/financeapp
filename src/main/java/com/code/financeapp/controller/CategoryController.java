package com.code.financeapp.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.code.financeapp.model.Category;
import com.code.financeapp.service.CategoryService;

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