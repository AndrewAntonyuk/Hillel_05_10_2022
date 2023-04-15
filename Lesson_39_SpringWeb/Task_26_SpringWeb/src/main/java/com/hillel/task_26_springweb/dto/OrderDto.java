package com.hillel.task_26_springweb.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

@Data
public class OrderDto {

    private Long id;

    private LocalDateTime timeStamp;

    private BigDecimal cost;

    private Map<String, Long> products;
}
