package com.av.entity;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Product {
    private static Long counterId = 0L;

    private Long currentId;

    private String name;

    private BigDecimal price;

    public Product(String name, BigDecimal price) {
        this.currentId = counterId;
        this.name = name;
        this.price = price;
        counterId++;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + currentId +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
