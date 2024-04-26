package com.example.backend.service;

import com.example.backend.dto.ProductDto;
import com.example.backend.entity.Category;
import com.example.backend.entity.Product;
import com.example.backend.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl {

    @Autowired
    private ProductRepository productRepository;

    //addProduct
    public Product addProduct(ProductDto productDto, Category category) {
        Product newProduct = new Product();

        newProduct.setDescription(productDto.getDescription());
        newProduct.setName(productDto.getName());
        newProduct.setImageUrl(productDto.getImageUrl());
        newProduct.setPrice(productDto.getPrice());

        newProduct.setCategory(category);

        return productRepository.save(newProduct);

    }

    //get:productDto after adding product in productDto
    //similar like adding Product in Dto
    public ProductDto getProductDto(Product product){
        ProductDto productDto = new ProductDto();

        productDto.setDescription(product.getDescription());
        productDto.setName(product.getName());
        productDto.setImageUrl(product.getImageUrl());
        productDto.setPrice(product.getPrice());
        productDto.setId(product.getId());

        productDto.setCategoryId(product.getCategory().getCategoryId());

        return productDto;

    }

    //getAll
    public List<ProductDto> getAllProduct() {

        List<Product> allProduct = productRepository.findAll();

        //response
        List<ProductDto> allProductDto = new ArrayList();
        for(Product product: allProduct){
            allProductDto.add(getProductDto(product));
        }
        return allProductDto;


    }

    //updateProduct:{id}
    public void updateProductById(ProductDto productDto, Integer productId) {

        Optional<Product> findProduct = productRepository.findById(productDto.getCategoryId());
        if(findProduct.isPresent())
        {
            Product getProduct = findProduct.get();

            getProduct.setName(productDto.getName());
            getProduct.setDescription(productDto.getDescription());
            getProduct.setPrice(productDto.getPrice());
            getProduct.setImageUrl(productDto.getImageUrl());

            productRepository.save(getProduct);

        }
    }

    //getProduct:{id}
    public Product findProductById(Integer productId) {
        Optional<Product> findId = productRepository.findById(productId);
        if(findId.isEmpty()){
            throw new RuntimeException("product is invalid" + productId);
        }
        return findId.get();

    }

    //deleteProduct:{id}
    public String deleteProductById(Integer productId) {
        return null;
    }






}
