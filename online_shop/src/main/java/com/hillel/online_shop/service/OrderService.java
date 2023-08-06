package com.hillel.online_shop.service;

import com.hillel.online_shop.dto.order.OrderDTO;

import java.util.List;

public interface OrderService {
    OrderDTO findById(long id);

    List<OrderDTO> findAllByUserId(long userId);

    Long create(Long userId, OrderDTO orderDTO);
}
