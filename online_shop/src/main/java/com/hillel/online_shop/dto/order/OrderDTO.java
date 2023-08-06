package com.hillel.online_shop.dto.order;

import com.hillel.online_shop.dto.product.ProductDTO;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
public class OrderDTO {

    private Long id;

    private BigDecimal cost;

    private LocalDate date;

    private List<ProductDTO> purchases;
}
