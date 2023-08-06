package com.hillel.online_shop.repository;

import com.hillel.online_shop.entity.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
