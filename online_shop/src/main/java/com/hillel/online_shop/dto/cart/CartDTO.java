package com.hillel.online_shop.dto.cart;

import com.hillel.online_shop.dto.product.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class CartDTO {

    private Long id;

    private BigDecimal cost;

    private List<ProductDTO> purchases;
}
