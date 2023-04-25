package com.hillel.task28springdata.repository;

import com.hillel.task28springdata.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
