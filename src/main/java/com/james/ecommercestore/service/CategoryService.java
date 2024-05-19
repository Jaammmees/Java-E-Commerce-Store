package com.james.ecommercestore.service;

import com.james.ecommercestore.model.Category;
import com.james.ecommercestore.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    //need to inject repository
    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> findAll() { return categoryRepository.findAll(); }

    public Optional<Category> findById(Long id) { return categoryRepository.findById(id); }

    public Category save(Category category) { return categoryRepository.save(category); }

    public void deleteById(Long id) { categoryRepository.deleteById(id); }

}
