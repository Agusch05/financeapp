package com.agustin.financeapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.agustin.financeapp.model.Category;
import com.agustin.financeapp.repository.CategoryRepository;

@Service
public class CategoryService {
    
    private final CategoryRepository categoryRepository;
    
    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }
    
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }
    
    public Category addCategory(Category category) {
        // NO llamar a setId aquí, JPA lo hace automáticamente
        return categoryRepository.save(category);
    }
}