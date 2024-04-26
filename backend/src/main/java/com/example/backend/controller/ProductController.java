package com.example.backend.controller;

import com.example.backend.dto.ProductDto;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.repository.CategoryRepository;
import com.example.backend.service.ProductService;
import com.example.backend.service.ProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ProductServiceImpl productServiceImpl;

    @Autowired
    private CategoryRepository categoryRepository;

    //request: add/create product
    @PostMapping("/add")
    public String createProduct(@RequestBody ProductDto productDto){
        Optional<Category> categoryId = categoryRepository.findById(productDto.getCategoryId());
        if(categoryId.isPresent()){
            Category getCategory = categoryId.get();
            Product addProductResult = productServiceImpl.addProduct(productDto, getCategory);

            if(addProductResult != null){
                return "service" + addProductResult;
            }else {
                return "failed to add product";
            }
        }else{
            return "Category not available";
        }
    }

    //request: get all
    @GetMapping("/all")
    public List<ProductDto> getProducts(){
        List<ProductDto> products = productServiceImpl.getAllProduct();
        return products;
    }

//fix the code here: error on updating
    //request: update ProductById
    @PostMapping("update/{productId}")
    public String updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto ){
        Optional<Category> findId = categoryRepository.findById(productDto.getCategoryId());
        if(findId.isPresent()){
            productServiceImpl.updateProductById(productDto, productId);
            return "Product upated successfully";
        }else{
            return "error";
        }
    }


//    public updateProduct(@PathVariable Integer id){}
//    @DeleteMapping("delete/{id}")
//    public deleteProductById(@PathVariable Integer id){}


}
