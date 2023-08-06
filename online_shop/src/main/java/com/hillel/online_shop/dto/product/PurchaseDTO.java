package com.hillel.online_shop.dto.product;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PurchaseDTO {

    private Long productId;

    private String name;

    private BigDecimal price;

    private int quantity;
}
