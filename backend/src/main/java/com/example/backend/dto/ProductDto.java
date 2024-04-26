package com.example.backend.dto;

import com.example.backend.entity.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.antlr.v4.runtime.misc.NotNull;


@Table
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Access(AccessType.FIELD)
public class ProductDto {
    //optional, only needed for update
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    //from product entity
    @Column(name = "name")
    private  String name;

    @Column(name = "description")
    private  String description;

    @Column(name = "price")
    private Integer price;

    @Column(name = "imageUrl")
    private  String imageUrl;

    //from category entity
    @Column(name = "category_id")
    private Integer categoryId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    //contructor


//    public ProductDto() {
//    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
