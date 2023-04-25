package com.hillel.task29springsecurity.service;

import com.hillel.task29springsecurity.dto.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getById(Long id);

    List<ProductDto> getAll();

    Long create(ProductDto productDto);

    void deleteById(Long id);
}
