package com.hillel.task_26_springweb.entity;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
public class Order {

    @Setter(AccessLevel.NONE)
    @Getter(AccessLevel.NONE)
    private static Long currentId = 0L;
    @Setter(AccessLevel.NONE)
    private Long id;
    private LocalDateTime timeStamp;
    private BigDecimal cost;
    private Map<Product, Long> products;

    public Order() {
        timeStamp = LocalDateTime.now();
        this.id = currentId;
        this.cost = new BigDecimal("0.0");
        this.products = new HashMap<>();
        currentId++;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", timestamp=" + timeStamp +
                ", cost=" + cost +
                "," + System.lineSeparator() +
                "products=" + products +
                '}' + System.lineSeparator();
    }
}
