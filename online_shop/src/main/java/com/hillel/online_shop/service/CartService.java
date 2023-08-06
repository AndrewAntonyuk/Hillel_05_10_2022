package com.hillel.online_shop.service;

import com.hillel.online_shop.dto.cart.CartDTO;
import com.hillel.online_shop.dto.product.ProductDTO;

public interface CartService {
    CartDTO findById(long id);

    CartDTO findByUserId(long userId);

    Long create(Long userId);

    void delete(Long id);

    void add(Long cartId, ProductDTO productDTO);

    void reduceQuantity(Long cartId, ProductDTO productDTO);

    void remove(Long cartId, Long productId);

    void clear(Long cartId);
}
