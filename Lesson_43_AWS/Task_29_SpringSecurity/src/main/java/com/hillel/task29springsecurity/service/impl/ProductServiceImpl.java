package com.hillel.task29springsecurity.service.impl;

import com.hillel.task29springsecurity.dto.ProductDto;
import com.hillel.task29springsecurity.entity.Product;
import com.hillel.task29springsecurity.exception.NoSuchProductException;
import com.hillel.task29springsecurity.repository.ProductRepository;
import com.hillel.task29springsecurity.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ModelMapper modelMapper;

    @Override
    public ProductDto getById(Long id) {
        return modelMapper.map(findById(id), ProductDto.class);
    }

    @Override
    public List<ProductDto> getAll() {
        List<Product> products = (List<Product>) productRepository.findAll();

        return products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public Long create(ProductDto productDto) {
        return productRepository.save(modelMapper.map(productDto, Product.class))
                .getId();
    }

    @Transactional
    @Override
    public void deleteById(Long id) {
          productRepository.delete(findById(id));
    }

    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(()->new NoSuchProductException("Can't find product with id " + id));
    }
}
