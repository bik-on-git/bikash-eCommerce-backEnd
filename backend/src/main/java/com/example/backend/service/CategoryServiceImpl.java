package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl {

    @Autowired
    public CategoryRepository categoryRepository;

    //service: create Category
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public List<Category> getAllCategory() {
        List<Category> ls = new ArrayList<>();
        List<Category> allCategory = categoryRepository.findAll();
        return allCategory;
    }

    //service: getById Category
    public String getCategoryById(Integer categoryId) {
        Optional<Category> idOfCategory = categoryRepository.findById(categoryId);
        if (idOfCategory.isPresent()) {
            Category getCategory = idOfCategory.get();
            return "Order details: " + getCategory.getCategoryId() + "\t" + getCategory.getCategoryName() + "\t" + getCategory.getDescription();
        } else {
            return "order not placed";
        }

    }

    //service: update CategoryById
    public void updateCategoryById( Integer categoryId, Category updateCategory){
        Category getCategory = categoryRepository.getById(categoryId);
        getCategory.setCategoryName(updateCategory.getCategoryName());
        getCategory.setDescription(updateCategory.getDescription());


        categoryRepository.save(getCategory);
    }

    //service: delete CategoryById
    public String deleteCategoryById(Integer categoryId){
        Optional<Category> findCategory = categoryRepository.findById(categoryId);
        if(findCategory.isPresent()){
            Category getCategory = findCategory.get();
            categoryRepository.delete(getCategory);
            return "Order Deleted: " + getCategory.getCategoryName() + getCategory.getDescription();

        }else{
            return "Can't find order";
        }

    }


}
