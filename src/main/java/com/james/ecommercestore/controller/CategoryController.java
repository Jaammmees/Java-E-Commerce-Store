package com.james.ecommercestore.controller;

import com.james.ecommercestore.model.Category;
import com.james.ecommercestore.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id){
        Optional<Category> category = categoryService.findById(id);
        return category.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.noContent().build());
    }

    //add category
    @PostMapping
    public Category createCategory(Category category) {
        return categoryService.save(category);
    }

    //update
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category categoryDetails){
        Optional<Category> category = categoryService.findById(id);
        if(category.isPresent()){
            Category existingCategory = category.get();
            existingCategory.setName(categoryDetails.getName());
            existingCategory.setDescription(categoryDetails.getDescription());
        } else {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Category> deleteCategory(@PathVariable Long id){
        categoryService.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
