package com.hillel.task28springdata.dto;

import com.hillel.task28springdata.entity.Product;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {
    private Long id;

    private LocalDate date;

    private BigDecimal cost;

    private List<ProductDTO> products;
}
