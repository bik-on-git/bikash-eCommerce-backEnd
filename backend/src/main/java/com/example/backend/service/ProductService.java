package com.example.backend.service;

import com.example.backend.entity.Category;
import com.example.backend.entity.Product;

import java.util.List;

public interface ProductService {

    public Product addProduct(Product product);

    public void updateProductById(Integer productId, Product updateProduct);

    public List<Product> getAllProduct();

    public String getProductById(Integer productId);

    public String deleteProductById(Integer productId);
}
