package com.example.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDto {

    private String productName;
    private Integer quantity;
    private double price;
    private long productId;
    private Integer userId;

}
