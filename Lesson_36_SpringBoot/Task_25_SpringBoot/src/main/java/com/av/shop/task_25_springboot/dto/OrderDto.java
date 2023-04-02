package com.av.shop.task_25_springboot.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;

@Data
public class OrderDto {
    private Date date;

    private Long id;

    private BigDecimal cost;

    private Map<String, Long> products;
}
