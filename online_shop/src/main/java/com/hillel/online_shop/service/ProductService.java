package com.hillel.online_shop.service;

import com.hillel.online_shop.dto.product.ProductDTO;

import java.util.List;

public interface ProductService {
    ProductDTO findById(long id);

    List<ProductDTO> findAll();

    Long create(ProductDTO productDTO);

    void update(ProductDTO productDTO);

    void delete(long id);
}

