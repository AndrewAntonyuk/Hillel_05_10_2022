package com.av.repository;

import com.av.entity.Product;
import com.av.exception.NoSuchProductException;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class ProductRepository {
    private final List<Product> products = new ArrayList<>();

    @PostConstruct
    public void load() {
        products.add(new Product("Cola", new BigDecimal("15.90")));
        products.add(new Product("Super Kontik", new BigDecimal("5.45")));
        products.add(new Product("Sprite", new BigDecimal("12.15")));
        products.add(new Product("Bud", new BigDecimal("9.37")));
        products.add(new Product("Burger", new BigDecimal("8.12")));
        products.add(new Product("Pizza", new BigDecimal("14.99")));
        products.add(new Product("Bulka", new BigDecimal("1.58")));
        products.add(new Product("Cake", new BigDecimal("28.87")));
        products.add(new Product("Fish", new BigDecimal("85.11")));
        products.add(new Product("Potato", new BigDecimal("2.48")));
        products.add(new Product("Pasta", new BigDecimal("10.85")));
    }

    public List<Product> getProducts() {
        return products;
    }

    public Product getProduct(Long id) throws NoSuchProductException {
        return findProduct(id);
    }

    private Product findProduct(Long id) {
        Product checkedProduct = products.stream()
                .filter(product -> product.getCurrentId().equals(id))
                .findFirst()
                .orElse(null);

        if (checkedProduct == null) {
            throw new NoSuchProductException("Product with ID "
                    + id + " doesn't exist in repository");
        }

        return checkedProduct;
    }
}
