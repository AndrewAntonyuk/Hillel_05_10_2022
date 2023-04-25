package com.hillel.task28springdata.repository;

import com.hillel.task28springdata.entity.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
