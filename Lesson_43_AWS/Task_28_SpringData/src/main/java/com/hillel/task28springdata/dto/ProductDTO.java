package com.hillel.task28springdata.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDTO {
    private Long id;

    private String name;

    private BigDecimal cost;
}
