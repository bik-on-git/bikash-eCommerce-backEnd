package com.example.backend.dto;

import com.example.backend.entity.Cart;
import com.example.backend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDto {

    private Integer id;
    private Integer quantity;
    private Product product;

    public CartItemDto(Cart cart){
        this.id = cart.getId();
        this.quantity = cart.getQuantity();
        this.setProduct(cart.getProduct());


    }
}
