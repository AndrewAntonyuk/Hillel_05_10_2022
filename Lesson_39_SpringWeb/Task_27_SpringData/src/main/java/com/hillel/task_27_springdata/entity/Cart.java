package com.hillel.task_27_springdata.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
@Table(name = "Carts")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany
    private List<Product> products;

    public Cart() {
    }

    public Cart(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id + "," + System.lineSeparator() +
                "products=" + products +
                '}';
    }
}
