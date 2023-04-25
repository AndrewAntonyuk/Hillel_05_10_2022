package com.hillel.task29springsecurity.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductDto {
    private Long id;

    private String name;

    private BigDecimal cost;
}
