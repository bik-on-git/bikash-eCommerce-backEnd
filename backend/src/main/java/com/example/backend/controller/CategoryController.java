package com.example.backend.controller;

import com.example.backend.entity.Category;
import com.example.backend.service.CategoryService;
import com.example.backend.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    //request: add/create Category
    @PostMapping("/create")
    public ResponseEntity<String> createCategory(@RequestBody Category category) {
        try
        {
            Category newCategory = categoryServiceImpl.createCategory(category);
            return ResponseEntity.ok("Category" + newCategory);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error" + e.getMessage());
        }


    }

    //request: listAll
    @GetMapping("/list")
    public ResponseEntity<List<Category>> listAllCategory() {

        try
        {
            List<Category> myAllList = categoryServiceImpl.getAllCategory();
            return ResponseEntity.ok(myAllList);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }

    }

    //request: get CategoryById
    @GetMapping("/list/{categoryId}")
    public ResponseEntity<String> getCategory(@PathVariable Integer categoryId) {

        try
        {
            String categoryById = categoryServiceImpl.getCategoryById(categoryId);
            return ResponseEntity.ok("success: " + categoryById);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error" + e.getMessage());
        }

    }

    //request: update CategoryById
    @PostMapping("/update/{categoryId}")
    public ResponseEntity<String> updateCategory(@PathVariable Integer categoryId, @RequestBody Category category) {
        //need to fix some error here
        try
        {
            categoryServiceImpl.updateCategoryById(categoryId, category);
            return ResponseEntity.ok("testing");

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error " + e.getMessage());
        }

    }

    //request: delete CategoryById
    @DeleteMapping("delete/{categoryId}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer categoryId) {

        try
        {
            String deleting = categoryServiceImpl.deleteCategoryById(categoryId);
            return ResponseEntity.ok(deleting);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error" + e.getMessage());
        }

    }


}
