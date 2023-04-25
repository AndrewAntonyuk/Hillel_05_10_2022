package com.hillel.task29springsecurity.repository;

import com.hillel.task29springsecurity.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
