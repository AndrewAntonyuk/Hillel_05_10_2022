package com.hillel.task_27_springdata.repository;

import com.hillel.task_27_springdata.entity.Product;

import java.util.List;

public interface ProductRepository {
    Long create(Product product);

    boolean deleteById(Long id);

    Product getById(Long id);

    List<Product> getAll();
}
