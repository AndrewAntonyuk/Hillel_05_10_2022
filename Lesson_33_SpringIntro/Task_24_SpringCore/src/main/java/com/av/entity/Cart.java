package com.av.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@Scope(scopeName = "prototype")
public class Cart {
    private final Map<Product, Integer> products = new HashMap<>();

    @Autowired
    private ProductRepository productRepository;

    public void saveProduct(Long id) {
        Product product = productRepository.getProduct(id);

        products.merge(product, 1, Integer::sum);
    }

    public void removeProduct(Long id) {
        Product product = productRepository.getProduct(id);
        Integer currentSum = products.get(product);

        if (currentSum != null) {
            if (currentSum > 1) {
                products.merge(product, -1, Integer::sum);
            } else {
                products.remove(product);
            }
        }
    }

    public String showProducts() {
        return "{" + products.entrySet()
                .stream()
                .map(e -> "(" + e.getKey()
                        + ", quantity=" + e.getValue() + ")")
                .collect(Collectors.joining(";" + System.lineSeparator())) + "}";
    }
}
