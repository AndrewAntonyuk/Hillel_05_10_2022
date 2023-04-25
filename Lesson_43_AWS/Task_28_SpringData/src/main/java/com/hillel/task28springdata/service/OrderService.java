package com.hillel.task28springdata.service;

import com.hillel.task28springdata.dto.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO getById(Long id);

    List<OrderDTO> getAll();

    Long add(OrderDTO order);

    void remove(Long id);
}
