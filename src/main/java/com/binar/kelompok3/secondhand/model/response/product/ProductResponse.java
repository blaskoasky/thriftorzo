package com.binar.kelompok3.secondhand.model.response.product;

import com.binar.kelompok3.secondhand.model.entity.ImageProduct;
import com.binar.kelompok3.secondhand.model.entity.Products;
import com.binar.kelompok3.secondhand.model.entity.Users;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class ProductResponse {

    private String id;
    private String name;
    private Double price;
    private String description;
    private String category;
    private Boolean publish;
    private Integer status;
    private UserResponse userResponse;
    private List<String> imgUrl;

    public ProductResponse() {
    }


    public ProductResponse(Products products, Users user) {
        this.userResponse = userMapper(user);
        this.id = products.getId();
        this.name = products.getName();
        this.price = products.getPrice();
        this.publish = products.getPublish();
        this.description = products.getDescription();
        this.category = products.getCategory();
        this.status = products.getStatus();
        this.imgUrl = products.getImageProducts()
                .stream()
                .map(ImageProduct::getUrl)
                .collect(Collectors.toList());
    }


    public ProductResponse(String name, Double price, String url) {
    }

    private UserResponse userMapper(Users user) {
        return new UserResponse(user.getId(), user.getName(), user.getCityName(), user.getImgUrl());
    }
}
