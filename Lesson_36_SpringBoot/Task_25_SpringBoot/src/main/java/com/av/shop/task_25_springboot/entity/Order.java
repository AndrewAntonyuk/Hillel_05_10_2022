package com.av.shop.task_25_springboot.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "orders")
@Data
public class Order {
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "created_date")
    private final Date date;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal cost = new BigDecimal("0.0");

    @ElementCollection
    @CollectionTable(name = "order_products_mapping",
            joinColumns = {@JoinColumn(name = "order_id", referencedColumnName = "id")})
    @MapKeyColumn(name = "product_name")
    @Column(name = "quantity")
    private Map<String, Long> products = new HashMap<>();

    public Order() {
        date = new Date();
    }
}
