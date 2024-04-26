package com.example.backend.service;

import com.example.backend.entity.Category;

import java.util.List;
import java.util.Optional;

public interface CategoryService {

    public Category createCategory(Category category);

   public void updateCategoryById(Integer categoryId, Category updateCategory);

    public List<Category> getAllCategory();

    public String getCategoryById(Integer categoryId);

    public String deleteCategoryById(Integer categoryId);



}
