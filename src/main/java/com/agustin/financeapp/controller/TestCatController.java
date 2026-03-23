package com.agustin.financeapp.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.web.bind.annotation.*;

// Esta es una clase simple sin dependencias externas
@RestController
@RequestMapping("/testcat")
@CrossOrigin(origins = "*")
public class TestCatController {
    
    private List<SimpleCategory> categories = new ArrayList<>();
    private AtomicLong idGenerator = new AtomicLong();
    
    public TestCatController() {
        SimpleCategory food = new SimpleCategory();
        food.setId(idGenerator.incrementAndGet());
        food.setName("Food");
        categories.add(food);
        
        SimpleCategory transport = new SimpleCategory();
        transport.setId(idGenerator.incrementAndGet());
        transport.setName("Transport");
        categories.add(transport);
    }
    
    @GetMapping
    public List<SimpleCategory> getAll() {
        return categories;
    }
    
    @PostMapping
    public SimpleCategory create(@RequestBody SimpleCategory category) {
        category.setId(idGenerator.incrementAndGet());
        categories.add(category);
        return category;
    }
    
    // Clase interna simple - NO usa JPA
    static class SimpleCategory {
        private Long id;
        private String name;
        
        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
    }
}