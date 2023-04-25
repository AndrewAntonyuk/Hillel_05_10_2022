package com.hillel.task29springsecurity.controller;

import com.hillel.task29springsecurity.dto.ProductDto;
import com.hillel.task29springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    @GetMapping("/product-get/{id}")
    public ProductDto getById(@PathVariable Long id) {
        return productService.getById(id);
    }

    @GetMapping("/get-all")
    public List<ProductDto> getAll() {
        return productService.getAll();
    }

    @PostMapping
    public Long create(@RequestBody ProductDto productDto) {
        return productService.create(productDto);
    }

    @DeleteMapping("/product-delete/{id}")
    public void deleteById(@PathVariable Long id) {
        productService.deleteById(id);
    }
}
