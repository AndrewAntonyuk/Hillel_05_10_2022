package com.hillel.task_26_springweb.entity;

import lombok.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
public class Product {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static Long currentId = 0L;
    @Setter(AccessLevel.NONE)
    private Long id;
    private String name;
    private BigDecimal cost;

    public Product() {
        this.id = currentId;
        currentId++;
    }

    public Product(String name, BigDecimal cost) {
        this();
        this.name = name;
        this.cost = cost;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                '}';
    }
}
