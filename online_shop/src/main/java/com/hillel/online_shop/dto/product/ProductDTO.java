package com.hillel.online_shop.dto.product;

import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;

@Data
public class ProductDTO {

    private Long id;

    @Size(min = 3, message = "Name must be longer than three!")
    private String name;

    @Positive(message = "Price must be greater than zero!")
    private BigDecimal price;

    @Range(min = 0, message = "Quantity must be greater or equal zero!")
    private Integer quantity;

}
