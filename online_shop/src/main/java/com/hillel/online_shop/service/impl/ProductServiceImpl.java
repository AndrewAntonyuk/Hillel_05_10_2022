package com.hillel.online_shop.service.impl;

import com.hillel.online_shop.dto.product.ProductDTO;
import com.hillel.online_shop.entity.Product;
import com.hillel.online_shop.exception.ProductNotFoundException;
import com.hillel.online_shop.repository.ProductRepository;
import com.hillel.online_shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;

    @Override
    public Long create(ProductDTO productDTO) {
        if (productDTO.getId() != null) {
            throw new IllegalArgumentException("field \"id\" must be null");
        }

        return productRepository.save(modelMapper.map(productDTO, Product.class)).getId();
    }

    @Override
    public void update(ProductDTO productDTO) {
        findById(productDTO.getId());
        safeFill(productDTO);
        productRepository.save(modelMapper.map(productDTO, Product.class));
    }

    @Override
    public void delete(long id) {
        getById(id);
        productRepository.deleteById(id);
    }

    @Override
    public ProductDTO findById(long id) {
        return modelMapper.map(getById(id), ProductDTO.class);
    }

    @Override
    public List<ProductDTO> findAll() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .collect(Collectors.toList());
    }

    private Product getById(long id) {
        return productRepository.findById(id).orElseThrow(() ->
                new ProductNotFoundException("product with id " + id + " not found"));
    }

    private void safeFill(ProductDTO productDTO) {
        Product product = getById(productDTO.getId());

        if(productDTO.getName() == null) productDTO.setName(product.getName());
        if(productDTO.getPrice() == null) productDTO.setPrice(product.getPrice());
        if(productDTO.getQuantity() == null) productDTO.setQuantity(product.getQuantity());
    }
}