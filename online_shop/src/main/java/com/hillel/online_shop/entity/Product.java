package com.hillel.online_shop.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_name")
    private String name;

    private BigDecimal price;

    private int quantity;


    @OneToMany(mappedBy = "product")
    private List<Purchase> purchases;
}
